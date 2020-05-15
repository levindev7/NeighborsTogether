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
import study.web.RegistrationForm;
import study.web.WebConfiguration;

import java.util.Arrays;
import java.util.Collections;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class, WebConfiguration.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@WebAppConfiguration
public class RegistrationControllerTest {
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
    public void testRegistrationForm() throws Exception {
        HouseGroup houseGroup = users.findHouseGroupByAddress("test"); // группа уже создается StartupListener, её используем или исключаем класс StLis

        RegistrationForm form = new RegistrationForm();
        form.setLogin("");
        form.setPassword("");
        form.setTelephoneNumber("");
        form.setHouseGroups(Collections.singletonList(houseGroup));
        form.setSelectedHouseGroup(houseGroup);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/user/register")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("form", form))
                .andExpect(MockMvcResultMatchers.view().name("register"))
                .andReturn();
    }
}
