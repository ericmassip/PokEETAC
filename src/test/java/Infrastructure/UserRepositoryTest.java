package Infrastructure;

import Entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by ericmassip on 16/11/16.
 */
public class UserRepositoryTest {
    UserRepository userRepository = new UserRepository();
    User eric;
    User samuel;

    @Before
    public void setUp() throws Exception {
        eric = new User();
        eric.setUsername("ericmassip");
        eric.setPassword("1234");
        samuel = new User();
        samuel.setUsername("nessemut");
        samuel.setPassword("0000");
        userRepository.insertUser(eric);
        userRepository.insertUser(samuel);
    }

    @After
    public void tearDown() throws Exception {
        userRepository.deleteUser(eric);
        userRepository.deleteUser(samuel);
        this.eric = null;
        this.samuel = null;
    }

    @Test
    public void insertUser() throws Exception {
        int idEric = eric.getId();
        userRepository.selectUser(samuel,idEric);
        assertEquals(samuel.getId(), eric.getId());
        assertEquals(samuel.getUsername(), eric.getUsername());
    }

    @Test
    public void selectUser() throws Exception {
        int idEric = eric.getId();
        User juan = new User();
        userRepository.selectUser(juan, idEric);
        assertEquals(juan.getId(), eric.getId());
        assertEquals(juan.getUsername(), eric.getUsername());
        assertEquals(juan.getPassword(), eric.getPassword());
    }

    @Test
    public void getAllUsers() throws Exception {
        List<User> allUsers = userRepository.selectAll(User.class);
        assertEquals(allUsers.get(allUsers.size() - 2).getId(), eric.getId());
        assertEquals(allUsers.get(allUsers.size() - 1).getId(), samuel.getId());
    }

    @Test
    public void updateUser() throws Exception {
        eric.setUsername("ericksson");
        userRepository.updateUser(eric);
        assertEquals("ericksson", eric.getUsername());
    }

    @Test
    public void deleteUser() throws Exception {
        int idUserToDelete = samuel.getId();
        userRepository.deleteUser(samuel);
        User juan = new User();
        userRepository.selectUser(juan, idUserToDelete);
        assertNotEquals(juan.getUsername(), samuel.getUsername());
    }

}