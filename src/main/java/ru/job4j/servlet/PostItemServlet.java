package ru.job4j.servlet;

import ru.job4j.hibernate.Hibernate;
import ru.job4j.model.Item;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;


@WebServlet(urlPatterns = {"/additem"})
public class PostItemServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Hibernate hb = new Hibernate();
        long id = Long.parseLong(req.getParameter("id"));
        if(id == 0) {
            hb.add(new Item(req.getParameter("desc"), new Timestamp(System.currentTimeMillis()), false));
        } else {
            Item item = hb.findById(Long.parseLong(req.getParameter("id")));
            if(req.getParameter("desc") != null) {
                item.setDescription(req.getParameter("desc"));
            }
            if(req.getParameter("created") == null) {
                item.setCreated(new Timestamp(System.currentTimeMillis()));
            }
            if(req.getParameter("done").equals("true")) {
                item.setDone(true);
            } else {
                item.setDone(false);
            }
            hb.update(item);
        }
    }
}
