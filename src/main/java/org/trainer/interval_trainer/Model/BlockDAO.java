package org.trainer.interval_trainer.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the Data Access Object (DAO) for managing Block entities in the database.
 */
public class BlockDAO implements IGenericDAO<Block> {
    private final Connection connection;

    /**
     * Constructor for the BlockDAO class. It initializes the database connection and creates the blocks table if it doesn't exist.
     */
    public BlockDAO() {
        connection = DebugConnection.getInstance();
        createTable();
    }

    /**
     * Creates the blocks table if it doesn't exist.
     */
    private void createTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS blocks ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "routine_id INTEGER NOT NULL,"
                    + "group_id INTEGER,"
                    + "type VARCHAR NOT NULL,"
                    + "time_in_seconds INTEGER NOT NULL,"
                    + "FOREIGN KEY (routine_id) REFERENCES routines(id),"
                    + "FOREIGN KEY (group_id) REFERENCES groups(id),"
                    + ")";
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    /**
     * Adds a block to the database.
     *
     * @param block The block to be added.
     */
    @Override
    public void add(Block block) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO blocks (routine_id, group_id, type, time_in_seconds)");
            statement.setInt(1, block.getRoutineId());
            statement.setInt(2, block.getGroupId());
            statement.setString(3, "");
            statement.setInt(4, block.getTimeinSeconds().get());
            statement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    /**
     * Updates an existing block in the database.
     *
     * @param block The block to be updated.
     */
    @Override
    public void update(Block block) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE blocks SET routine_id = ?, group_id = ?, type = ?, time_in_seconds = ? WHERE id = ?");
            statement.setInt(1, block.getRoutineId());
            statement.setInt(2, block.getGroupId());
            statement.setString(3, "");
            statement.setInt(4, block.getTimeinSeconds().get());
            statement.setInt(5, block.getId());
            statement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    /**
     * Deletes a block from the database.
     *
     * @param block The block to be deleted.
     */
    @Override
    public void delete(Block block) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM blocks WHERE id = ?");
            statement.setInt(1, block.getId());
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    /**
     * Retrieves a block from the database based on its ID.
     *
     * @param id The ID of the block to retrieve.
     * @return The retrieved block, or null if not found.
     */
    @Override
    public Block getById(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM blocks WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int routineId = resultSet.getInt("routine_id");
                int groupId = resultSet.getInt("group_id");
                String type = resultSet.getString("type");
                int timeInSeconds = resultSet.getInt("time_in_seconds");
                Block block = new Block(id, routineId, groupId, type, timeInSeconds);
                block.setId(id);
                return block;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves a list of all block from the database.
     *
     * @return The retrieved list of blocks, or null if not found.
     */
    @Override
    public List<Block> getAll() {
        List<Block> blocks = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM blocks");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int blockId = resultSet.getInt("id");
                int routineId = resultSet.getInt("routine_id");
                int groupId = resultSet.getInt("group_id");
                String type = resultSet.getString("type");
                int timeInSeconds = resultSet.getInt("time_in_seconds");
                Block block = new Block(blockId, routineId, groupId, type, timeInSeconds);
                blocks.add(block);
            }
            return blocks;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }
}
