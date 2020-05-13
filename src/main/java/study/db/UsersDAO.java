package study.db;

import com.sun.istack.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import study.model.Flat;
import study.model.HouseGroup;
import study.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Repository
public class UsersDAO {
    private EntityManager manager;

    //!!!!!!!!!!!!!!!!!!почитать про аспекты и прокси
    @Autowired  // переделать через конструктор, для обеспечения проверок и гарантированного создания объекта, привязка через поле только в тестах и при возникновении циклических зависимостей (когда создаваемые объекты ссылаются друг на друга)
    public UsersDAO(@Qualifier("defaultManager") EntityManager manager) {
        this.manager = manager;
        Objects.requireNonNull(manager, "EntityManager shouldn't be null");
    }

    public HouseGroup createHouseGroup(String houseAddress) {
        HouseGroup houseGroup = new HouseGroup(houseAddress);

        // Если persist не пройдет, то транзакция будет в подвешенном состоянии, поэтому в этом случае обязательно после begin должен следовать откат rollback
        manager.getTransaction().begin();
        try {
            manager.persist(houseGroup);
        } catch (Throwable cause) {
            manager.getTransaction().rollback();
            throw cause;
        }
        manager.getTransaction().commit();

        return houseGroup;
    }

    public List<HouseGroup> findAllGroups() {
        return manager.createQuery("SELECT h from HouseGroup h", HouseGroup.class).getResultList();
    }

    @Nullable
    public HouseGroup findHouseGroupByAddress(String houseAddress) {
        try {
            return manager.createQuery("SELECT h from HouseGroup h WHERE h.houseAddress = :houseAddressToSearch", HouseGroup.class)
                    .setParameter("houseAddressToSearch", houseAddress)
                    .getSingleResult();
        } catch (NoResultException cause) {
            return null;
        }
    }

    public User createUser(String login, String password, String telephoneNumber, HouseGroup houseGroup) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setTelephoneNumber(telephoneNumber);
        user.setHouseGroup(houseGroup);


        manager.getTransaction().begin();
        try {
            manager.persist(user);
        } catch (Throwable cause) {
            manager.getTransaction().rollback();
            throw cause;
        }
        manager.getTransaction().commit();

        return user;
    }

    public List<User> findAllUsers() {
        return manager.createQuery("SELECT u from User u", User.class).getResultList();
    }

    @Nullable
    public User findUserByLogin(String login) {
        try {
            return manager.createQuery("SELECT u from User u WHERE u.login = :loginToSearch", User.class)
                    .setParameter("loginToSearch", login)
                    .getSingleResult();
        } catch (NoResultException cause) {
            return null;
        }
    }

    public void changeUserTelephone(User user, String newTelephoneNumber) {
        manager.getTransaction().begin();
        user.setTelephoneNumber(newTelephoneNumber);
        manager.getTransaction().commit();
    }

    public void logOutHouseGroupOldUsers(Date dateBefore, HouseGroup logOutHouseGroup) {
        manager.getTransaction().begin();
        try {
            manager.createQuery("UPDATE User set houseGroup = :houseGroup " +
                    "where registrationDate < :before")
                    .setParameter("houseGroup", logOutHouseGroup)
                    .setParameter("before", dateBefore)
                    .executeUpdate();
        } catch (Throwable cause) {
            manager.getTransaction().rollback();
            throw cause;
        }
        manager.getTransaction().commit();
    }



    public Flat createFlat(String flatNumber, HouseGroup houseGroup, User user) {
        Flat flat = new Flat();
        flat.setFlatNumber(flatNumber);
        flat.setHouseGroupFlats(houseGroup);
        flat.setUser(user);

        manager.getTransaction().begin();
        try {
            manager.persist(flat);
        } catch (Throwable cause) {
            manager.getTransaction().rollback();
            throw cause;
        }
        manager.getTransaction().commit();

        return flat;
    }

}
