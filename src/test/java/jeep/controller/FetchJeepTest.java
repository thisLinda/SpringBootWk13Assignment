package jeep.controller;

import com.promineotech.jeep.Constants;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import com.promineotech.jeep.JeepSales;
import jeep.controller.support.FetchJeepTestSupport;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes={JeepSales.class})
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {
        "classpath:flyway/migrations/V1.0__Jeep_Schema.sql",
        "classpath:flyway/migrations/V1.1__Jeep_Data.sql"},
        config=@SqlConfig(encoding = "utf-8"))

@Slf4j
class FetchJeepTest extends FetchJeepTestSupport {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int serverPort;

//    given when then, Martin Fowler

    @Test
    void testThatJeepsAreReturnedWhenAValidModelAndTrimAreSupplied() {
//        Given: a valid model, trim, and URI
        JeepModel model = JeepModel.WRANGLER;
        String trim = "Sport";
        String uri = String.format("http://localhost:%d/jeeps?model=%s&trim=%s", serverPort, model, trim);
        System.out.println(uri);
//        String uri = String.format("%s?model=%s&trim=%s", getBaseUri(), model, trim);
//            System.out.println(uri);

//        When: a connection is made to the URI
        ResponseEntity<List<Jeep>> response = restTemplate.exchange(
                uri, HttpMethod.GET,null, new ParameterizedTypeReference<>() {});
//        ResponseEntity<Jeep> response = restTemplate.getForEntity(uri, Jeep.class);

//        Then: a success (200 ok) status code is returned
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

//        And: the actual list returned is the same as the expected list
        List<Jeep> actual = response.getBody();
        List<Jeep> expected = buildExpected();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testThatAnErrorMessageIsReturnedWhenAnUnknownTrimIsSupplied() {
//        Given: a valid model, trim, and URI
        JeepModel model = JeepModel.WRANGLER;
        String trim = "Unknown Value";
        String uri = String.format("http://localhost:%d/jeeps?model=%s&trim=%s", serverPort, model, trim);
        System.out.println(uri);

//        When: a connection is made to the URI
        ResponseEntity<Map<String, Object>> response;
        response = restTemplate.exchange(
                uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });

//        Then: a not found (404) status code is returned
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

//        And: an error message is returned
        Map<String, Object> error = response.getBody();

        assertErrorMessageValid(error, HttpStatus.NOT_FOUND);
    }

    @ParameterizedTest
    @MethodSource("jeep.controller.FetchJeepTest#parametersForInvalidInput")
    void testThatAnErrorMessageIsReturnedWhenAnInvalidTrimIsSupplied(String model, String trim, String reason) {
//        Given: a valid model, trim, and URI
        String uri = String.format("http://localhost:%d/jeeps?model=%s&trim=%s", serverPort, model, trim);
        System.out.println(uri);

//        When: a connection is made to the URI
        ResponseEntity<Map<String, Object>> response;
        response = restTemplate.exchange(
                uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });

//        Then: a not found (404) status code is returned
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

//        And: an error message is returned
        Map<String, Object> error = response.getBody();

        assertErrorMessageValid(error, HttpStatus.BAD_REQUEST);
    }

    static Stream<Arguments> parametersForInvalidInput() {
        // @formatter:off
        return Stream.of(
                arguments(
                        "WRANGLER", "@#$*",
                        "Trim contains non-alpha-numeric chars"),
                arguments(
                        "WRANGLER", "C".repeat(Constants.TRIM_MAX_LENGTH + 1),
                        "Trim length too long"),
                arguments(
                        "INVALID", "Sport",
                        "Model is not enum value")
        //@formatter:on
        );
    }

}