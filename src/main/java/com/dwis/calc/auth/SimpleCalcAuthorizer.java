package com.dwis.calc.auth;

import com.dwis.calc.core.User;
import io.dropwizard.auth.Authorizer;

/**
 * Created by Dominik on 02.04.16.
 */
public class SimpleCalcAuthorizer implements Authorizer<User> {

    @Override
    public boolean authorize(User user, String role) {
        return user.getName().equals("User") && role.equals("ADMIN");
    }
}
