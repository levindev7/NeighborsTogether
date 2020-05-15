package study.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import study.db.UsersDAO;

@Component
public class StartupListener {
    @Autowired
    public UsersDAO users;


    @EventListener
    @Transactional // вызов транзакшнл метода из другого транзакшнл метода позволяет делать вложенные транзакции (когда они должны быть связаны)
    public void applicationStarted(ContextRefreshedEvent event) { // при появлении события с типом указанным в параметре метод отмеченный как @EventListener автоматически запускается
        if (users.findHouseGroupByAddress("test") == null) {
            users.createHouseGroup("test");
        }
    }
}
