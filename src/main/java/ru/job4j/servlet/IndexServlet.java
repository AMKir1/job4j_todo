package ru.job4j.servlet;

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
        if(req.getSession(false ) == null) {
            req.setAttribute("user", req.getSession().getAttribute("user"));
        }
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
