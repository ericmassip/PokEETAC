package Business;

import Entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

/**
 * Created by ericmassip on 16/11/16.
 */
public class UserBusinessTest {
    UserBusiness userBusiness = new UserBusiness();
    User eric;

    @Before
    public void setUp() throws Exception {
        eric = new User();
        eric.setUsername("eric");
        eric.setPassword("1234");
    }

    @After
    public void tearDown() throws Exception {
        this.eric = null;
    }

    @Test
    public void isRegistered() throws Exception {
        assertFalse(userBusiness.isRegistered("eric", "1234"));
        assertFalse(userBusiness.isRegistered("",""));
    }
}