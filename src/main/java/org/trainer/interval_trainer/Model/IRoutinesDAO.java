package org.trainer.interval_trainer.Model;

import java.util.List;

public interface IRoutinesDAO {
        /**
         * Adds a new routine to the database.
         * @param routine The routine to add.
         */
        public void addRoutine(Routine routine);
        /**
         * Updates an existing routine in the database.
         * @param routine The routine to update.
         */
        public void updateRoutine(Routine routine);
        /**
         * Deletes a routine from the database.
         * @param routine The routine to delete.
         */
        public void deleteRoutine(Routine routine);
        /**
         * Retrieves a routine from the database.
         * @param id The id of the routine to retrieve.
         * @return The routine with the given id, or null if not found.
         */
        public Routine getRoutine(int id);

}
