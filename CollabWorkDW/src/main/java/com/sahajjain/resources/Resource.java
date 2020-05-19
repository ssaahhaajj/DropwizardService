package com.sahajjain.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.sahajjain.api.Saying;
import com.sahajjain.model.User;
import io.dropwizard.auth.Auth;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Optional;

@RolesAllowed("Admin")
@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class Resource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    @Inject
    public Resource(@Named("template") String template, @Named("defualtName") String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public Saying sayHello(@QueryParam("name") Optional<String> name, @Auth User user) {
        final String value = String.format(template, name.orElse(user.getName()));
        return new Saying(counter.incrementAndGet(), value);
    }
}