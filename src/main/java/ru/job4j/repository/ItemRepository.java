package ru.job4j.repository;

import org.hibernate.query.Query;
import ru.job4j.hibernate.Hibernate;
import ru.job4j.model.Category;
import ru.job4j.model.Item;
import ru.job4j.store.ItemStore;

import java.util.ArrayList;
import java.util.List;

public class ItemRepository implements ItemStore, AutoCloseable {

    private final static Hibernate hb = new Hibernate();

    @Override
    public Item add(Item item) {
        hb.upsert(s -> s.save(item));
        return item;
    }

    @Override
    public Item findById(long id) {
        return hb.get(s -> s.get(Item.class, id));
    }

    @Override
    public Item update(Item item) {
        hb.upsert(s -> s.update(item));
        return item;
    }

    @Override
    public void delete(Item item) {
        hb.upsert(s -> s.delete(item));
    }

    @Override
    public List<Item> all() {
        return hb.get(s -> s.createQuery("select distinct i from ru.job4j.model.Item i", ru.job4j.model.Item.class).list());
    }

    @Override
    public List<Item> allNotDoneItems() {
        return hb.get(s -> s.createQuery("select distinct i from ru.job4j.model.Item i where i.done = false", ru.job4j.model.Item.class).list());
    }

    @Override
    public void close() throws Exception {
        hb.close();
    }
}
