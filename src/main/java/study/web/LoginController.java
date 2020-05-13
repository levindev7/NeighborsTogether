package study.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import study.db.UsersDAO;
import study.model.User;

import javax.servlet.http.HttpSession;

//@WebServlet(urlPatterns = {"/user/profile/*", "/login"})  //в параметре задаем обрабатываемые адреса
@Controller
public class LoginController {
    @Autowired
    private UsersDAO users;

    @GetMapping(path = "/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping (path = "/login")
    public String doPost(HttpSession session, // если любой параметр не обязателен (опционален) для некоторых форм, нужно в @RequestParam указать параметр (required = false)
                         @RequestParam("usernameField") String username, // дополнительный параметр если его имя не совпадает с переменной
                         @RequestParam("passwordField") String password) {  // указанное в параметре метода doPost Спринг достанет из запроса
        if (session.getAttribute("verifiedUserName") != null) {
            return "redirect:/";
        }

        User user = users.findUserByLogin(username);
            //user.getHouseGroup().getUsers(); если нужна ленивая коллекция, то получаем её до закрытия менеджера


        if (user != null && password.equals(user.getPassword())) {
            session.setAttribute("verifiedUserName", username); // началло сессеии указывающее признак авторизации
            return "redirect:/"; // редирект в полученный корень проекта //NT_war если введены верные данные
                        // contextPath путь куда деплоится (компиляций кода, выгрузка его на сервер, подтягивание зависимостей) приложение, можно задать в конфиге проекта
        } else {
            return "redirect:/login?login=" + username; // в случае неудачи происходит редирект с введенным ранее логином
        }
    }
}
