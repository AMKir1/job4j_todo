package ru.job4j.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.hibernate.Hibernate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/getitems"})
public class GetItemServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Hibernate hb = new Hibernate();
        ObjectMapper mapper = new ObjectMapper();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("json");
        if(req.getParameter("done").equals("true")) {
            resp.getWriter().write(mapper.writeValueAsString(hb.allItems()));
        } else {
            resp.getWriter().write(mapper.writeValueAsString(hb.allNotDoneItems()));
        }
    }
}
