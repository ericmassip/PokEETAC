package ServiceLibrary;

import Business.ProfemonBusiness;
import Entity.Profemon;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by ericmassip on 5/12/16.
 */
@Path("/profemon")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfemonService {
    private Logger log = Logger.getLogger(ProfemonService.class);
    ProfemonBusiness profemonBusiness = new ProfemonBusiness();

    @GET
    @Path("/all/{filterBy: .*}")
    public List<Profemon> getProfemons(@PathParam("filterBy") String filterBy) {
        return profemonBusiness.getProfemons(filterBy);
    }

}
