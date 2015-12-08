# Simple-Sample-Dropwizard
This project shows a very simple example of dropwizard sample and setting up jwt authentication.

DropWizard sample project
=========================

Reference : https://gitlab.com/jomoespe/dropwizard-sample

This project is a sample of **[Dropwizard](http://www.dropwizard.io)**. Follows 
the [Getting Started](http://www.dropwizard.io/getting-started.html).

**Dropwizard** is a library/framework to build Java microservice applications
based on **JAX-RS** specification.

To build the project:
    
    mvn clean package
    
To run the project:

    java -jar target/dropwizard-1.0-SNAPSHOT.jar server

To run the project with a configuration file:

    java -jar target/dropwizard-1.0-SNAPSHOT.jar server [config_file.yml]
    
Example, to start server in port 80 (must be root):

    java -jar target/dropwizard-1.0-SNAPSHOT.jar server server.yml

Then, to access the application:

    curl http://localhost:8080/hello-world
