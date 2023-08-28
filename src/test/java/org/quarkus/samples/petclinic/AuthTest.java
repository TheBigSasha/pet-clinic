package org.quarkus.samples.petclinic;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class AuthTest {

    @Test
    void shouldAccessPublicWhenAnonymous() {
        get("/")
                .then()
                .statusCode(HttpStatus.SC_OK);

    }

    @Test
    void shouldAccessAdminWhenAdminAuthenticated() {
        given()
                .auth().preemptive().basic("admin", "admin")
                .when()
                .get("/vets")
                .then()
                .statusCode(HttpStatus.SC_OK);

    }
}
