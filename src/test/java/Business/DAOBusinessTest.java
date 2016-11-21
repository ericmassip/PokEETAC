package Business;

import Entity.User;
import Infrastructure.DAO.DAOBusiness;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by ericmassip on 12/11/16.
 */
public class DAOBusinessTest {
    DAOBusiness daoBusiness = new DAOBusiness();
    User eric;

    @Before
    public void setUp() throws Exception {
        eric = new User();
        eric.setUsername("ericmassip");
        eric.setPassword("1234");
    }

    @After
    public void tearDown() throws Exception {
        this.eric = null;
    }

    @Test
    public void getInsertQuery() throws Exception {
        assertEquals("INSERT INTO " + eric.getClass().getSimpleName() + " (id,username,password,level,experiencePoints,isAdmin) VALUES (?,?,?,?,?,?)", daoBusiness.getInsertQuery(eric));
    }

    @Test
    public void getSelectQuery() throws Exception {
        assertEquals("SELECT * FROM " + eric.getClass().getSimpleName() + " WHERE id=?", daoBusiness.getSelectQuery(eric));
    }

    @Test
    public void getUpdateQuery() throws Exception {
        assertEquals("UPDATE " + eric.getClass().getSimpleName() + " SET id=?,username=?,password=?,level=?,experiencePoints=?,isAdmin=? WHERE id=?", daoBusiness.getUpdateQuery(eric));
    }

    @Test
    public void getDeleteQuery() throws Exception {
        assertEquals("DELETE FROM " + eric.getClass().getSimpleName() + " WHERE id=?", daoBusiness.getDeleteQuery(eric));
    }

    @Test
    public void getSelectAllQuery() throws Exception {
        assertEquals("SELECT * FROM " + eric.getClass().getSimpleName(), daoBusiness.getSelectAllQuery(User.class));
    }

    @Test
    public void getPrimaryKeyParameter() throws Exception {
        assertEquals(0, daoBusiness.getPrimaryKeyParameter(eric));
    }
}