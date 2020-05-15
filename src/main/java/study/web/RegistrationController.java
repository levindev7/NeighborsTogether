package study.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import study.db.UsersDAO;
import study.model.HouseGroup;

import java.util.List;

//@WebServlet(urlPatterns = "/user/register")
@Controller
public class RegistrationController {
    @Autowired
    private UsersDAO users;

    @GetMapping(path = "/user/register") // пусть можно указать как для метода, так и для всего класса
    public String getRegistrationForm(ModelMap model) {
        List<HouseGroup> houseGroups = users.findAllGroups(); // запрашиваем список групп домов (адресов)

        RegistrationForm form = new RegistrationForm(); // заполняем форму
        form.setLogin("");
        form.setPassword("");
        form.setTelephoneNumber("");
        form.setHouseGroups(houseGroups);
        form.setSelectedHouseGroup(houseGroups.get(0));

        model.addAttribute("form", form); // атрибут для jsp (подключается через jsp:useBean)

        return "register";  // возвращаем имя вью части, которое нужно связать c помощью @bean viewResolver в WebConfig

    }

    @PostMapping(path = "/user/register")
    // @Transactional нужно было бы отметить если бы совершалось несколько связанных действий
    public String processRegistrationForm(@RequestParam String login,
                                        @RequestParam String password,
                                        @RequestParam String telephoneNumber,
                                        @RequestParam("houseGroup") String houseAddress) {  // Spring сам достанет параметры из реквеста

                    HouseGroup houseGroup = users.findHouseGroupByAddress(houseAddress);
            if (houseGroup == null) {
                throw new IllegalStateException("Address: " + houseAddress + " wasn't found");
            }

            users.createUser(login, password, telephoneNumber, houseGroup);

            return "redirect:/login?login=" + login; // редирект на логин
    }
}
