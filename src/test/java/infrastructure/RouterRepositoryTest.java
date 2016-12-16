package infrastructure;

import entity.Router;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by ericmassip on 1/12/16.
 */
public class RouterRepositoryTest {
    RouterRepository routerRepository = new RouterRepository();
    Router router1;

    @Before
    public void setUp() throws Exception {
        router1 = new Router();
    }

    @After
    public void tearDown() throws Exception {
        this.router1 = null;
    }

    @Test
    public void selectLocation() throws Exception {
        routerRepository.selectRouter(router1, 1);
        assertNotNull(router1.getBSSID());
        assertNotNull(router1.getFloor());
    }

    @Test
    public void getAllLocations() throws Exception {
        List<Router> routers = routerRepository.getAllRouters();
        assertNotNull(routers);
    }

}