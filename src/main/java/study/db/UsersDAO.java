package study.db;

import com.sun.istack.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import study.model.Flat;
import study.model.HouseGroup;
import study.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Repository
public class UsersDAO {

    @PersistenceContext //вместо @Autowired помечаем manager, который будет создавать @Transactional транзакции
    private EntityManager manager; // и через конструктор инъекция уже недоступна, только через поле

    /*аннотация @Transactional для метода работы с базой заменяет конструкцию -> manager.getTransaction.begin -> try{...persist} -> catch{...rollback}->...commit

    при этом во время исполнения создается прокси класс наследующий наш класс, он переопределяет все @Transactional методы и добавляет обертку try/catch
    class UsersDAOWrapper extends UsersDAO {
    }

    при этом методы и классы под @Transactional не могут быть final
    * */
    @Transactional
    public HouseGroup createHouseGroup(String houseAddress) {
        HouseGroup houseGroup = new HouseGroup(houseAddress);
        manager.persist(houseGroup);
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

    @Transactional
    public User createUser(String login, String password, String telephoneNumber, HouseGroup houseGroup) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setTelephoneNumber(telephoneNumber);
        user.setHouseGroup(houseGroup);

        manager.persist(user);

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

    @Transactional
    public void changeUserTelephone(User user, String newTelephoneNumber) {
        user.setTelephoneNumber(newTelephoneNumber);
    }

    @Transactional
    public void logOutHouseGroupOldUsers(Date dateBefore, HouseGroup logOutHouseGroup) {
            manager.createQuery("UPDATE User set houseGroup = :houseGroup " +
                    "where registrationDate < :before")
                    .setParameter("houseGroup", logOutHouseGroup)
                    .setParameter("before", dateBefore)
                    .executeUpdate();
    }

    @Transactional
    public Flat createFlat(String flatNumber, HouseGroup houseGroup, User user) {
        Flat flat = new Flat();
        flat.setFlatNumber(flatNumber);
        flat.setHouseGroupFlats(houseGroup);
        flat.setUser(user);

        manager.persist(flat);

        return flat;
    }

}
