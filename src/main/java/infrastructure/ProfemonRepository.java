package infrastructure;

import entity.Profemon;
import infrastructure.DAO.DAORepository;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
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

    public List<Profemon> getProfemonsFilteredBy(String filterBy) {
        List<Profemon> profemons = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * ");
        query.append("FROM Profemon ");
        query.append("WHERE id LIKE ? OR name LIKE ?");
        Connection con = getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query.toString());
            preparedStatement.setObject(1, "%" + filterBy + "%");
            preparedStatement.setObject(2, "%" + filterBy + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            while (resultSet.next()) {
                Profemon profemon = new Profemon();
                setFieldsFromResultSet(resultSet, resultSetMetaData, profemon);
                profemons.add(profemon);
            }
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("All Profemons selected filtered by " + filterBy);
        return profemons;
    }
}
