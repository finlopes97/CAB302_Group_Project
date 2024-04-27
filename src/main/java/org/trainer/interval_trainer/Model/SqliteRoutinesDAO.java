package org.trainer.interval_trainer.Model;

import java.sql.*;

public class SqliteRoutinesDAO implements IRoutinesDAO{

    private final Connection connection;
    public SqliteRoutinesDAO() {
        connection = SqliteConnection.getInstance();
        createTable();
    }
    private void createTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS routines ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "name VARCHAR NOT NULL,"
                    + "created_by VARCHAR NOT NULL,"
                    + "created_on TIMESTAMP NOT NULL,"
                    + "description TEXT NOT NULL,"
                    + "total_time INTEGER NOT NULL"
                    +")";
            statement.execute(query);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addRoutine(Routine routine) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO routines (name, created_by, created_on, description, total_time) VALUES (?,?,?,?,?)");
            statement.setString(1, routine.getName());
            statement.setString(2, routine.getCreatedBy());
            statement.setTimestamp(3, routine.getCreatedOn());
            statement.setString(4, routine.getDescription());
            statement.setInt(5, routine.getTotalTime());
            statement.executeUpdate();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRoutine(Routine routine) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE routines SET name = ?, created_by = ?, created_on = ?, description = ?, total_time = ? WHERE id = ?");
            statement.setString(1, routine.getName());
            statement.setString(2, routine.getCreatedBy());
            statement.setTimestamp(3, routine.getCreatedOn());
            statement.setString(4, routine.getDescription());
            statement.setInt(5, routine.getTotalTime());
            statement.setInt(6, routine.getId());
            statement.executeUpdate();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRoutine(Routine routine) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM routines WHERE id = ?");

            statement.setInt(6, routine.getId());
            statement.executeUpdate();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Routine getRoutine(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM routines WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String created_by = resultSet.getString("created_by");
                Timestamp created_on = resultSet.getTimestamp("created_on");
                String description = resultSet.getString("description");
                int total_time = resultSet.getInt("total_time");
                Routine routine = new Routine(name, created_by, created_on, description, total_time);
                routine.setId(id);
                return routine;
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
