package study.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import study.db.UsersDAO;
import study.model.HouseGroup;
import study.model.User;
import study.web.LoginController;
import study.web.WebConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class, WebConfiguration.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@WebAppConfiguration
public class LoginControllerTest {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UsersDAO users;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build(); //имитация запросов сервера
    }

    @Test
    public void loginFormViewTest() throws Exception {
        mockMvc.perform(   //запрос на получение login формы от незалогиненного пользователя
                MockMvcRequestBuilders.get("/login")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("login"))
                .andReturn();
    }

    @Test
    public void loginFormViewWithSessionTest() throws Exception {
        mockMvc.perform(   //запрос на получение login формы когда есть сессия
                MockMvcRequestBuilders.get("/login")
                .sessionAttr(LoginController.VERIFIED_USER_NAME_ATTRIBUTE, "test")  // передаем аттрибут сессии
        ).andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andReturn();
    }

    @Test
    public void loginFormValidTest() throws Exception {
        HouseGroup houseGroup = users.createHouseGroup("test-address");
        User user = users.createUser("test-login", "123", "12345", houseGroup);

        mockMvc.perform(   //запрос на получение login формы когда есть сессия
                MockMvcRequestBuilders.post("/login")
                        .param("usernameField", "test-login")
                        .param("passwordField", "123")// передаем параметры формы
        ).andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/")) // проверка редиректа на нужное место
                .andReturn();
    }

    @Test
    public void loginFormInvalidTest() throws Exception {
        HouseGroup houseGroup = users.createHouseGroup("test-address");
        User user = users.createUser("test-login", "123", "12345", houseGroup);

        mockMvc.perform(   //запрос на получение login формы когда есть сессия
                MockMvcRequestBuilders.post("/login")
                        .param("usernameField", "invalid-login")
                        .param("passwordField", "123")// передаем параметры формы
        ).andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/login?login=invalid-login")) // проверка редиректа на нужное место
                .andReturn();
    }

    @Test
    public void loginFormAlreadyLoggedInTest() throws Exception {
        HouseGroup houseGroup = users.createHouseGroup("test-address");
        User user = users.createUser("test-login", "123", "12345", houseGroup);

        mockMvc.perform(   //запрос на получение login формы когда есть сессия
                MockMvcRequestBuilders.post("/login")
                        .param("usernameField", "test-login")
                        .param("passwordField", "123")// передаем параметры формы
                .sessionAttr(LoginController.VERIFIED_USER_NAME_ATTRIBUTE, "another-login") // атрибут сессии
        ).andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/")) // проверка редиректа на нужное место
                .andReturn();
    }
}
