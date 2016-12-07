package Business;

import Entity.Capturado;
import Entity.Profemon;
import Entity.User;
import Infrastructure.CapturadoRepository;
import Infrastructure.UserRepository;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by ericmassip on 12/11/16.
 */
public class UserBusiness {
    UserRepository userRepository = new UserRepository();
    private Logger log = Logger.getLogger(UserBusiness.class);

    public boolean isLoginSuccessful(String username, String password) {
        boolean isLoginSuccessful = false;
        for (User user: userRepository.getAllUsers()) {
            if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
                isLoginSuccessful = true;
            }
        }
        return isLoginSuccessful;
    }

    public boolean isRegisterSuccessful(User newUser) {
        boolean isRegisterSuccessful = true;
        for (User user: userRepository.getAllUsers()) {
            if(user.getUsername().equals(newUser.getUsername()) || user.getEmail().equals(newUser.getEmail())) {
                isRegisterSuccessful = false;
            }
        }
        if (isRegisterSuccessful) {
            userRepository.insertUser(newUser);
        }
        return isRegisterSuccessful;
    }

    public int getUserLevel(int userId) {
        CapturadoRepository capturadoRepository = new CapturadoRepository();
        int totalLevel = 0;
        User user = getUser(userId);
        try {
            capturadoRepository.setUserProfemonsCapturadosFromDatabase(user);
            for (Profemon profemon : user.getProfemons()) {
                totalLevel = totalLevel + profemon.getInitialLevel();
            }
            for (Capturado capturado : capturadoRepository.getUserCapturados(user)) {
                totalLevel = totalLevel + capturado.getLevel();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            log.error("Getting the level of userId " + userId);
        }
        return totalLevel;
    }

    private User getUser(int userId) {
        User user = new User();
        userRepository.selectUser(user, userId);
        if(user.getUsername() == null) {
            log.warn("No User selected from id " + userId);
        }
        return user;
    }

    public List<Profemon> getUserProfemons(int userId) {
        CapturadoRepository capturadoRepository = new CapturadoRepository();
        User user = getUser(userId);
        try {
            capturadoRepository.setUserProfemonsCapturadosFromDatabase(user);
            return user.getProfemons();
        } catch (NullPointerException e) {
            e.printStackTrace();
            log.error("Getting the profemons of userId " + userId);
            return null;
        }
    }
}
