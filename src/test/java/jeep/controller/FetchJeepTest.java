package jeep.controller;

import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import com.promineotech.jeep.JeepSales;
import jeep.controller.support.FetchJeepTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes={JeepSales.class})
public class FetchJeepTest extends FetchJeepTestSupport {

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
//
//        ResponseEntity<Jeep> response = restTemplate.getForEntity(uri, Jeep.class);

//        Then: a success (200 ok) status code is returned
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.println(getBaseUri());
    }

}