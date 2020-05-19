package com.sahajjain;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.sahajjain.auth.AppAuthorizer;
import com.sahajjain.auth.AppBasicAuthenticator;
import com.sahajjain.db.EmployeeDAO;
import com.sahajjain.model.User;
import com.sahajjain.db.UserDAO;
import com.sahajjain.resources.EmployeeResource;
import com.sahajjain.service.EmployeeService;
import com.sahajjain.service.UserService;
import com.sahajjain.resources.Resource;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.jdbi.DBIFactory;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.skife.jdbi.v2.DBI;

public class CollabWorkDWApplication extends Application<CollabWorkDWConfiguration> {

    public static void main(final String[] args) throws Exception {
        new CollabWorkDWApplication().run(args);
    }

    @Override
    public String getName() {
        return "CollabWorkDW";
    }

    @Override
    public void initialize(final Bootstrap<CollabWorkDWConfiguration> bootstrap) {

    }

    @Override
    public void run(CollabWorkDWConfiguration c, Environment e) {

        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(e, c.getDatabase(), "DBDW");

        final UserDAO userDAO=jdbi.onDemand(UserDAO.class);
        final EmployeeDAO employeeDAO=jdbi.onDemand((EmployeeDAO.class));

        Injector injector = Guice.createInjector(new SampleModule());
        final Resource helloDropWizardResource = injector.getInstance(Resource.class);

        e.jersey().register(helloDropWizardResource);
        e.jersey().register(new EmployeeResource(new EmployeeService(employeeDAO)));

        e.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
                .setAuthenticator(new AppBasicAuthenticator(new UserService(userDAO)))
                .setAuthorizer(new AppAuthorizer())
                .setRealm("BASIC-AUTH-REALM")
                .buildAuthFilter()));
        e.jersey().register(RolesAllowedDynamicFeature.class);
        e.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
        e.jersey().register(new DropwizardSkolExceptionMapper());

    }

}
