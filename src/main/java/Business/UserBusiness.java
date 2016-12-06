package Business;

import Entity.Capturado;
import Entity.Profemon;
import Entity.User;
import Infrastructure.CapturadoRepository;
import Infrastructure.UserRepository;

/**
 * Created by ericmassip on 12/11/16.
 */
public class UserBusiness {
    UserRepository userRepository = new UserRepository();

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

    public User getUser(int userId) {
        User user = new User();
        userRepository.selectUser(user, userId);
        return user;
    }

    public int getUserLevel(int userId) {
        CapturadoRepository capturadoRepository = new CapturadoRepository();
        User user = getUser(userId);
        capturadoRepository.setUserProfemonsCapturadosFromDatabase(user);
        int totalLevel = 0;
        for (Profemon profemon: user.getProfemons()) {
            totalLevel = totalLevel + profemon.getInitialLevel();
        }
        for (Capturado capturado: capturadoRepository.getUserCapturados(user)) {
            totalLevel = totalLevel + capturado.getLevel();
        }
        return totalLevel;
    }
}
