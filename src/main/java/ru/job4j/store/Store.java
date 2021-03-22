package ru.job4j.store;

import ru.job4j.model.Item;

import java.util.List;

public interface Store<T> {
    T add(T t);
    T findById(long id);
    T update(T t);
    void delete(T t);
    List<T> all();
}
