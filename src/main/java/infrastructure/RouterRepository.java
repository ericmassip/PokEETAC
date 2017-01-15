package infrastructure;

import entity.Router;
import infrastructure.DAO.DAORepository;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

/**
 * Created by ericmassip on 1/12/16.
 */
public class RouterRepository extends DAORepository {
    private Logger log = Logger.getLogger(RouterRepository.class);

    public void selectRouter(Router router, int primaryKey) {
        select(router, primaryKey);
        log.info("Router selected: BSSID -> " + router.getBSSID() + ", floor -> " + router.getFloor() + " from id " + primaryKey);
    }

    public List<Router> getAllRouters() {
        log.info("All Routers selected");
        return selectAll(Router.class);
    }

    public Router selectRouterByBSSID(String BSSID) {
        Router router = new Router();
        StringBuilder query = new StringBuilder("SELECT * ");
        query.append("FROM Router ");
        query.append("WHERE Router.BSSID = ?");
        Connection con = getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query.toString());
            preparedStatement.setObject(1, BSSID);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            if (resultSet.next()) {
                setFieldsFromResultSet(resultSet, resultSetMetaData, router);
            }
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("Router with BSSID " + router.getBSSID() + " selected");
        return router;
    }
}
