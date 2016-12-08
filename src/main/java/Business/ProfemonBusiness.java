package Business;

import Entity.Profemon;
import Infrastructure.ProfemonRepository;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Random;

/**
 * Created by ericmassip on 5/12/16.
 */
public class ProfemonBusiness {
    ProfemonRepository profemonRepository = new ProfemonRepository();
    private Logger log = Logger.getLogger(UserBusiness.class);

    public List<Profemon> getProfemons(String filterBy) {
        if(filterBy == null || filterBy.equals("")) {
            return profemonRepository.getAllProfemons();
        } else {
            return profemonRepository.getProfemonsFilteredBy(filterBy);
        }
    }

    public Profemon getProfemon(int profemonId) {
        Profemon profemon = new Profemon();
        profemonRepository.selectProfemon(profemon, profemonId);
        if(profemon.getName() == null) {
            log.warn("No Profemon selected from id " + profemonId);
        }
        return profemon;
    }

    public void deleteProfemon(int profemonId) {
        Profemon profemon = getProfemon(profemonId);
        try {
            profemonRepository.deleteProfemon(profemon);
        } catch(NullPointerException e) {
            e.printStackTrace();
            log.error("Deleting profemon with id " + profemonId);
        }
    }

    public int getRandomProfemonId(int profemonsSize) {
        Random random = new Random();
        int randomProfemonId = random.nextInt(profemonsSize) + 1;
        if(random.nextInt(100) < 75) {
            return randomProfemonId;
        } else {
            return 0;
        }
    }
}
