package Infrastructure;

import Entity.Location;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by ericmassip on 1/12/16.
 */
public class LocationRepositoryTest {
    LocationRepository locationRepository = new LocationRepository();
    Location location1;

    @Before
    public void setUp() throws Exception {
        location1 = new Location();
    }

    @After
    public void tearDown() throws Exception {
        this.location1 = null;
    }

    @Test
    public void selectLocation() throws Exception {
        locationRepository.selectLocation(location1, 1);
        assertNotNull(location1.getLatitude());
        assertNotNull(location1.getLongitude());
        assertNotNull(location1.getFloor());
    }

    @Test
    public void getAllLocations() throws Exception {
        List<Location> locations = locationRepository.getAllLocations();
        assertNotNull(locations);
    }

}