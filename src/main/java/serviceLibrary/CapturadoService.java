package serviceLibrary;

import entity.Capturado;
import infrastructure.CapturadoRepository;
import org.apache.log4j.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Calendar;

/**
 * Created by ericmassip on 8/12/16.
 */
@Path("/capturado")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CapturadoService {
    private Logger log = Logger.getLogger(CapturadoService.class);

    @POST
    public void insertCapturado(Capturado capturado) {
        CapturadoRepository capturadoRepository = new CapturadoRepository();
        Calendar calendarRightNow = Calendar.getInstance();
        capturado.setDate(calendarRightNow);
        capturado.setLevel(1);
        capturadoRepository.insertCapturado(capturado);
    }
}
