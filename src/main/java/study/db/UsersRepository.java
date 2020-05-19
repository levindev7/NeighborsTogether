package study.db;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import study.model.User;
import study.model.UserStatus;

import java.util.List;

@Repository   //на одну сущность один репозиторий! // не нужно тестировать т.к. все делается спрингом (кроме случаев когда используем свои запросы) // есть доп методы
public interface UsersRepository extends CrudRepository<User, Integer> { // указываем тип объекта и тип его Id
    User findByLogin(String login); // важно название метода  // реализация интерфейса создается "на ходу" и методы выполняются по имени и типу
    User findByLoginAndPassword(String login, String password);

    List<User> findByStatusOrderByLogin(UserStatus status); // поиск списка пользователей по статуса с сортировкой по логину

    @Query("select u from User u where u.bonusScore > 100") // можно искать с запросом
    List<User> findUsersScoreTooBig(int bonusScore);
}
