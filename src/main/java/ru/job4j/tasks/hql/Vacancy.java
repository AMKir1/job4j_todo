package ru.job4j.tasks.hql;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vacancies")
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String description;

    public Vacancy() {
    }

    public Vacancy(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static Vacancy of(String name, String description) {
        Vacancy v = new Vacancy();
        v.setName(name);
        v.setDescription(description);
        return v;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy vacancy = (Vacancy) o;
        return id == vacancy.id &&
                Objects.equals(name, vacancy.name) &&
                Objects.equals(description, vacancy.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}
