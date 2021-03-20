package ru.job4j.servlet;

import ru.job4j.hibernate.Hibernate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns ={ "/index.do"})
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Hibernate hb = new Hibernate();
        req.setAttribute("items", hb.allItems());
        req.getRequestDispatcher("/").forward(req, resp);
    }
}
