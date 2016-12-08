package Business;

import Entity.Capturado;
import Infrastructure.CapturadoRepository;
import org.apache.log4j.Logger;

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
}
