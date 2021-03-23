package ru.job4j.servlet;

import ru.job4j.repository.CategoryRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/index"})
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CategoryRepository categoryRepository = new CategoryRepository();
        if(req.getSession(false ) == null) {
            req.setAttribute("user", req.getSession().getAttribute("user"));
        }
        req.setAttribute("categories", categoryRepository.all());
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
