package ru.job4j.tasks.many;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "models")
public class CarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    public CarModel() {
    }

    public CarModel(String name) {
        this.name = name;
    }

    public CarModel(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ru.job4j.tasks.many.CarModel of(String name) {
        ru.job4j.tasks.many.CarModel model = new ru.job4j.tasks.many.CarModel();
        model.name = name;
        return model;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Car{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ru.job4j.tasks.many.CarModel carModel = (ru.job4j.tasks.many.CarModel) o;
        return id == carModel.id &&
                Objects.equals(name, carModel.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
