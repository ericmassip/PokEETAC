package serviceLibrary;

import business.UserBusiness;
import entity.User;
import entity.serviceLibraryResults.*;
import infrastructure.UserRepository;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserService {
    private Logger log = Logger.getLogger(UserService.class);
    UserBusiness userBusiness = new UserBusiness();

    @POST
    @Path("/login")
    public AuthenticationResult login(User user) {
        AuthenticationResult authenticationResult = userBusiness.isLoginSuccessful(user);
        log.info("Login: " + user.getUsername() + " tried to log in and the loginResult was " + authenticationResult.isSuccessful);
        return authenticationResult;
    }

    @POST
    @Path("/register")
    public AuthenticationResult register(User newUser) {
        AuthenticationResult authenticationResult = userBusiness.isRegisterSuccessful(newUser);
        log.info("Register: " + newUser.getUsername() + " tried to register and the registerResult was " + authenticationResult.isSuccessful);
        return authenticationResult;
    }

    @GET
    @Path("/{userId}")
    public User getUser(@PathParam("userId") int userId) {
        return userBusiness.getUser(userId);
    }

    @PUT
    @Path("/admin")
    public void setIsAdmin(User user) {
        userBusiness.setIsAdmin(user);
    }

    @GET
    @Path("/level/{userId}")
    public UserLevelResult getUserLevel(@PathParam("userId") int userId) {
        UserLevelResult userLevelResult = new UserLevelResult();
        userLevelResult.userLevel = userBusiness.getUserLevel(userId);
        return userLevelResult;
    }

    @GET
    @Path("/profemons/{userId}")
    public List<ProfemonCapturadoResult> getUserProfemons(@PathParam("userId") int userId) {
        return userBusiness.getUserProfemons(userId);
    }

    @GET
    @Path("/capturados/{userId}")
    public List<ProfemonLocationResult> getProfemonCapturas(@PathParam("userId") int userId) {
        return userBusiness.getProfemonCapturas(userId);
    }

    @GET
    @Path("/capturados/successfulByDay/{userId}")
    public List<SuccessfulCapturadoByDayResult> getSuccessfulCapturadosByDay(@PathParam("userId") int userId) {
        List<SuccessfulCapturadoByDayResult> successfulCapturadoByDayResults = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendarDay = Calendar.getInstance();
        for (int i = 1; i < Calendar.DAY_OF_WEEK + 1; i++) {
            SuccessfulCapturadoByDayResult successfulCapturadoByDayResult = new SuccessfulCapturadoByDayResult();
            successfulCapturadoByDayResult.setSuccessfulCapturadoByDayResult(dateFormat.format(calendarDay.getTime()), userBusiness.getSuccessfulCapturadosByDay(userId, calendarDay));
            successfulCapturadoByDayResults.add(successfulCapturadoByDayResult);
            calendarDay.add(Calendar.DATE, -1);
        }
        return successfulCapturadoByDayResults;
    }

    @GET
    @Path("/capturados/successfulPercentage/{userId}")
    public SuccessfulPercentageResult getCapturadosSuccessfulPercentage(@PathParam("userId") int userId) {
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

    @POST
    @Path("/location/floor")
    public UserFloorResult getFloor(List<ScannedRouterResult> scannedRouters) {
        UserFloorResult userFloorResult = new UserFloorResult();
        userFloorResult.floor = userBusiness.getUserFloor(scannedRouters);
        return userFloorResult;
    }
}
