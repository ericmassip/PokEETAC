package Business;

import Infrastructure.LocationRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by ericmassip on 8/12/16.
 */
public class LocationBusinessTest {
    LocationBusiness locationBusiness = new LocationBusiness();
    LocationRepository locationRepository = new LocationRepository();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getProfemonLocations() throws Exception {
        assertTrue(locationBusiness.getProfemonLocations().size() <= locationRepository.getAllLocations().size());
    }

}