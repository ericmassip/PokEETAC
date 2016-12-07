package ServiceLibrary;

import Business.UserBusiness;
import Entity.Profemon;
import Entity.ServiceLibraryResults.AuthenticationResult;
import Entity.ServiceLibraryResults.UserLevelResult;
import Entity.User;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by ericmassip on 30/11/16.
 */
@Path("/user")
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

    @GET
    @Path("/level/{userId}")
    public UserLevelResult getUserLevel (@PathParam("userId") int userId) {
        UserLevelResult userLevelResult = new UserLevelResult();
        userLevelResult.userLevel = userBusiness.getUserLevel(userId);
        return userLevelResult;
    }

    @GET
    @Path("/profemons/{userId}")
    public List<Profemon> getUserProfemons (@PathParam("userId") int userId) {
        return userBusiness.getUserProfemons(userId);
    }
}
