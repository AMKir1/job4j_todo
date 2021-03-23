package ru.job4j.servlet;

import ru.job4j.model.User;
import ru.job4j.repository.UserRepository;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/reg"})
public class RegServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login.equals("") && password.equals("")) {
            req.setAttribute("error", "Введите логин и пароль!");
            req.getRequestDispatcher("/reg.jsp").forward(req, resp);
        } else {
            if (login.equals("")) {
                req.setAttribute("error", "Введите логин!");
                req.getRequestDispatcher("/reg.jsp").forward(req, resp);
            } else if (password.equals("")) {
                req.setAttribute("error", "Введите пароль!");
                req.getRequestDispatcher("/reg.jsp").forward(req, resp);
            } else {
                UserRepository repository = new UserRepository();
                User currentUser = repository.findByLogin(login);
                if (currentUser == null) {
                    User user = new User(login, password, login);
                    repository.add(user);
                    HttpSession sc = req.getSession();
                    sc.setAttribute("user", user);
                    resp.sendRedirect(req.getContextPath() + "/index");
                } else {
                    req.setAttribute("error", "Пользователь уже существует");
                    req.getRequestDispatcher("/reg.jsp").forward(req, resp);
                }
            }
        }
    }
}
