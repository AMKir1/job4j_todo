package ru.job4j.tasks.mapping;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "engine_id", foreignKey = @ForeignKey(name = "ENGINE_ID_FK"))
    private Engine engine;

    @ManyToMany(mappedBy = "cars")
    private Set<Driver> drivers = new HashSet<>();

    public Car() { }

    public Car(long id, String name, Engine engine, Set<Driver> drivers) {
        this.id = id;
        this.name = name;
        this.engine = engine;
        this.drivers = drivers;
    }

    public static Car of(String name) {
        Car car = new Car();
        car.setName(name);
        return car;
    }

    public Set<Driver> getDrivers() {
        return this.drivers;
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

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void setDrivers(Set<Driver> drivers) {
        this.drivers = drivers;
    }
}
