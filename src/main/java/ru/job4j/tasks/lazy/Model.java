//package ru.job4j.tasks.lazy;
//
//import javax.persistence.*;
//import java.util.Objects;
//
//@Entity
//@Table(name = "lazy_models")
//public class Model {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    private String name;
//    @ManyToOne
//    @JoinColumn(name = "brand_id")
//    private Brand brand;
//
//    public Model() {
//    }
//
//    public Model(long id, String name, Brand brand) {
//        this.id = id;
//        this.name = name;
//        this.brand = brand;
//    }
//
//    public static Model of(String name, Brand brand) {
//        Model model = new Model();
//        model.setName(name);
//        model.setBrand(brand);
//        return model;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Brand getBrand() {
//        return brand;
//    }
//
//    public void setBrand(Brand brand) {
//        this.brand = brand;
//    }
//
//    @Override
//    public String toString() {
//        return "Model{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", brand=" + brand +
//                '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Model model = (Model) o;
//        return id == model.id &&
//                Objects.equals(name, model.name);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name);
//    }
//}
