package Business;

import Entity.Profemon;
import Infrastructure.ProfemonRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by ericmassip on 6/12/16.
 */
public class ProfemonBusinessTest {
    ProfemonBusiness profemonBusiness = new ProfemonBusiness();
    ProfemonRepository profemonRepository = new ProfemonRepository();
    Profemon tonimon;
    Profemon juanizard;

    @Before
    public void setUp() throws Exception {
        tonimon = new Profemon();
        tonimon.setName("tonimon");
        tonimon.setInitialLevel(1);
        juanizard = new Profemon();
        juanizard.setName("juanizard");
        juanizard.setInitialLevel(2);
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
    public void getProfemonsFilteredBy() throws Exception {
        String filterBy = "tonimon";
        List<Profemon> profemons = profemonBusiness.getProfemons(filterBy);
        assertEquals(1, profemons.size());
        filterBy = "";
        profemons = profemonBusiness.getProfemons(filterBy);
        assertNotEquals(1, profemons.size());
    }

    @Test
    public void getRandomProfemon() throws Exception {
        int randomProfemonId = profemonBusiness.getRandomProfemonId(10);
        assertTrue(0 <= randomProfemonId);
        assertTrue(randomProfemonId < 10);
    }
}