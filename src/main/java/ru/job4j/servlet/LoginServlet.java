package ru.job4j.servlet;

import ru.job4j.model.User;
import ru.job4j.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if(login.equals("") && password.equals("")) {
            req.setAttribute("error", "Введите логин и пароль!");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        } else {
            if (login.equals("")) {
                req.setAttribute("error", "Введите логин!");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            } else if (password.equals("")) {
                req.setAttribute("error", "Введите пароль!");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            } else {
                UserRepository repository = new UserRepository();
                User currentUser = repository.findByLogin(login);
                if (currentUser != null) {
                    if (currentUser.getPassword().equals(password)) {
                        HttpSession sc = req.getSession();
                        sc.setAttribute("user", currentUser);
                        resp.sendRedirect(req.getContextPath() + "/index.do");
                    } else {
                        req.setAttribute("error", "Не верный пароль");
                        req.getRequestDispatcher("/login.jsp").forward(req, resp);
                    }
                } else {
                    req.setAttribute("error", "Пользователя не существует");
                    req.getRequestDispatcher("/login.jsp").forward(req, resp);
                }
            }
        }
    }

}
