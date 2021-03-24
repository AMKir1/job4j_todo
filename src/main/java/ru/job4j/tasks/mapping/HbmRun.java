package ru.job4j.tasks.many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.tasks.mapping.Car;
import ru.job4j.tasks.mapping.Driver;

import java.util.HashSet;
import java.util.Set;


public class HbmRun {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            String[] carsData = {"BMW", "MINI"};
            String[] driversData = {"Alex", "Andrew"};

            Set<Car> cars = new HashSet<>();

            for (String car : carsData) {
                cars.add(Car.of(car));
            }

            for (String driver : driversData) {
                Driver d = Driver.of(driver);
                d.setCars(cars);
                session.persist(d);
            }

            session.getTransaction().commit();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

}