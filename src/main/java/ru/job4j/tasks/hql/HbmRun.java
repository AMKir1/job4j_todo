package ru.job4j.tasks.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            System.out.println("SELECT");
            Query query1 = session.createQuery("from Candidate");
            for (Object st : query1.list()) {
                System.out.println(st);
            }

            System.out.println("SELECT BY ID");
            System.out.println(session.createQuery("from Candidate c where c.id = :id")
                    .setParameter("id", (long) 2)
                    .uniqueResult().toString()
            );

            System.out.println("SELECT BY NAME");
            Query query3 = session.createQuery("from Candidate c  where c.name = :name")
                    .setParameter("name", "Jon");
            for (Object st : query3.list()) {
                System.out.println(st);
            }

            System.out.println("UPDATE");
            session.createQuery("update Candidate c set c.name = :name, c.experience = :experience, c.salary = :salary where c.id = :id")
                    .setParameter("name", "Bob")
                    .setParameter("experience", "9")
                    .setParameter("salary", (double) 100)
                    .setParameter("id", (long) 1)
                    .executeUpdate();
            System.out.println(session.createQuery("from Candidate c  where c.id =" + (long) 1).uniqueResult().toString());

            System.out.println("DELETE BY ID");
            session.createQuery("delete from Candidate c where c.id = :id")
                    .setParameter("id", (long) 3)
                    .executeUpdate();


            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
