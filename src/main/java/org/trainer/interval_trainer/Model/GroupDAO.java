package org.trainer.interval_trainer.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the Data Access Object (DAO) for managing Group entities in the database.
 */
public class GroupDAO implements IGenericDAO<Group> {
    private final Connection connection;

    /**
     * Constructor for the GroupDAO class. It initializes the database connection and creates the groups table if it doesn't exist.
     */
    public GroupDAO() {
        connection = DebugConnection.getInstance();
        createTable();
    }

    /**
     * Creates the blocks table if it doesn't exist.
     */
    private void createTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS groups ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "routine_id INTEGER NOT NULL,"
                    + "intervals INTEGER NOT NULL,"
                    + "FOREIGN KEY (routine_id) REFERENCES routines(id),"
                    + ")";
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    /**
     * Adds a group to the database.
     *
     * @param group The block to be added.
     */
    @Override
    public void add(Group group) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO groups (routine_id, intervals)");
            statement.setInt(1, group.getRoutineId());
            statement.setInt(2, group.getIntervals());
            statement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    /**
     * Updates an existing group in the database.
     *
     * @param group The group to be updated.
     */
    @Override
    public void update(Group group) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE groups SET routine_id = ?, intervals = ? WHERE id = ?");
            statement.setInt(1, group.getRoutineId());
            statement.setInt(2, group.getIntervals());
            statement.setInt(3, group.getId());
            statement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    /**
     * Deletes a group from the database.
     *
     * @param group The group to be deleted.
     */
    @Override
    public void delete(Group group) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM groups WHERE id = ?");
            statement.setInt(1, group.getId());
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    /**
     * Retrieves a group from the database based on its ID.
     *
     * @param id The ID of the group to retrieve.
     * @return The retrieved group, or null if not found.
     */
    @Override
    public Group getById(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM groups WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int routineId = resultSet.getInt("routine_id");
                int intervals = resultSet.getInt("intervals");
                List<Block> blocks = getBlocksByGroupId(id);
                Group group = new Group(id, routineId, intervals, blocks);
                return group;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves a list of all groups from the database.
     *
     * @return The retrieved list of groups, or null if not found.
     */
    @Override
    public List<Group> getAll() {
        try {
            List<Group> groups = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM groups");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int routineId = resultSet.getInt("routine_id");
                int intervals = resultSet.getInt("intervals");
                List<Block> blocks = getBlocksByGroupId(id);
                Group group = new Group(id, routineId, intervals, blocks);
            }
            return groups;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves a list of all block from the database based on their group id.
     *
     * @return The retrieved list of blocks, or null if not found.
     */
    private List<Block> getBlocksByGroupId(int Id) {
        List<Block> blocks = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM blocks WHERE group_id = ?");
            statement.setInt(1, Id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                int blockId = resultSet.getInt("id");
                int routineId = resultSet.getInt("routine_id");
                int groupId = resultSet.getInt("group_id");
                String type = resultSet.getString("type");
                int timeInSeconds = resultSet.getInt("time_in_seconds");
                Block block = new Block(blockId, routineId, groupId, type, timeInSeconds);
                blocks.add(block);
                return blocks;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }
}
