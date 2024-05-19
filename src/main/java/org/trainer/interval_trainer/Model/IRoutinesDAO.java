package org.trainer.interval_trainer.Model;

/**
 * The {@code IRoutinesDAO} interface defines the basic CRUD operations for managing routines in the database.
 * It provides methods to add, update, delete, and retrieve routines.
 */
public interface IRoutinesDAO {
        /**
         * Adds a new routine to the database.
         * @param routine The routine to add.
         */
        void addRoutine(Routine routine);
        /**
         * Updates an existing routine in the database.
         * @param routine The routine to update.
         */
        void updateRoutine(Routine routine);
        /**
         * Deletes a routine from the database.
         * @param routine The routine to delete.
         */
        void deleteRoutine(Routine routine);
        /**
         * Retrieves a routine from the database.
         * @param id The id of the routine to retrieve.
         * @return The routine with the given id, or null if not found.
         */
        Routine getRoutine(int id);
}
