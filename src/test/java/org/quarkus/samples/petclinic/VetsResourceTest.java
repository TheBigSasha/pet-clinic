package org.quarkus.samples.petclinic;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.quarkus.samples.petclinic.system.ErrorExceptionMapper.ERROR_HEADER;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import org.junit.jupiter.api.Test;
import org.quarkus.samples.petclinic.owner.OwnersResource;
import org.quarkus.samples.petclinic.vet.VetResource;

@QuarkusTest
@TestHTTPEndpoint(VetResource.class)
public class VetsResourceTest {

    @Test
    @TestSecurity(user = "jeff", roles = {"admin"})
    void vetPage() {
        when().get("vets.html").then()
                .statusCode(200)
                .header(ERROR_HEADER, is(nullValue()));
    }
}
