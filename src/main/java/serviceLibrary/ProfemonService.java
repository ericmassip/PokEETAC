package serviceLibrary;


import business.LocationBusiness;
import business.ProfemonBusiness;
import entity.Profemon;
import entity.serviceLibraryResults.ImageResult;
import entity.serviceLibraryResults.ProfemonLocationResult;
import infrastructure.ProfemonRepository;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import sun.misc.BASE64Decoder;

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
        String base64Image = imageResult.image.split(",")[1];

        byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);


        File imageFile = new File("/home/ea0/PokEETAC/web/images/profedex/" + imageResult.name + ".png");
        try {
            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
            ImageIO.write(bufferedImage, "png", imageFile);
        }
        catch(Exception e){
            e.printStackTrace();
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
