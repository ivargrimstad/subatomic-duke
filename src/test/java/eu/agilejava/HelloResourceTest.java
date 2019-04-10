package eu.agilejava;

import io.quarkus.test.junit.QuarkusTest;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class HelloResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/messages/1")
          .then()
             .statusCode(200)
             .body(is("{\"persistent\":true,\"message\":\"Howdy\"}"));
    }

}