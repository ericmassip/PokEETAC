package infrastructure;

import entity.Location;
import infrastructure.DAO.DAORepository;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by ericmassip on 1/12/16.
 */
public class LocationRepository extends DAORepository {
    private Logger log = Logger.getLogger(LocationRepository.class);

    public void selectLocation(Location location, int primaryKey) {
        select(location, primaryKey);
        log.info("Location selected: latitude -> " + location.getLatitude() + ", longitude -> " + location.getLongitude() + ", floor -> " + location.getFloor() + " from id " + primaryKey);
    }

    public List<Location> getAllLocations() {
        log.info("All Locations selected");
        return selectAll(Location.class);
    }
}
