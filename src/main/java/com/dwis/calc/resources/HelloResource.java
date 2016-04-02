package com.dwis.calc.resources;

import com.codahale.metrics.annotation.Timed;
import com.dwis.calc.api.Saying;
import com.dwis.calc.core.User;
import com.dwis.calc.dao.UserDao;
import com.google.common.base.Optional;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
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
