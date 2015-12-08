package org.akashpatel.dropwizard.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import io.dropwizard.setup.Bootstrap;

public class AppApplication extends Application<AppConfiguration>{

    public static void main(String...args) throws Exception {
        new AppApplication().run(args);
    }
    
    @Override
    public void run(AppConfiguration t, Environment e) throws Exception {
        e.jersey().register(new HelloWorldResource());
    }
    
    
    @Override
    public String getName() {
        return "my first DropWizard.io application";
    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        // nothing to do yet
    }

}
