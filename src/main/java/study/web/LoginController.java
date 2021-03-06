package study.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import study.db.UsersRepository;
import study.model.User;

import javax.servlet.http.HttpSession;

//@WebServlet(urlPatterns = {"/user/profile/*", "/login"})  //в параметре задаем обрабатываемые адреса
@Controller
public class LoginController {
    public static final String VERIFIED_USER_NAME_ATTRIBUTE = "verifiedUserName";
    @Autowired
    private UsersRepository users;

    @GetMapping(path = "/login")
    public String loginPage(@RequestParam(required = false) String login,
                            HttpSession session) {
        if (session.getAttribute(VERIFIED_USER_NAME_ATTRIBUTE) != null) {  // если пользователь уже залогинен, форма логина ему не отображается
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping (path = "/login")
    public String processLoginForm(HttpSession session, // если любой параметр не обязателен (опционален) для некоторых форм, нужно в @RequestParam указать параметр (required = false)
                         @RequestParam("usernameField") String username, // дополнительный параметр если его имя не совпадает с переменной
                         @RequestParam("passwordField") String password) {  // указанное в параметре метода doPost Спринг достанет из запроса
        if (session.getAttribute(VERIFIED_USER_NAME_ATTRIBUTE) != null) {
            return "redirect:/";
        }

        User user = users.findByLogin(username);
            //user.getHouseGroup().getUsers(); если нужна ленивая коллекция, то получаем её до закрытия менеджера


        if (user != null && password.equals(user.getPassword())) {
            session.setAttribute(VERIFIED_USER_NAME_ATTRIBUTE, username); // началло сессеии указывающее признак авторизации
            return "redirect:/"; // редирект в полученный корень проекта //NT_war если введены верные данные
                        // contextPath путь куда деплоится (компиляций кода, выгрузка его на сервер, подтягивание зависимостей) приложение, можно задать в конфиге проекта
        } else {
            return "redirect:/login?login=" + username; // в случае неудачи происходит редирект с введенным ранее логином
        }
    }
}
