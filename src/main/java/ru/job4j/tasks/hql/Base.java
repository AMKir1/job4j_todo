package ru.job4j.tasks.hql;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "bases")
public class Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vacancy> vacancies = new ArrayList<>();

    public Base() {
    }

    public Base(long id, String name, List<Vacancy> vacancies) {
        this.id = id;
        this.name = name;
        this.vacancies = vacancies;
    }

    public static Base of(String name) {
        Base base = new Base();
        base.setName(name);
        return base;
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

    public List<Vacancy> getVacancies() {
        return vacancies;
    }

    public void addVacancy(Vacancy vacancy) {
        this.vacancies.add(vacancy);
    }

    @Override
    public String toString() {
        return "Base{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", vacancies=" + vacancies +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Base base = (Base) o;
        return id == base.id &&
                Objects.equals(name, base.name) &&
                Objects.equals(vacancies, base.vacancies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, vacancies);
    }
}
