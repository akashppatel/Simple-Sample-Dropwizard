package org.akashpatel.dropwizard.dropwizard;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static javax.ws.rs.core.Response.Status.SERVICE_UNAVAILABLE;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
    @GET
    public void sayHello(@Suspended final AsyncResponse asyncResponse) {
        CompletableFuture
            .supplyAsync(() -> "Hello from future!!" )
            .thenApply((result) -> asyncResponse.resume(result))
            .exceptionally(e -> asyncResponse.resume(
                // Here manage and log errors out of control
                Response.status(INTERNAL_SERVER_ERROR)
                        .entity(e.getLocalizedMessage())
                        .build()
            ));
        
        // Set response timeout
        asyncResponse.setTimeout(1000, TimeUnit.MILLISECONDS);
        asyncResponse.setTimeoutHandler(ar -> ar.resume(
            Response.status(SERVICE_UNAVAILABLE)
                  .type(MediaType.WILDCARD_TYPE)
                  .entity("timeout!!!")
                  .build()));        

        
        //asyncResponse.resume("hello async!");
    }
}
