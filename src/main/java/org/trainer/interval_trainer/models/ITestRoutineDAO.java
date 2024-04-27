package org.trainer.interval_trainer.models;
import java.util.List;
public interface ITestRoutineDAO {
    /**
     * Adds a new routine to the database.
     * @param routine The routine to add.
     */
    public void addRoutine(TestRoutine routine);
    /**
     * Updates an existing routine in the database.
     * @param routine The routine to update.
     */
    public void updateRoutine(TestRoutine routine);
    /**
     * Deletes a routine from the database.
     * @param routine The routine to delete.
     */
    public void deleteRoutine(TestRoutine routine);
    /**
     * Retrieves a routine from the database.
     * @param id The id of the routine to retrieve.
     * @return The routine with the given id, or null if not found.
     */
    public TestRoutine getRoutine(int id);

}
