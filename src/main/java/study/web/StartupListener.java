package study.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import study.db.UsersDAO;

@Component
public class StartupListener {
    @Autowired
    public UsersDAO users;


    @EventListener
    public void applicationStarted(ContextRefreshedEvent event) { // при появлении события с типом указанным в параметре метод отмеченный как @EventListener автоматически запускается
        if (users.findHouseGroupByAddress("test") == null) {
            users.createHouseGroup("test");
        }
    }
}
