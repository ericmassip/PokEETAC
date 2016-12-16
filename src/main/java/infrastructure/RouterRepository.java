package infrastructure;

import entity.Router;
import infrastructure.DAO.DAORepository;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by ericmassip on 1/12/16.
 */
public class RouterRepository extends DAORepository {
    private Logger log = Logger.getLogger(RouterRepository.class);

    public void selectRouter(Router router, int primaryKey) {
        select(router, primaryKey);
        log.info("Router selected: BSSID -> " + router.getBSSID() + ", floor -> " + router.getFloor() + " from id " + primaryKey);
    }

    public List<Router> getAllRouters() {
        log.info("All Routers selected");
        return selectAll(Router.class);
    }
}
