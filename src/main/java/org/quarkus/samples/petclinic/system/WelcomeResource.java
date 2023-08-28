package org.quarkus.samples.petclinic.system;

import java.util.Locale;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.quarkus.qute.TemplateInstance;

@Path("/")
@PermitAll // welcome page is accessible to all users
public class WelcomeResource {

    @Inject
    TemplatesLocale templates;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get() {
        return templates.welcome("null", false);
    }

}
