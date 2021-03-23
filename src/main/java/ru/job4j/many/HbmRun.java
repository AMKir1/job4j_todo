package ru.job4j.many;

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

            CarBrand bmw = CarBrand.of("BMW");

            CarModel sedan = CarModel.of("Sedan");
            CarModel cabrio = CarModel.of("Cabrio");
            CarModel gt = CarModel.of("GT");
            CarModel coupe = CarModel.of("Coupe");
            CarModel granCoupe = CarModel.of("Gran Coupe");

            bmw.addModel(sedan);
            bmw.addModel(cabrio);
            bmw.addModel(gt);
            bmw.addModel(coupe);
            bmw.addModel(granCoupe);

            session.save(bmw);
            session.getTransaction().commit();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

}
