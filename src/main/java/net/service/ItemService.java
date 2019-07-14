package net.service;

import java.util.List;

public interface ItemService<T> {
    void add(T t);

    void update(T t);

    void remove(int id);

    T getById(int id);

    List<T> list();

    List<T> listByQuery(String query);
}
