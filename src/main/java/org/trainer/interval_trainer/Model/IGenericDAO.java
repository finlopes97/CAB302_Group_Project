package org.trainer.interval_trainer.Model;

import java.util.List;

public interface IGenericDAO<T extends BaseItem> {
    void add(T item);
    void update(T item);
    void delete(T item);
    T getById(int id);
    List<T> getAll();
}
