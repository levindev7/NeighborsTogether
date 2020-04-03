package study.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import study.UsersDAO;
import study.model.HouseGroup;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import static org.junit.Assert.*;

public class UsersDAOTest {
    private EntityManagerFactory factory;
    private EntityManager manager;
    private UsersDAO users;

    @Before
    public void connect() {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        manager = factory.createEntityManager();
        users = new UsersDAO(manager);

    }

    @After
    public void close () {
        if (manager != null) {
            manager.close();
        }
        if (factory != null) {
            factory.close();
        }
    }

    @Test
    public void createHouseGroup() {
        HouseGroup created = users.createHouseGroup("test-address1");
        assertNotNull(created);

        try {
            users.createHouseGroup("test-address1");
            fail("createHouseGroup should fail for the same houseGroupAddress");
        } catch (PersistenceException expected) {
        }
    }

    @Test
    public void findAllGroups() {
    }

    @Test
    public void findHouseGroupByAddress() {
    }

    @Test
    public void createUser() {
    }

    @Test
    public void findAllUsers() {
    }

    @Test
    public void findUserByLogin() {
    }

    @Test
    public void createFlat() {
    }
}