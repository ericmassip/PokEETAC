package Business;

import Entity.User;
import Infrastructure.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by ericmassip on 16/11/16.
 */
public class UserBusinessTest {
    UserBusiness userBusiness = new UserBusiness();
    UserRepository userRepository = new UserRepository();
    User eric;

    @Before
    public void setUp() throws Exception {
        eric = new User();
        eric.setUsername("erick");
        eric.setPassword("123456");
        eric.setEmail("erick@gmail.com");
        userRepository.insertUser(eric);
    }

    @After
    public void tearDown() throws Exception {
        userRepository.deleteUser(eric);
        this.eric = null;
    }

    @Test
    public void isLoginSuccessful() throws Exception {
        assertTrue(userBusiness.isLoginSuccessful("erick", "123456"));
        assertFalse(userBusiness.isLoginSuccessful("",""));
    }

    @Test
    public void isRegisterSuccessful() throws Exception {
        User samuel = new User();
        samuel.setUsername("samuel");
        samuel.setPassword("0000");
        samuel.setEmail("samuel@gmail.com");
        samuel.setIsAdmin(true);
        assertTrue(userBusiness.isRegisterSuccessful(samuel));
        assertFalse(userBusiness.isRegisterSuccessful(samuel));
        assertFalse(userBusiness.isRegisterSuccessful(eric));
        userRepository.deleteUser(samuel);
    }
}