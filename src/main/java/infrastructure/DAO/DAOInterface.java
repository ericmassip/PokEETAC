package infrastructure.DAO;

import java.util.List;

/**
 * Created by ericmassip on 12/11/16.
 */
public interface DAOInterface {
    void insert(Object object);
    void select(Object object, int primaryKey);
    void update(Object object);
    void delete(Object object);
    List selectAll(Class classToLoad);
}
