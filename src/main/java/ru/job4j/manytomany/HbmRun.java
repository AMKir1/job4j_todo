package ru.job4j.manytomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmRun {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

//            Book bookASP = Book.of("Евгений Онегин");
//            Book bookAPCH = Book.of("Человек в футляре");
//            Book bookAMK = Book.of("Мемуары Кирилловых А.М.");
//            Book bookAll = Book.of("Книга, которую написали все");
//            Book bookTWO = Book.of("Книга, которую написали двое");
//
//            Author asp  = Author.of("А.С. Пушкин");
//            asp.addBook(bookASP);
//            asp.addBook(bookAll);
//            asp.addBook(bookTWO);
//
//            Author apch  = Author.of("А.П. Чехов");
//            apch.addBook(bookAPCH);
//            apch.addBook(bookAll);
//
//            Author amk  = Author.of("А.М. Кирилловых");
//            amk.addBook(bookAMK);
//            amk.addBook(bookAll);
//            amk.addBook(bookTWO);
//
//            session.persist(asp);
//            session.persist(apch);
//            session.persist(amk);

            Author author = session.get(Author.class, (long) 3);
            session.remove(author);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

}
