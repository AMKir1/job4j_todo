package ru.job4j.tasks.hql;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "candidates")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String experience;
    private double salary;

    @OneToOne(fetch = FetchType.LAZY)
    private Base base;

    public Candidate() {
    }

    public Candidate(long id, String name, String experience, double salary) {
        this.id = id;
        this.name = name;
        this.experience = experience;
        this.salary = salary;
    }

    public static Candidate of(String name, String experience, double salary) {
        Candidate c = new Candidate();
        c.setName(name);
        c.setExperience(experience);
        c.setSalary(salary);
        return c;
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

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", experience='" + experience + '\'' +
                ", salary=" + salary +
                ", base=" + base +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidate candidate = (Candidate) o;
        return id == candidate.id &&
                Double.compare(candidate.salary, salary) == 0 &&
                Objects.equals(name, candidate.name) &&
                Objects.equals(experience, candidate.experience);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, experience, salary);
    }
}
