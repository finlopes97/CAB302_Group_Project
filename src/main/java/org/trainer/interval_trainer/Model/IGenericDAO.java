package org.trainer.interval_trainer.Model;

import java.util.List;

/**
 * The {@code IGenericDAO} interface defines the basic CRUD operations for data access objects (DAOs).
 * It provides methods to add, update, delete, and retrieve items of type {@code T}, where {@code T} extends {@code BaseItem}.
 * @param <T> the type of items managed by this DAO, which extends {@code BaseItem}
 */
public interface IGenericDAO<T extends BaseItem> {
    /**
     * Adds a new item to the data store.
     * @param item the item to add
     */
    void add(T item);

    /**
     * Updates an existing item in the data store.
     * @param item the item to update
     */
    void update(T item);

    /**
     * Deletes an item from the data store.
     * @param item the item to delete
     */
    void delete(T item);

    /**
     * Retrieves an item by its unique identifier.
     * @param id the unique identifier of the item
     * @return the item with the specified ID, or {@code null} if not found
     */
    T getById(int id);

    /**
     * Retrieves all items from the data store.
     * @return a list of all items
     */
    List<T> getAll();
}
