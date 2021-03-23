package ru.job4j.lazy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "lazy_brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    //    @OneToMany(mappedBy = "brand", fetch = FetchType.EAGER) нежелательно
    @OneToMany(mappedBy = "brand")
    private List<Model> models = new ArrayList<>();

    public Brand() {
    }

    public Brand(long id, String name, List<Model> models) {
        this.id = id;
        this.name = name;
        this.models = models;
    }

    public static Brand of(String name) {
        Brand brand = new Brand();
        brand.setName(name);
        return brand;
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

    public List<Model> getModels() {
        return models;
    }

    public void addModel(Model model) {
        this.models.add(model);
    }

    @Override
    public String toString() {
        return "Brand{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brand brand = (Brand) o;
        return id == brand.id &&
                Objects.equals(name, brand.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
