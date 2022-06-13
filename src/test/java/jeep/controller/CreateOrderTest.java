package jeep.controller;

import com.promineotech.jeep.JeepSales;
import jeep.controller.support.CreateOrderTestSupport;
import lombok.Getter;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes={JeepSales.class})
@ActiveProfiles("test")
@Sql(
        scripts = {"classpath:flyway/migrations/V1.0__Jeep_Schema.sql",
            "classpath:flyway/migrations/V1.1__Jeep_Data.sql"},
        config=@SqlConfig(encoding = "utf-8"))
public class CreateOrderTest extends CreateOrderTestSupport {

    @LocalServerPort
    private int serverPort;

    @Autowired
    @Getter
    private TestRestTemplate restTemplate;

    @Test
    void testCreateOrderReturnsSuccess201() {
        // Given: an order as JSON
        String body = createOrderBody();
        String uri = getBaseUriForOrders();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);

        // When: the order is sent
        ResponseEntity<?> response = getRestTemplate().exchange(uri, HttpMethod.POST, bodyEntity, Order.class);

        // Then: a 201 status is returned
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        // And: the returned order is correct
    }

}