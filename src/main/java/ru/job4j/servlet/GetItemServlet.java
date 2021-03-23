package ru.job4j.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.model.Item;
import ru.job4j.repository.ItemRepository;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/getitems"})
public class GetItemServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("json");
        ItemRepository repository = new ItemRepository();
        ObjectMapper mapper = new ObjectMapper();
        if (req.getParameter("done").equals("true")) {
            resp.getWriter().write(mapper.writeValueAsString(repository.all()));
        } else {
            resp.getWriter().write(mapper.writeValueAsString(repository.allNotDoneItems()));
        }
    }
}
