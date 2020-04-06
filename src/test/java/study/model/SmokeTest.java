package study.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import study.model.Flat;
import study.model.HouseGroup;
import study.model.User;
import study.model.UserStatus;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.Assert.*;

public class SmokeTest {
    private EntityManagerFactory factory;
    private EntityManager manager;

    @Before
    public void connect() {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        manager = factory.createEntityManager();

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
    public void createUserTest() {
        User user1 = new User();
        user1.setLogin("test-user");
        user1.setPassword("123");
        user1.setTelephoneNumber("12345");

        HouseGroup houseGroup = new HouseGroup();
        houseGroup.setHouseAddress("Minsk-test");

        Flat flat1 = new Flat();
        flat1.setFlatNumber("13");

        user1.setStatus(UserStatus.GUEST);
        user1.setHouseGroup(houseGroup);
        flat1.setUser(user1);
        flat1.setHouseGroupFlats(houseGroup);



        manager.getTransaction().begin();
        manager.persist(houseGroup);
        manager.persist(user1);
        manager.persist(flat1);

        User foundUser = manager.find(User.class, user1.getId());
        Assert.assertNotNull(foundUser);
        Assert.assertSame(foundUser, user1);

        manager.getTransaction().commit();

        manager.refresh(foundUser);
    }

    @Test
    public void testSearchNative() {
        createUserTest();

        manager.createNativeQuery("SELECT * from Users").getResultList();
        // Native запрос по имени таблицы (@Table (name = "Users))
    }

    @Test
    public void testSearch() {
        createUserTest();

        List<User> foundUser = manager.createQuery("SELECT u from User u WHERE u.houseGroup.houseAddress = :houseGroup", User.class)
                .setParameter("houseGroup", "Minsk-test")
                .getResultList();
        // HQL запрос по названию сущности (Public class User)

        assertEquals(1, foundUser.size());
        assertEquals("test-user", foundUser.get(0).getLogin());

    }

    @Test
    public void testSearchGroup() {
        createUserTest();

        List<HouseGroup> foundHouseGroups = manager.createQuery("SELECT u.houseGroup from User u WHERE u.status = :status", HouseGroup.class)
                .setParameter("status", UserStatus.GUEST)
                .getResultList();

        assertEquals(1, foundHouseGroups.size());
        assertEquals("Minsk-test", foundHouseGroups.get(0).getHouseAddress());

    }

    /*java.lang.IllegalArgumentException: Type specified for TypedQuery [study.study.model.Flat] is incompatible with query return type [interface java.util.Collection
    @Test
    public void testSearchFlat() {
        createUserTest();

        List<Flat> foundFlat = manager.createQuery("SELECT u.flats from User u WHERE u.flats = :flatNumber", Flat.class)
                .setParameter("flatNumber", "13")
                .getResultList();

        assertEquals(1, foundFlat.size());
        assertEquals("13", foundFlat.get(0).getFlatNumber());

    }*/
}
