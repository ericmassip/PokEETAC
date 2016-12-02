package Business;

import Entity.User;
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
}
