package ServiceLibrary;

import Business.UserBusiness;
import Entity.Profemon;
import Entity.ServiceLibraryResults.*;
import Entity.User;
import Infrastructure.UserRepository;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GET
    @Path("/capturados/{userId}")
    public List<ProfemonLocationResult> getProfemonCapturas (@PathParam("userId") int userId) {
        return userBusiness.getProfemonCapturas(userId);
    }

    @GET
    @Path("/capturados/successfulByDay/{userId}")
    public SuccessfulCapturadoByDayResult getSuccessfulCapturadosByDay (@PathParam("userId") int userId) {
        SuccessfulCapturadoByDayResult successfulCapturadoByDayResult = new SuccessfulCapturadoByDayResult();
        Calendar calendarDay = Calendar.getInstance();
        Map<Integer, Integer> successfulCapturadosByDay = new HashMap<>();
        for(int i = 1; i < 8; i++) {
            calendarDay.add(Calendar.DATE, -1);
            successfulCapturadosByDay.put(i, userBusiness.getSuccessfulCapturadosByDay(userId, calendarDay));
        }
        successfulCapturadoByDayResult.setCapturadosByDay(successfulCapturadosByDay);
        return successfulCapturadoByDayResult;
    }

    @GET
    @Path("/capturados/successfulPercentage/{userId}")
    public SuccessfulPercentageResult getCapturadosSuccessfulPercentage (@PathParam("userId") int userId) {
        SuccessfulPercentageResult successfulPercentageResult = new SuccessfulPercentageResult();
        successfulPercentageResult.successfulPercentage = userBusiness.getCapturadosSuccessfulPercentage(userId);
        return successfulPercentageResult;
    }

    @GET
    @Path("/all")
    public List<User> getAllUsers() {
        UserRepository userRepository = new UserRepository();
        return userRepository.getAllUsers();
    }
}
