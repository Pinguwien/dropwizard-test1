package com.dwis.calc.resources;

import com.codahale.metrics.annotation.Timed;
import com.dwis.calc.api.Saying;
import com.dwis.calc.core.User;
import com.dwis.calc.dao.UserDao;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Dominik on 02.04.16.
 */
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private UserDao userDao;
    public UserResource(UserDao dao){
        this.userDao = dao;
    }

    @RolesAllowed("ADMIN")
    @GET
    @Timed
    @UnitOfWork
    public List<User> getUsers(@Auth User user){
        return userDao.findAll();
    }
}
