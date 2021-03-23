package ru.job4j.servlet;


import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.model.Category;
import ru.job4j.model.User;
import ru.job4j.repository.CategoryRepository;
import ru.job4j.repository.ItemRepository;
import ru.job4j.model.Item;
import ru.job4j.repository.UserRepository;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@WebServlet(urlPatterns = {"/additem"})
public class PostItemServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ItemRepository repository = new ItemRepository();
        UserRepository userRepository = new UserRepository();
        CategoryRepository categoryRepository = new CategoryRepository();

        User currentUser = userRepository.findByLogin(req.getParameter("user"));
        long id = Long.parseLong(req.getParameter("id"));
        List<String> categories = new ArrayList<>();
        if(req.getParameter("categories") != null) {
            categories = List.of(req.getParameter("categories").split(""));
        }

        if(id == 0) {
            Item item = new Item(req.getParameter("desc"), new Timestamp(System.currentTimeMillis()), false);
            if(currentUser != null) {
                item.setUser(currentUser);
            }

            if (categories.size() > 0) {
                for(String c : categories) {
                    item.addCategory(categoryRepository.findById(Long.parseLong(c)));
                }
            }

            repository.add(item);
        } else {
            Item item = repository.findById(Long.parseLong(req.getParameter("id")));
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
            if (categories.size() > 0) {
                for(String c : categories) {
                    item.addCategory(categoryRepository.findById(Long.parseLong(c)));
                }
            }
            repository.update(item);
        }
    }
}
