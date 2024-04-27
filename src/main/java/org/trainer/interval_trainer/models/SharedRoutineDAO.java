package org.trainer.interval_trainer.models;

import java.sql.*;

public class SharedRoutineDAO implements ITestRoutineDAO {
    private final Connection connection;
    public SharedRoutineDAO() {
        connection = TestSqliteConnection.getInstance();
    }

    private void createTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS shared_routines ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "name VARCHAR NOT NULL,"
                    + "created_by VARCHAR NOT NULL,"
                    + "created_on TIMESTAMP NOT NULL,"
                    + "description TEXT NOT NULL,"
                    + "total_time INTEGER NOT NULL,"
                    + "shared_on TIMESTAMP NOT NULL,"
                    + "use_alias BOOLEAN NOT NULL,"
                    + "user_alias VARCHAR,"
                    + ")";
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addRoutine(SharedRoutine sharedRoutine) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO routines (name, created_by, created_on, description, total_time, shared_on, use_alias, user_alias) VALUES (?,?,?,?,?,?,?,?)");
            statement.setString(1, sharedRoutine.getName());
            statement.setString(2, sharedRoutine.getCreatedBy());
            statement.setTimestamp(3, sharedRoutine.getCreatedOn());
            statement.setString(4, sharedRoutine.getDescription());
            statement.setInt(5, sharedRoutine.getTotalTime());
            statement.setTimestamp(7, sharedRoutine.getSharedOn());
            statement.setBoolean(8, sharedRoutine.isUseAlias());
            statement.setString(9, sharedRoutine.getUserAlias());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRoutine(TestRoutine routine) {

    }

    @Override
    public void deleteRoutine(TestRoutine routine) {

    }

    @Override
    public TestRoutine getRoutine(int id) {
        return null;
    }
}
