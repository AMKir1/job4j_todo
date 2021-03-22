package ru.job4j.store;

import ru.job4j.model.Item;

import java.util.List;

public interface ItemStore extends Store<Item> {
    List<Item> allNotDoneItems();
}
