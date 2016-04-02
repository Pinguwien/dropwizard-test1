package com.dwis.calc.dao;

import com.dwis.calc.core.User;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by Dominik on 02.04.16.
 */
public class UserDao extends AbstractDAO<User> {
    public UserDao(SessionFactory factory) {
        super(factory);
    }

    public User findById(Long id) {
        return get(id);
    }

    public long create(User user) {
        return persist(user).getId();
    }

    public List<User> findAll() {

        return list(namedQuery("com.dwis.calc.core.User.findAll"));
    }
}