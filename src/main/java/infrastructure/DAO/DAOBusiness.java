package infrastructure.DAO;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ericmassip on 11/10/16.
 * DAOBusiness
 */

@SuppressWarnings("StringBufferReplaceableByString")
public class DAOBusiness {

    public String getInsertQuery(Object object) {
        StringBuffer query = new StringBuffer("INSERT INTO ");
        query.append(object.getClass().getSimpleName());
        query.append(" (");
        addFieldsInsertQuery(object, query);
        query.append(")");
        query.append(" VALUES (");
        addInterrogantsInsertQuery(object, query);
        query.append(")");
        return query.toString();
    }

    public String getSelectQuery(Object object) {
        StringBuilder query = new StringBuilder("SELECT * FROM ");
        query.append(object.getClass().getSimpleName());
        query.append(" WHERE id=?");
        return query.toString();
    }

    public String getUpdateQuery(Object object) {
        StringBuffer query = new StringBuffer("UPDATE ");
        query.append(object.getClass().getSimpleName());
        query.append(" SET ");
        addFieldsAndInterrogantsUpdateQuery(object, query);
        query.append(" WHERE id=?");
        return query.toString();
    }

    public String getDeleteQuery(Object object) {
        StringBuilder query = new StringBuilder("DELETE FROM ");
        query.append(object.getClass().getSimpleName());
        query.append(" WHERE id=?");
        return query.toString();
    }

    public String getSelectAllQuery(Class classToLoad) {
        StringBuilder query = new StringBuilder("SELECT * FROM ");
        query.append(classToLoad.getSimpleName());
        return query.toString();
    }

    private void addFieldsInsertQuery(Object object, StringBuffer query) {
        for (Field f : getNonGenericObjectDeclaredFields(object)) {
            query.append(f.getName()).append(",");
        }
        query.deleteCharAt(query.length() - 1);
    }

    private void addInterrogantsInsertQuery(Object object, StringBuffer query) {
        for (Field ignore : getNonGenericObjectDeclaredFields(object)) {
            query.append("?,");
        }
        query.deleteCharAt(query.length() - 1);
    }

    public void addClassFieldsParameters(Object object, PreparedStatement pstm) {
        int i = 1;
        for (Field field : getNonGenericObjectDeclaredFields(object)) {
            try {
                Method method = object.getClass().getMethod(getGetterName(field.getName()));
                Object methodObjectResulted = getMethodObjectResulted(object, method, field);
                pstm.setObject(i, methodObjectResulted);
                i++;
            } catch (NoSuchMethodException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private Object getMethodObjectResulted(Object object, Method method, Field field) {
        Object methodObjectResulted = null;
        try {
            if (field.getType() == Calendar.class) {
                Calendar calendar = (Calendar) method.invoke(object);
                methodObjectResulted = new java.sql.Date(calendar.getTime().getTime());
            } else {
                methodObjectResulted = method.invoke(object);
            }
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return methodObjectResulted;
    }

    public List<Field> getNonGenericObjectDeclaredFields(Object object) {
        List<Field> nonGenericObjectDeclaredFields = new ArrayList<>();
        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.getType() == String.class || field.getType() == Integer.class || field.getType() == int.class || field.getType() == boolean.class || field.getType() == Boolean.class || field.getType() == Double.class || field.getType() == double.class || field.getType() == Calendar.class) {
                nonGenericObjectDeclaredFields.add(field);
            }
        }
        return nonGenericObjectDeclaredFields;
    }

    private String getGetterName(String fieldName) {
        StringBuilder getterName = new StringBuilder("get");
        getterName.append(capitalizeName(fieldName));
        return getterName.toString();
    }

    private String getSetterName(String fieldName) {
        StringBuilder setterName = new StringBuilder("set");
        setterName.append(capitalizeName(fieldName));
        return setterName.toString();
    }

    private String capitalizeName(String name) {
        String capitalizedFieldName;
        capitalizedFieldName = name.substring(0, 1).toUpperCase() + name.substring(1);
        return capitalizedFieldName;
    }

    private void addFieldsAndInterrogantsUpdateQuery(Object object, StringBuffer query) {
        for (Field f : getNonGenericObjectDeclaredFields(object)) {
            query.append(f.getName());
            query.append("=?,");
        }
        query.deleteCharAt(query.length() - 1);
    }

    public void addPrimaryKeyParameter(PreparedStatement pstm, int position, int primaryKey) {
        try {
            pstm.setObject(position, primaryKey);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getPrimaryKeyParameter(Object object) {
        Method method;
        int id = 0;
        try {
            method = object.getClass().getMethod(getGetterName("id"));
            Object methodObjectResulted = method.invoke(object);
            id = Integer.parseInt(methodObjectResulted.toString());
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void setFieldsFromResultSet(ResultSet resultSet, ResultSetMetaData resultSetMetaData, Object object) {
        try {
            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                String columnType = resultSetMetaData.getColumnTypeName(i);
                String columnName = resultSetMetaData.getColumnLabel(i);
                switch (columnType) {
                    case "VARCHAR":
                        String resultString = resultSet.getString(i);
                        if (resultString != null) {
                            setField(resultString, columnName, object);
                        }
                        break;
                    case "INT":
                        int resultInt = resultSet.getInt(i);
                        setField(resultInt, columnName, object);
                        break;
                    case "DOUBLE":
                        double resultDouble = resultSet.getDouble(i);
                        setField(resultDouble, columnName, object);
                        break;
                    case "TINYINT":
                        boolean resultBoolean = resultSet.getBoolean(i);
                        setField(resultBoolean, columnName, object);
                        break;
                    case "DATETIME":
                        Date resultDate = resultSet.getDate(i);
                        Calendar resultCalendar = Calendar.getInstance();
                        resultCalendar.setTime(resultDate);
                        setField(resultCalendar, columnName, object);
                        break;
                    default:
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setField(Object result, String name, Object object) {
        Method method;
        try {
            method = object.getClass().getMethod(getSetterName(name), object.getClass().getDeclaredField(name).getType());
            method.invoke(object, result);
        } catch (NoSuchMethodException | NoSuchFieldException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
