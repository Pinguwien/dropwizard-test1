package com.dwis.calc.resources;

import com.codahale.metrics.annotation.Timed;
import com.dwis.calc.core.User;
import com.dwis.calc.dto.Saying;
import io.dropwizard.auth.Auth;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Dominik on 02.04.16.
 */
@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
public class HelloResource {

    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public HelloResource(String template, String defaultName){

        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @RolesAllowed("ADMIN")
    @GET
    @Timed
    public Saying sayHello(@Auth User user){
        final String value = String.format(template, defaultName);
        return new Saying(counter.incrementAndGet(), value);
    }
}
