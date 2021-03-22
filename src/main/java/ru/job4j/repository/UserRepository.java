package ru.job4j.repository;

import ru.job4j.hibernate.Hibernate;
import ru.job4j.model.User;
import ru.job4j.store.UserStore;
import java.util.List;

public class UserRepository implements UserStore, AutoCloseable {
    Hibernate hb = new Hibernate();

    @Override
    public User add(User user) {
        hb.upsert(s -> s.save(user));
        return user;
    }

    @Override
    public User findById(long id) {
        return hb.get(s -> s.get(User.class, id));
    }

    @Override
    public User update(User user) {
        hb.upsert(s -> s.update(user));
        return user;
    }

    @Override
    public void delete(User user) {
        hb.upsert(s -> s.delete(user));
    }

    @Override
    public List<User> all() {
        return hb.get(s -> s.createQuery("from ru.job4j.model.User").list());
    }

    @Override
    public User findByLogin(String login) {
        List users = hb.get(s -> s.createQuery("from ru.job4j.model.User where login = \'" + login + "\'").list());
        return users.size() > 0 ? (User) users.get(0) : null;
    }

    @Override
    public void close() throws Exception {
        hb.close();
    }
}
