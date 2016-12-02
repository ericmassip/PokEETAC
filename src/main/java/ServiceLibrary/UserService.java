package ServiceLibrary;

import Business.UserBusiness;
import Entity.AuthenticationResult;
import Entity.User;
import org.apache.log4j.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by ericmassip on 30/11/16.
 */
@Path("/pokeetac/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserService {
    private Logger log = Logger.getLogger(UserService.class);
    UserBusiness userBusiness = new UserBusiness();

    @POST
    @Path("/login")
    public AuthenticationResult login (User user) {
        AuthenticationResult authenticationResult = new AuthenticationResult();
        authenticationResult.isSuccessful = userBusiness.isLoginSuccessful(user.getUsername(), user.getPassword());
        log.info("Login: " + user.getUsername() + " tried to log in and the loginResult was " + authenticationResult.isSuccessful);
        return authenticationResult;
    }

    @POST
    @Path("/register")
    public AuthenticationResult register (User newUser) {
        AuthenticationResult authenticationResult = new AuthenticationResult();
        authenticationResult.isSuccessful = userBusiness.isRegisterSuccessful(newUser);
        log.info("Register: " + newUser.getUsername() + " tried to register and the registerResult was " + authenticationResult.isSuccessful);
        return authenticationResult;
    }
}
