package com.java;

import com.java.auth.*;
import com.java.db.BookingsDAO;
import com.java.db.CarDAO;
import com.java.db.TokenDAO;
import com.java.db.UserDAO;
import com.java.resources.CarResource;
import com.java.resources.TokenResource;
import com.java.resources.UserResource;
import io.dropwizard.Application;
import io.dropwizard.auth.*;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.skife.jdbi.v2.DBI;

import javax.servlet.Filter;


public class CarRentalApplication extends Application<CarRentalConfiguration> {

    public static void main(final String[] args) throws Exception {
        new CarRentalApplication().run(args);
    }

    @Override
    public String getName() {
        return "CarRental";
    }

    @Override
    public void initialize(final Bootstrap<CarRentalConfiguration> bootstrap) {
        // TODO: application initialization
        bootstrap.addBundle(new MigrationsBundle<CarRentalConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(CarRentalConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
    }

    @Override
    public void run(final CarRentalConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");
        final CarDAO carDAO = jdbi.onDemand(CarDAO.class);
        final UserDAO userDAO = jdbi.onDemand(UserDAO.class);
        final TokenDAO tokenDAO = jdbi.onDemand(TokenDAO.class);
        final BookingsDAO bookingsDAO = jdbi.onDemand(BookingsDAO.class);
        environment.jersey().register(new CarResource(carDAO, bookingsDAO));
        environment.jersey().register(new UserResource(userDAO, tokenDAO, bookingsDAO));
        environment.jersey().register(new TokenResource(userDAO, tokenDAO));
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(MyUser.class));
        BasicAuthenticator authenticator = new BasicAuthenticator(userDAO, tokenDAO);
        UserAuthFilter filter = new UserAuthFilter(authenticator);
        environment.jersey().register(new AuthDynamicFeature(filter));
    }

}
