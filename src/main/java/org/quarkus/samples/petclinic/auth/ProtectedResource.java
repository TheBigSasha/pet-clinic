package org.quarkus.samples.petclinic.auth;

import io.quarkus.qute.TemplateInstance;
import io.quarkus.security.Authenticated;
import io.smallrye.mutiny.Uni;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.quarkus.samples.petclinic.system.TemplatesLocale;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/protected")
@Authenticated
public class ProtectedResource {

    @Inject
    JsonWebToken principal;

    @Inject
    TemplatesLocale templates;

    @GET
    @RolesAllowed("user")
    @Produces("text/plain")
    @Path("userName")
    public Uni<String> userName() {
        return Uni.createFrom().item(principal.getName());
    }

    @GET
    @RolesAllowed("admin")
    @Produces("text/plain")
    @Path("adminName")
    public Uni<String> adminName() {
        return Uni.createFrom().item(principal.getName());
    }
}

