package serviceLibrary;

import entity.User;
import entity.serviceLibraryResults.AuthenticationResult;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by ericmassip on 1/12/16.
 */
@Path("/hello")
public class HelloRESTAPIService {
    private Logger log = Logger.getLogger(HelloRESTAPIService.class);

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public  String sayHello(){
        return "Welcome to the world of REST";
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public User userHello() {
        User user = new User();
        user.setUsername("eric");
        return user;
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public AuthenticationResult loginHello(User user) {
        AuthenticationResult authenticationResult = new AuthenticationResult();
        log.info("User " + user.getUsername() + " trying to log in");
        authenticationResult.isSuccessful = true;
        return authenticationResult;
    }
}
