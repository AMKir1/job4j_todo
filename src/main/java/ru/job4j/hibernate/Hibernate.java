package ru.job4j.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.model.Item;
import ru.job4j.store.Store;


import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

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
        this.upsert(s -> s.save(item));
        return item;
    }

    @Override
    public Item findById(long id) {
        return this.get(s -> s.get(Item.class, id));
    }

    @Override
    public Item update(Item item) {
        this.upsert(s -> s.update(item));
        return item;
    }

    @Override
    public void delete(Item item) {
        this.upsert(s -> s.delete(item));
    }

    @Override
    public List<Item> allItems() {
        return this.get(s -> s.createQuery("from ru.job4j.model.Item").list());
    }

    @Override
    public List<Item> allNotDoneItems() {
        return this.get(s -> s.createQuery("from ru.job4j.model.Item where done = false order by created desc").list());
    }

    private <T> T get(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    private void upsert(final Consumer<Session> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            command.accept(session);
            tx.commit();

        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

}
