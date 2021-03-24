package ru.job4j.tasks.mapping;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "drivers")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "history_owner",
            joinColumns = {@JoinColumn(name = "driver_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "car_id", nullable = false, updatable = false)})
    private Set<Car> cars = new HashSet<>();

    public Driver() {
    }

    public Driver(long id, String name, Set<Car> cars) {
        this.id = id;
        this.name = name;
        this.cars = cars;
    }

    public static Driver of(String name) {
        Driver driver = new Driver();
        driver.setName(name);
        return driver;
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

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public Set<Car> getCars() {
        return this.cars;
    }

}
