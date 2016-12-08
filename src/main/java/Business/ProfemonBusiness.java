package Business;

import Entity.Profemon;
import Infrastructure.ProfemonRepository;
import org.apache.log4j.Logger;

import java.util.List;

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
}
