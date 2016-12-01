package Infrastructure;

import Entity.Capturado;
import Entity.Profemon;
import Entity.User;
import Infrastructure.DAO.DAORepository;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ericmassip on 1/12/16.
 */
public class CapturadoRepository extends DAORepository {
    private Logger log = Logger.getLogger(CapturadoRepository.class);

    public void insertCapturado(Capturado capturado) {
        insert(capturado);
        log.info("Capturado added: profemon id " + capturado.getIdProfemon());
    }

    public void selectCapturado(Capturado capturado, int primaryKey) {
        select(capturado, primaryKey);
        log.info("Capturado selected: from id " + primaryKey + " with profemon id " + capturado.getIdProfemon());
    }

    public List<Capturado> getAllCapturados() {
        log.info("All Capturados selected");
        return selectAll(Capturado.class);
    }

    public void updateCapturado(Capturado capturado) {
        update(capturado);
        log.info("Capturado updated: capturado id " + capturado.getId());
    }

    public void deleteCapturado(Capturado capturado) {
        delete(capturado);
        log.info("Capturado deleted: capturado id " + capturado.getId());
    }

    public void setUserProfemonsCapturadosFromDatabase(User user) {
        List<Profemon> userProfemons = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT Profemon.id, Profemon.name, Profemon.initialLevel ");
        query.append("FROM Profemon ");
        query.append("LEFT JOIN Capturado ON Profemon.id = Capturado.idProfemon ");
        query.append("WHERE Capturado.idUser = ?");
        Connection con = getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query.toString());
            preparedStatement.setObject(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            while (resultSet.next()) {
                Profemon profemon = new Profemon();
                setFieldsFromResultSet(resultSet, resultSetMetaData, profemon);
                userProfemons.add(profemon);
            }
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        user.setProfemons(userProfemons);
        log.info(user.getUsername() + "'s profemons all set from database");
    }
}
