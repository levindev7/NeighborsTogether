package study.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.db.UsersRepository;
import study.model.User;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsersRestController {
    @Autowired
    private UsersRepository repository;


    @GetMapping("/api/users")  // если среди сущностей есть ленивые списки (или циклические жадные загрузки), то чтобы они загрузились должна поддерживаться hibernate сессия
    public List<User> findUsers() {
        ArrayList<User> allUsers = new ArrayList<>();

        for (User user : repository.findAll()) {
            allUsers.add(user);
        }
        return allUsers;
    }
}
