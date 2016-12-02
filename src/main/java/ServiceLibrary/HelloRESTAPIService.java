package ServiceLibrary;

import Entity.User;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by ericmassip on 1/12/16.
 */
@Path("/hello")
public class HelloRESTAPIService {
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
}
