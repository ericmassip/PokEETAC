package Business;

import Entity.Location;
import Entity.Profemon;
import Entity.ServiceLibraryResults.ProfemonLocationResult;
import Infrastructure.LocationRepository;
import Infrastructure.ProfemonRepository;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

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

    public List<ProfemonLocationResult> getProfemonLocations() {
        ProfemonBusiness profemonBusiness = new ProfemonBusiness();
        ProfemonRepository profemonRepository = new ProfemonRepository();
        List<ProfemonLocationResult> profemonLocationResults = new ArrayList<>();
        for (Location location : locationRepository.getAllLocations()) {
            int randomProfemonId = profemonBusiness.getRandomProfemonId(profemonRepository.getAllProfemons().size());
            if(randomProfemonId != 0) {
                Profemon profemon = profemonBusiness.getProfemon(randomProfemonId);
                ProfemonLocationResult profemonLocationResult = new ProfemonLocationResult();
                profemonLocationResult.fillInTheFields(profemon.getId(), profemon.getName(), location.getLatitude(), location.getLongitude(), location.getFloor());
                profemonLocationResults.add(profemonLocationResult);
            }
        }
        return profemonLocationResults;
    }
}
