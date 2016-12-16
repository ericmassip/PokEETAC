package business;

import entity.Capturado;
import entity.Location;
import entity.Profemon;
import entity.serviceLibraryResults.ProfemonLocationResult;
import infrastructure.CapturadoRepository;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ericmassip on 8/12/16.
 */
public class CapturadoBusiness {
    CapturadoRepository capturadoRepository = new CapturadoRepository();
    private Logger log = Logger.getLogger(CapturadoBusiness.class);

    public Capturado getCapturado(int capturadoId) {
        Capturado capturado = new Capturado();
        capturadoRepository.selectCapturado(capturado, capturadoId);
        if(capturado.getId() == 0) {
            log.warn("No Capturado selected from id " + capturadoId);
        }
        return capturado;
    }

    public List<ProfemonLocationResult> getProfemonLocationResults(List<Capturado> capturados) {
        ProfemonBusiness profemonBusiness = new ProfemonBusiness();
        LocationBusiness locationBusiness = new LocationBusiness();
        List<ProfemonLocationResult> profemonLocationResults = new ArrayList<>();
        for (Capturado capturado : capturados) {
            Profemon profemonCapturado = profemonBusiness.getProfemon(capturado.getIdProfemon());
            Location locationCapturado = locationBusiness.getLocation(capturado.getIdLocation());
            ProfemonLocationResult profemonLocationResult = new ProfemonLocationResult();
            profemonLocationResult.fillInTheFields(profemonCapturado.getId(), profemonCapturado.getName(), locationCapturado.getId(), locationCapturado.getLatitude(), locationCapturado.getLongitude(), locationCapturado.getFloor());
            profemonLocationResults.add(profemonLocationResult);
        }
        return profemonLocationResults;
    }
}
