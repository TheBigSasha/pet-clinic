package org.quarkus.samples.petclinic.auth;

import io.quarkus.qute.TemplateInstance;
import org.quarkus.samples.petclinic.system.TemplatesLocale;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class AuthResource {
    @Inject
    TemplatesLocale templates;

    @GET
    @Path("/login/error")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance authErrorTemplate() {
        return templates.login(List.of("Login failed!"));
    }

    @GET
    @Path("/login")
    @Produces(MediaType.TEXT_HTML)
    /**
     * Renders the login.html
     *
     * @return
     */
    public TemplateInstance loginTemplate() {
        return templates.login(Collections.EMPTY_LIST);
    }
}
