package ServiceLibrary;

import Business.UserBusiness;
import Entity.LoginResult;
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
    public LoginResult login (User user) {
        LoginResult loginResult = new LoginResult();
        loginResult.isSuccessful = userBusiness.isLoginSuccessful(user.getUsername(), user.getPassword());
        log.info("login: " + user.getUsername() + " tried to log in and the loginResult was " + loginResult.isSuccessful);
        return loginResult;
    }
}
