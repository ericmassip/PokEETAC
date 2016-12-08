package Business;

import Entity.Capturado;
import Entity.Location;
import Entity.Profemon;
import Entity.ServiceLibraryResults.ProfemonCapturaResult;
import Entity.User;
import Infrastructure.CapturadoRepository;
import Infrastructure.LocationRepository;
import Infrastructure.ProfemonRepository;
import Infrastructure.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ericmassip on 16/11/16.
 */
public class UserBusinessTest {
    SiteBusiness siteBusiness = new SiteBusiness();
    UserBusiness userBusiness = new UserBusiness();
    UserRepository userRepository = new UserRepository();
    User eric;
    CapturadoRepository capturadoRepository = new CapturadoRepository();
    Capturado ericCapturado1;
    Capturado ericCapturado2;
    ProfemonRepository profemonRepository = new ProfemonRepository();
    Profemon tonimon;
    Profemon juanizard;
    LocationRepository locationRepository = new LocationRepository();
    Location location1;
    Location location2;

    @Before
    public void setUp() throws Exception {
        eric = new User();
        eric.setUsername("erick");
        eric.setPassword("123456");
        eric.setEmail("erick@gmail.com");
        userRepository.insertUser(eric);
        tonimon = new Profemon();
        tonimon.setName("tonimon");
        tonimon.setInitialLevel(1);
        juanizard = new Profemon();
        juanizard.setName("juanizard");
        juanizard.setInitialLevel(2);
        profemonRepository.insertProfemon(tonimon);
        profemonRepository.insertProfemon(juanizard);
        location1 = new Location();
        locationRepository.selectLocation(location1, 1);
        location2 = new Location();
        locationRepository.selectLocation(location2, 2);
        ericCapturado1 = new Capturado();
        ericCapturado1.setIdUser(eric.getId());
        ericCapturado1.setIdProfemon(tonimon.getId());
        ericCapturado1.setIdLocation(location1.getId());
        ericCapturado1.setLevel(1);
        ericCapturado1.setIsSuccessful(true);
        ericCapturado1.setDate(siteBusiness.getFormattedDate(3, Calendar.DECEMBER, 2016));
        ericCapturado2 = new Capturado();
        ericCapturado2.setIdUser(eric.getId());
        ericCapturado2.setIdProfemon(juanizard.getId());
        ericCapturado2.setIdLocation(location2.getId());
        ericCapturado2.setLevel(1);
        ericCapturado2.setIsSuccessful(true);
        ericCapturado2.setDate(siteBusiness.getFormattedDate(6, Calendar.DECEMBER, 2016));
        capturadoRepository.insertCapturado(ericCapturado1);
        capturadoRepository.insertCapturado(ericCapturado2);
    }

    @After
    public void tearDown() throws Exception {
        capturadoRepository.deleteCapturado(ericCapturado1);
        capturadoRepository.deleteCapturado(ericCapturado2);
        userRepository.deleteUser(eric);
        profemonRepository.deleteProfemon(tonimon);
        profemonRepository.deleteProfemon(juanizard);
        this.eric = null;
        this.tonimon = null;
        this.juanizard = null;
        this.location1 = null;
        this.location2 = null;
        this.ericCapturado1 = null;
        this.ericCapturado2 = null;
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

    @Test
    public void getUserLevel() throws Exception {
        Capturado ericCapturado3 = new Capturado();
        ericCapturado3.setIdUser(eric.getId());
        ericCapturado3.setIdProfemon(juanizard.getId());
        ericCapturado3.setIdLocation(location2.getId());
        ericCapturado3.setLevel(1);
        ericCapturado3.setIsSuccessful(false);
        capturadoRepository.insertCapturado(ericCapturado3);
        int userLevel = userBusiness.getUserLevel(eric.getId());
        assertNotNull(userLevel);
        assertEquals(5, userLevel);
        capturadoRepository.deleteCapturado(ericCapturado3);
    }

    @Test
    public void getUserProfemons() throws Exception {
        List<Profemon> ericProfemons = userBusiness.getUserProfemons(eric.getId());
        assertEquals(2, ericProfemons.size());
        assertEquals(tonimon.getId(), ericProfemons.get(0).getId());
        assertEquals(juanizard.getName(), ericProfemons.get(1).getName());
    }

    @Test
    public void getProfemonCapturas() throws Exception {
        List<ProfemonCapturaResult> profemonCapturaResults = userBusiness.getProfemonCapturas(eric.getId());
        assertEquals(2, profemonCapturaResults.size());
        assertEquals(tonimon.getName(), profemonCapturaResults.get(0).name);
    }

    @Test
    public void getSuccessfulCapturadosByDay() throws Exception {
        int successfulCapturados = userBusiness.getSuccessfulCapturadosByDay(eric.getId(), siteBusiness.getFormattedDate(3, Calendar.DECEMBER, 2016));
        assertEquals(1, successfulCapturados);
    }

    @Test
    public void getCapturadosSuccessfulPercentage() throws Exception {
        assertEquals(100, userBusiness.getCapturadosSuccessfulPercentage(eric.getId()), 0.0001);
    }
}