package com.dwis.calc;

import com.dwis.calc.auth.SimpleCalcAuthenticator;
import com.dwis.calc.auth.SimpleCalcAuthorizer;
import com.dwis.calc.core.User;
import com.dwis.calc.dao.UserDao;
import com.dwis.calc.resources.HelloResource;
import com.dwis.calc.resources.UserResource;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

/**
 * Created by Dominik on 02.04.16.
 */
public class CalcApplication extends Application<CalcConfiguration> {

    public static void main(String[] args) throws Exception{
        new CalcApplication().run(args);
    }

    private final HibernateBundle<CalcConfiguration> hibernate = new HibernateBundle<CalcConfiguration>(User.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(CalcConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public String getName(){
        return "RomanCalc";
    }

    @Override
    public void initialize(Bootstrap<CalcConfiguration> bootstrap){
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(CalcConfiguration calcConfiguration,
                    Environment environment){

        final HelloResource helloResource = new HelloResource(
                calcConfiguration.getTemplate(),
                calcConfiguration.getDefaultName()
        );
        final UserDao dao = new UserDao(hibernate.getSessionFactory());

        environment.jersey().register(new UserResource(dao));
        environment.jersey().register(helloResource);
        environment.jersey().register(new AuthDynamicFeature(
                new BasicCredentialAuthFilter.Builder<User>()
                        .setAuthenticator(new SimpleCalcAuthenticator())
                        .setAuthorizer(new SimpleCalcAuthorizer())
                        .setRealm("SUPER SECRET STUFF")
                        .buildAuthFilter()));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        //If you want to use @Auth to inject a custom Principal type into your resource
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
    }
}
