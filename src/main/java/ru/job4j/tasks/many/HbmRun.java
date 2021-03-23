//package ru.job4j.tasks.many;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.boot.MetadataSources;
//import org.hibernate.boot.registry.StandardServiceRegistry;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//
//
//public class HbmRun {
//
//    public static void main(String[] args) {
//        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
//                .configure().build();
//        try {
//            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
//            Session session = sf.openSession();
//            session.beginTransaction();
//
//            CarBrand bmw = CarBrand.of("BMW");
//
//            ru.job4j.tasks.many.CarModel sedan = ru.job4j.tasks.many.CarModel.of("Sedan");
//            ru.job4j.tasks.many.CarModel cabrio = ru.job4j.tasks.many.CarModel.of("Cabrio");
//            ru.job4j.tasks.many.CarModel gt = ru.job4j.tasks.many.CarModel.of("GT");
//            ru.job4j.tasks.many.CarModel coupe = ru.job4j.tasks.many.CarModel.of("Coupe");
//            ru.job4j.tasks.many.CarModel granCoupe = ru.job4j.tasks.many.CarModel.of("Gran Coupe");
//
//            bmw.addModel(sedan);
//            bmw.addModel(cabrio);
//            bmw.addModel(gt);
//            bmw.addModel(coupe);
//            bmw.addModel(granCoupe);
//
//            session.save(bmw);
//            session.getTransaction().commit();
//            session.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            StandardServiceRegistryBuilder.destroy(registry);
//        }
//    }
//
//}
