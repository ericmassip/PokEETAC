package infrastructure.DAO;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by ericmassip on 10/10/16.
 * DAOfolder
 */
public class DAORepository extends DAOBusiness implements DAOInterface {

    public Connection getConnection() {
        Connection con = null;
        String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//        String DB_URL = "jdbc:mysql://localhost:3306/PokEETAC_Testing";
//        String DB_URL = "jdbc:mysql://localhost:3306/PokEETAC";
        String DB_URL = "jdbc:mysql://147.83.7.208:3306/PokEETAC";
//        String DB_URL = "jdbc:mysql://sql7.freemysqlhosting.net/sql7153567";
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "Mazinger82");
//        properties.setProperty("user", "root");
//        properties.setProperty("password", "erinHo10");
//        properties.setProperty("user", "sql7153567");
//        properties.setProperty("password", "u3QKa3ktGq");
        properties.setProperty("useSSL", "false");
        properties.setProperty("serverTimezone", "UTC");
        try{
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, properties);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public void insert(Object object) {
        String query = getInsertQuery(object);
        Connection con = getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            addClassFieldsParameters(object, preparedStatement);
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                setField(generatedKeys.getInt(1), "id", object);
            }
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void select(Object object, int primaryKey) {
        String query = getSelectQuery(object);
        Connection con = getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            int position = 1;
            addPrimaryKeyParameter(preparedStatement, position, primaryKey);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            while (resultSet.next()) {
                setFieldsFromResultSet(resultSet, resultSetMetaData, object);
            }
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Object object) {
        String query = getUpdateQuery(object);
        Connection con = getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            addClassFieldsParameters(object, preparedStatement);
            int primaryKey = getPrimaryKeyParameter(object);
            List<Field> nonObjectDeclaredFields = getNonGenericObjectDeclaredFields(object);
            int position = (nonObjectDeclaredFields.size() + 1);
            addPrimaryKeyParameter(preparedStatement, position, primaryKey);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Object object) {
        String query = getDeleteQuery(object);
        Connection con = getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            int position = 1;
            int primaryKey = getPrimaryKeyParameter(object);
            addPrimaryKeyParameter(preparedStatement, position, primaryKey);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List selectAll(Class classToLoad) {
        List<Object> objects = new ArrayList<>();
        String query = getSelectAllQuery(classToLoad);
        Connection con = getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            while (resultSet.next()) {
                Object newObject = classToLoad.newInstance();
                setFieldsFromResultSet(resultSet, resultSetMetaData, newObject);
                objects.add(newObject);
            }
            preparedStatement.close();
            con.close();
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return objects;
    }
}
