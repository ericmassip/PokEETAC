package Business;

import Entity.Location;
import Infrastructure.LocationRepository;
import org.apache.log4j.Logger;

/**
 * Created by ericmassip on 7/12/16.
 */
public class LocationBusiness {
    LocationRepository locationRepository = new LocationRepository();
    private Logger log = Logger.getLogger(LocationBusiness.class);

    public Location getLocation(int locationId) {
        Location location = new Location();
        locationRepository.selectLocation(location, locationId);
        if(location.getId() == 0) {
            log.warn("No Profemon selected from id " + locationId);
        }
        return location;
    }
}
