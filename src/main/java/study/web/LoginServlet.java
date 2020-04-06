package study.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/user/profile/*", "/login"})  //в параметре задаем обрабатываемые адреса
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("verifiedUserName") != null) {
            resp.sendRedirect(req.getContextPath()); // если пользователь авторизован, то при овторной попытке авторизоваться произойдет редирект
            return;
        }

        String username = req.getParameter("usernameField");
        String password = req.getParameter("passwordField");

        if (username != null && password != null && username.equals("test") && password.equals("123")) {
            req.getSession().setAttribute("verifiedUserName", username); // началло сессеии указывающее признак авторизации
            resp.sendRedirect(req.getContextPath()); // редирект в полученный корень проекта //NT_war
        } else {
            resp.sendRedirect("login?login=" + username); // в случае неудачи происходит редирект с введенным ранее логином
        }
    }
}
