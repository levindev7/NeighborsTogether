package study.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import study.db.UsersDAO;
import study.model.HouseGroup;

@RunWith(SpringJUnit4ClassRunner.class)  // указываем жюниту, что запуск через спринг
@ContextConfiguration(classes = TestConfiguration.class) // указывваем какая используется конфигурация (тестовая)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)   // в тестах для обеспечения независимости исполнения необходимо каждый раз зачищать контекст по умолчанию один контекст classMode один на все классы тестов
public class SpringDAOTest {
    @Autowired
    public UsersDAO users;

    @Test
    public void smokeTest() {
       HouseGroup hG = users.createHouseGroup("test-address");
       users.createUser("login1", "aaa", "12345", hG);
    }
}
