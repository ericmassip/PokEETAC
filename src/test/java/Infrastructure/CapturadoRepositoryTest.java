package Infrastructure;

import Business.SiteBusiness;
import Entity.Capturado;
import Entity.Location;
import Entity.Profemon;
import Entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by ericmassip on 1/12/16.
 */
public class CapturadoRepositoryTest {
    SiteBusiness siteBusiness = new SiteBusiness();
    CapturadoRepository capturadoRepository = new CapturadoRepository();
    Capturado ericCapturado1;
    Capturado ericCapturado2;
    UserRepository userRepository = new UserRepository();
    User eric;
    ProfemonRepository profemonRepository = new ProfemonRepository();
    Profemon tonimon;
    Profemon juanizard;
    LocationRepository locationRepository = new LocationRepository();
    Location location1;
    Location location2;

    @Before
    public void setUp() throws Exception {
        eric = new User();
        eric.setUsername("ericmassip");
        eric.setPassword("1234");
        userRepository.insertUser(eric);
        tonimon = new Profemon();
        tonimon.setName("tonimon");
        juanizard = new Profemon();
        juanizard.setName("juanizard");
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
        ericCapturado1.setIsSuccessful(true);
        ericCapturado1.setDate(siteBusiness.getFormattedDate(3, Calendar.DECEMBER, 2016));
        ericCapturado2 = new Capturado();
        ericCapturado2.setIdUser(eric.getId());
        ericCapturado2.setIdProfemon(juanizard.getId());
        ericCapturado2.setIdLocation(location2.getId());
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
    public void insertCapturado() throws Exception {
        int idEricCapturado1 = ericCapturado1.getId();
        int idEricCapturado2 = ericCapturado2.getId();
        capturadoRepository.selectCapturado(ericCapturado2,idEricCapturado1);
        assertEquals(ericCapturado2.getId(), ericCapturado1.getId());
        assertEquals(ericCapturado2.getIdProfemon(), ericCapturado1.getIdProfemon());
        capturadoRepository.selectCapturado(ericCapturado2, idEricCapturado2);
    }

    @Test
    public void selectCapturado() throws Exception {
        int idEricCapturado1 = ericCapturado1.getId();
        Capturado ericCapturado3 = new Capturado();
        capturadoRepository.selectCapturado(ericCapturado3, idEricCapturado1);
        assertEquals(ericCapturado3.getId(), ericCapturado1.getId());
        assertEquals(ericCapturado3.getIdUser(), ericCapturado1.getIdUser());
        assertEquals(ericCapturado3.getIdProfemon(), ericCapturado1.getIdProfemon());
        assertEquals(ericCapturado3.getIdLocation(), ericCapturado1.getIdLocation());
        assertEquals(3, ericCapturado1.getDate().get(Calendar.DAY_OF_MONTH));
        assertEquals(Calendar.DECEMBER, ericCapturado1.getDate().get(Calendar.MONTH));
        assertEquals(2016, ericCapturado1.getDate().get(Calendar.YEAR));
    }

    @Test
    public void getAllCapturados() throws Exception {
        List<Capturado> allCapturados = capturadoRepository.getAllCapturados();
        assertEquals(allCapturados.get(allCapturados.size() - 2).getId(), ericCapturado1.getId());
        assertEquals(allCapturados.get(allCapturados.size() - 1).getId(), ericCapturado2.getId());
    }

    @Test
    public void updateCapturado() throws Exception {
        ericCapturado1.setLevel(2);
        capturadoRepository.updateCapturado(ericCapturado1);
        assertEquals(2, ericCapturado1.getLevel());
    }

    @Test
    public void deleteCapturado() throws Exception {
        int idUserToDelete = ericCapturado1.getId();
        capturadoRepository.deleteCapturado(ericCapturado1);
        Capturado ericCapturado3 = new Capturado();
        capturadoRepository.selectCapturado(ericCapturado3, idUserToDelete);
        assertNotEquals(ericCapturado3.getIdUser(), ericCapturado1.getIdUser());
    }

    @Test
    public void setUserProfemonsCapturadosFromDatabase() throws Exception {
        capturadoRepository.setUserProfemonsCapturadosFromDatabase(eric);
        List<Profemon> ericProfemons = eric.getProfemons();
        assertNotNull(ericProfemons);
        assertNotEquals(ericProfemons.size(), 0);
        assertEquals(ericProfemons.get(ericProfemons.size() - 2).getId(), tonimon.getId());
        assertEquals(ericProfemons.get(ericProfemons.size() - 1).getId(), juanizard.getId());
    }

    @Test
    public void getUserCapturados() throws Exception {
        List<Capturado> ericCapturados = capturadoRepository.getUserCapturados(eric);
        assertEquals(2, ericCapturados.size());
        assertEquals(ericCapturados.get(0).getId(), ericCapturado1.getId());
        assertEquals(ericCapturados.get(0).getIdUser(), ericCapturado1.getIdUser());
        assertEquals(ericCapturados.get(0).getIdProfemon(), ericCapturado1.getIdProfemon());
        assertEquals(ericCapturados.get(0).getIdLocation(), ericCapturado1.getIdLocation());
    }

    @Test
    public void getUserCapturadosAllAttempts() throws Exception {
        List<Capturado> capturadosAllAttempts = capturadoRepository.getUserCapturadosAllAttempts(eric);
        assertEquals(2, capturadosAllAttempts.size());
        assertEquals(capturadosAllAttempts.get(1).getId(), ericCapturado2.getId());
    }
}