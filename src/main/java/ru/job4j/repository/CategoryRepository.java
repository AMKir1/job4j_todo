package ru.job4j.repository;

import ru.job4j.hibernate.Hibernate;
import ru.job4j.model.Category;
import ru.job4j.store.CategoryStore;
import java.util.List;

public class CategoryRepository implements CategoryStore, AutoCloseable {

    private final static Hibernate hb = new Hibernate();

    @Override
    public Category add(Category category) {
        return null;
    }

    @Override
    public Category findById(long id) {
        return hb.get(s -> s.get(ru.job4j.model.Category.class, id));
    }

    @Override
    public Category update(Category category) {
        return null;
    }

    @Override
    public void delete(Category category) {

    }

    @Override
    public List<Category> all() {
        return hb.get(s -> s.createQuery("from ru.job4j.model.Category").list());
    }

    @Override
    public void close() throws Exception {
        hb.close();
    }
}
