package net.DAO;

import java.util.List;

public interface DAO<T> {

    void add(T t);

    void update(T t);

    void remove(int id);

    T getById(int id);

    List<T> getAll();

    List<T> getByQuery(String query);

}
