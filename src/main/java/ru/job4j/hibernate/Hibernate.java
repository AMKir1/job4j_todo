package ru.job4j.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.model.Item;
import ru.job4j.store.Store;


import java.util.List;

public class Hibernate implements Store, AutoCloseable {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    @Override
    public Item add(Item item) {
        Session s = sf.openSession();
        s.beginTransaction();
        s.save(item);
        s.getTransaction().commit();
        s.close();
        return item;
    }

    @Override
    public Item findById(long id) {
        Session s = sf.openSession();
        s.beginTransaction();
        Item item = s.get(Item.class, id);
        s.getTransaction().commit();
        s.close();
        return item;
    }

    @Override
    public Item update(Item item) {
        Session s = sf.openSession();
        s.beginTransaction();
        s.update(item);
        s.getTransaction().commit();
        s.close();
        return item;
    }

    @Override
    public void delete(Item item) {
        Session s = sf.openSession();
        s.beginTransaction();
        s.delete(item);
        s.getTransaction().commit();
        s.close();
    }

    @Override
    public List<Item> allItems() {
        Session s = sf.openSession();
        s.beginTransaction();
        List items = s.createQuery("from ru.job4j.model.Item order by created desc").list();
        s.getTransaction().commit();
        s.close();
        return items;
    }

    @Override
    public List<Item> allNotDoneItems() {
        Session s = sf.openSession();
        s.beginTransaction();
        List doneitems = s.createQuery("from ru.job4j.model.Item where done = false order by created desc").list();
        s.getTransaction().commit();
        s.close();
        return doneitems;
    }

}
