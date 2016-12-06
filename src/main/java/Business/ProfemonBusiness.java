package Business;

import Entity.Profemon;
import Infrastructure.ProfemonRepository;

import java.util.List;

/**
 * Created by ericmassip on 5/12/16.
 */
public class ProfemonBusiness {
    ProfemonRepository profemonRepository = new ProfemonRepository();

    public List<Profemon> getProfemons(String filterBy) {
        if(filterBy == null || filterBy.equals("")) {
            return profemonRepository.getAllProfemons();
        } else {
            return profemonRepository.getProfemonsFilteredBy(filterBy);
        }
    }
}
