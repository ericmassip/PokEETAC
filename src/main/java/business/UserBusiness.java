package business;

import entity.Capturado;
import entity.Profemon;
import entity.Router;
import entity.User;
import entity.serviceLibraryResults.AuthenticationResult;
import entity.serviceLibraryResults.ProfemonCapturadoResult;
import entity.serviceLibraryResults.ProfemonLocationResult;
import entity.serviceLibraryResults.ScannedRouterResult;
import infrastructure.CapturadoRepository;
import infrastructure.RouterRepository;
import infrastructure.UserRepository;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by ericmassip on 12/11/16.
 */
public class UserBusiness {
    UserRepository userRepository = new UserRepository();
    private Logger log = Logger.getLogger(UserBusiness.class);

    public AuthenticationResult isLoginSuccessful(User userLoggingIn) {
        AuthenticationResult authenticationResult = new AuthenticationResult();
        for (User user : userRepository.getAllUsers()) {
            if (user.getUsername().equals(userLoggingIn.getUsername()) && user.getPassword().equals(userLoggingIn.getPassword())) {
                authenticationResult.isSuccessful = true;
                authenticationResult.userId = user.getId();
                authenticationResult.isAdmin = user.getIsAdmin();
            }
        }
        return authenticationResult;
    }

    public AuthenticationResult isRegisterSuccessful(User newUser) {
        AuthenticationResult authenticationResult = new AuthenticationResult();
        authenticationResult.isSuccessful = true;
        for (User user : userRepository.getAllUsers()) {
            try {
                if (user.getUsername().equals(newUser.getUsername()) || user.getEmail().equals(newUser.getEmail())) {
                    authenticationResult.isSuccessful = false;
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
                log.error("User " + user.getUsername() + " has null email");
            }
        }
        if (authenticationResult.isSuccessful) {
            userRepository.insertUser(newUser);
            authenticationResult.userId = newUser.getId();
        }
        return authenticationResult;
    }

    public int getUserLevel(int userId) {
        int totalLevel = 0;
        try {
            for (ProfemonCapturadoResult profemonCapturadoResult : getUserProfemons(userId)) {
                totalLevel += profemonCapturadoResult.level;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            log.error("Getting the level of userId " + userId);
        }
        return totalLevel;
    }

    public User getUser(int userId) {
        User user = new User();
        userRepository.selectUser(user, userId);
        if (user.getUsername() == null) {
            log.warn("No User selected from id " + userId);
        }
        return user;
    }

    public List<ProfemonCapturadoResult> getUserProfemons(int userId) {
        ProfemonBusiness profemonBusiness = new ProfemonBusiness();
        CapturadoRepository capturadoRepository = new CapturadoRepository();
        User user = getUser(userId);
        List<ProfemonCapturadoResult> profemonCapturadoResults = new ArrayList<>();
        try {
            for (Capturado capturado : capturadoRepository.getUserCapturados(user)) {
                Profemon profemonCapturado = profemonBusiness.getProfemon(capturado.getIdProfemon());
                ProfemonCapturadoResult profemonCapturadoResult = new ProfemonCapturadoResult();
                profemonCapturadoResult.fillIntheFields(profemonCapturado.getId(), profemonCapturado.getName(), profemonCapturado.getInitialLevel() + capturado.getLevel());
                profemonCapturadoResults.add(profemonCapturadoResult);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            log.error("Getting the profemons of userId " + userId);
        }
        return profemonCapturadoResults;
    }

    public List<ProfemonLocationResult> getProfemonCapturas(int userId) {
        CapturadoBusiness capturadoBusiness = new CapturadoBusiness();
        CapturadoRepository capturadoRepository = new CapturadoRepository();
        List<ProfemonLocationResult> profemonCapturas = new ArrayList<>();
        User user = getUser(userId);
        try {
            profemonCapturas = capturadoBusiness.getProfemonLocationResults(capturadoRepository.getUserCapturados(user));
        } catch (NullPointerException e) {
            e.printStackTrace();
            log.error("Getting the profemonCapturas of userId " + userId);
        }
        return profemonCapturas;
    }

    public int getSuccessfulCapturadosByDay(int userId, Calendar calendar) {
        CapturadoRepository capturadoRepository = new CapturadoRepository();
        User user = getUser(userId);
        int successfulCapturados = 0;
        try {
            for (Capturado capturado : capturadoRepository.getUserCapturados(user)) {
                if (DateUtils.isSameDay(capturado.getDate(), calendar)) {
                    successfulCapturados += 1;
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            log.error("Getting successful capturados by day of userId " + userId);
        }
        return successfulCapturados;
    }

    public double getCapturadosSuccessfulPercentage(int userId) {
        CapturadoRepository capturadoRepository = new CapturadoRepository();
        User user = getUser(userId);
        double successfulPercentage = 0;
        try {
            double successfulCapturados = capturadoRepository.getUserCapturados(user).size();
            double allCapturadosAttempts = capturadoRepository.getUserCapturadosAllAttempts(user).size();
            successfulPercentage = (successfulCapturados / allCapturadosAttempts) * 100;
        } catch (NullPointerException e) {
            e.printStackTrace();
            log.error("Getting successful capturados percentage of userId " + userId);
        }
        return successfulPercentage;
    }

    public int getUserFloor(List<ScannedRouterResult> scannedRouters) {
        RouterRepository routerRepository = new RouterRepository();
        RouterBusiness routerBusiness = new RouterBusiness();
        int pointsZeroFloor = 0;
        int pointsFirstFloor = 0;
        int pointsSecondFloor = 0;
        int pointsThirdFloor = 0;

        for (ScannedRouterResult scannedRouter : scannedRouters) {
            Router router = routerRepository.selectRouterByBSSID(scannedRouter.BSSID);
            if (router.getBSSID() != null) {
                Double signalLevel = scannedRouter.signalLevel;
                Integer signalLevelIntValue = signalLevel.intValue();
                switch (router.getFloor()) {
                    case 0:
                        pointsZeroFloor += routerBusiness.getCorrespondingPointsForScannedRouter(signalLevelIntValue);
                        break;
                    case 1:
                        pointsFirstFloor += routerBusiness.getCorrespondingPointsForScannedRouter(signalLevelIntValue);
                        break;
                    case 2:
                        pointsSecondFloor += routerBusiness.getCorrespondingPointsForScannedRouter(signalLevelIntValue);
                        break;
                    case 3:
                        pointsThirdFloor += routerBusiness.getCorrespondingPointsForScannedRouter(signalLevelIntValue);
                        break;
                }
            }
        }
        int[] pointsPerFloor = {pointsZeroFloor, pointsFirstFloor, pointsSecondFloor, pointsThirdFloor};
        return routerBusiness.getFloorWithMaximumPoints(NumberUtils.max(pointsPerFloor), pointsPerFloor);
    }
}
