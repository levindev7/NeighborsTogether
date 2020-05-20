package study.db;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import study.model.User;
import study.model.UserStatus;

import java.util.List;

@Repository   //на одну сущность один репозиторий! // не нужно тестировать т.к. все делается спрингом (кроме случаев когда используем свои запросы) // есть доп методы
@RepositoryRestResource(collectionResourceRel = "users-api", itemResourceRel = "users-api", path = "users-api") // можно настроить, например изменить путь
public interface UsersRepository extends PagingAndSortingRepository<User, Integer> { // указываем тип объекта и тип его Id
    User findByLogin(@Param("loginName") String login); // важно название метода  // реализация интерфейса создается "на ходу" и методы выполняются по имени и типу
    User findByLoginAndPassword(String login, String password);

    Page<User> findByStatusOrderByLogin(UserStatus status, Pageable page); // разбиение информации на страницы

    @Query("select u from User u where u.bonusScore > 100") // можно искать с запросом
    List<User> findUsersScoreTooBig(int bonusScore);
}
