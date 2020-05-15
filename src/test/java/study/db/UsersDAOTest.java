package study.db;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import study.db.UsersDAO;
import study.model.HouseGroup;
import study.tests.TestConfiguration;

import javax.persistence.*;

import static org.junit.Assert.*;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

public class UsersDAOTest {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UsersDAO users;

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