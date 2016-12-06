package Infrastructure;

import Entity.Profemon;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by ericmassip on 1/12/16.
 */
public class ProfemonRepositoryTest {
    ProfemonRepository profemonRepository = new ProfemonRepository();
    Profemon tonimon;
    Profemon juanizard;

    @Before
    public void setUp() throws Exception {
        tonimon = new Profemon();
        tonimon.setName("tonimon");
        juanizard = new Profemon();
        juanizard.setName("juanizard");
        profemonRepository.insertProfemon(tonimon);
        profemonRepository.insertProfemon(juanizard);
    }

    @After
    public void tearDown() throws Exception {
        profemonRepository.deleteProfemon(tonimon);
        profemonRepository.deleteProfemon(juanizard);
        this.tonimon = null;
        this.juanizard = null;
    }

    @Test
    public void insertProfemon() throws Exception {
        int idTonimon = tonimon.getId();
        int idJuanizard = juanizard.getId();
        profemonRepository.selectProfemon(juanizard,idTonimon);
        assertEquals(juanizard.getId(), tonimon.getId());
        assertEquals(juanizard.getName(), tonimon.getName());
        profemonRepository.selectProfemon(juanizard, idJuanizard);
    }

    @Test
    public void selectProfemon() throws Exception {
        int idTonimon = tonimon.getId();
        Profemon casalsmon = new Profemon();
        profemonRepository.selectProfemon(casalsmon, idTonimon);
        assertEquals(casalsmon.getId(), tonimon.getId());
        assertEquals(casalsmon.getName(), tonimon.getName());
    }

    @Test
    public void getAllProfemons() throws Exception {
        List<Profemon> allProfemons = profemonRepository.getAllProfemons();
        assertEquals(allProfemons.get(allProfemons.size() - 2).getId(), tonimon.getId());
        assertEquals(allProfemons.get(allProfemons.size() - 1).getId(), juanizard.getId());
    }

    @Test
    public void updateProfemon() throws Exception {
        tonimon.setName("toniksson");
        profemonRepository.updateProfemon(tonimon);
        assertEquals("toniksson", tonimon.getName());
    }

    @Test
    public void deleteProfemon() throws Exception {
        int idUserToDelete = juanizard.getId();
        profemonRepository.deleteProfemon(juanizard);
        Profemon casalsmon = new Profemon();
        profemonRepository.selectProfemon(casalsmon, idUserToDelete);
        assertNotEquals(casalsmon.getName(), juanizard.getName());
    }

    @Test
    public void getProfemonsFilteredBy() throws Exception {
        List<Profemon> profemons = profemonRepository.getProfemonsFilteredBy("tonimon");
        assertEquals(1, profemons.size());
        assertEquals(tonimon.getName(), profemons.get(0).getName());
    }

}