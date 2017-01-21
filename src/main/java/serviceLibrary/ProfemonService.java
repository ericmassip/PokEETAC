package serviceLibrary;

import business.LocationBusiness;
import business.ProfemonBusiness;
import entity.Profemon;
import entity.serviceLibraryResults.ImageResult;
import entity.serviceLibraryResults.ProfemonLocationResult;
import infrastructure.ProfemonRepository;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.OutputStream;
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

    @GET
    @Path("/{profemonId}")
    public Profemon getProfemon(@PathParam("profemonId") int profemonId) {
        return profemonBusiness.getProfemon(profemonId);
    }

    @POST
    public void insertProfemon (Profemon profemon) {
        ProfemonRepository profemonRepository = new ProfemonRepository();
        profemonRepository.insertProfemon(profemon);
    }

    @POST
    @Path("/image")
    public void saveImage (ImageResult imageResult ) {

        byte[] data = Base64.decodeBase64(
                "Aqui es donde va la string gigante," +
                        " pero si la metes aqui a pelo te dará error porque es demasiado grande");
        try (OutputStream stream = new FileOutputStream("path/to/profedex/nombre.png")) {
            stream.write(data);
        }
        catch(Exception e){
            log.info(e.getMessage());
        }
    }

    @DELETE
    @Path("/{profemonId}")
    public void deleteProfemon (@PathParam("profemonId") int profemonId) {
        profemonBusiness.deleteProfemon(profemonId);
    }

    @GET
    @Path("/location/all")
    public List<ProfemonLocationResult> getRandomProfemonLocations() {
        LocationBusiness locationBusiness = new LocationBusiness();
        return locationBusiness.getProfemonLocations();
    }
}
