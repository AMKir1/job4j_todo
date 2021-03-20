package ru.job4j.store;

import ru.job4j.model.Item;

import java.util.List;

public interface Store {
    Item add(Item item);
    Item findById(long id);
    Item update(Item item);
    void delete(Item item);
    List<Item> allNotDoneItems();
    List<Item> allItems();
}
