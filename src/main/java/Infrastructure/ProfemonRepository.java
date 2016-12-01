package Infrastructure;

import Entity.Profemon;
import Infrastructure.DAO.DAORepository;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by ericmassip on 01/12/16.
 */
public class ProfemonRepository extends DAORepository {

    private Logger log = Logger.getLogger(ProfemonRepository.class);

    public void insertProfemon(Profemon profemon) {
        insert(profemon);
        log.info("Profemon added: " + profemon.getName() + " with id " + profemon.getId());
    }

    public void selectProfemon(Profemon profemon, int primaryKey) {
        select(profemon, primaryKey);
        log.info("Profemon selected: " + profemon.getName() + " from id " + primaryKey);
    }

    public List<Profemon> getAllProfemons() {
        log.info("All Profemons selected");
        return selectAll(Profemon.class);
    }

    public void updateProfemon(Profemon profemon) {
        update(profemon);
        log.info("Profemon updated: " + profemon.getName());
    }

    public void deleteProfemon(Profemon profemon) {
        delete(profemon);
        log.info("Profemon deleted: " + profemon.getName());
    }


}
