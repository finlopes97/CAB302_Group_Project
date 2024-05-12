package org.trainer.interval_trainer.Model;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                    + "total_time INTEGER NOT NULL,"
                    + "data BLOB NOT NULL"
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
            PreparedStatement statement = connection.prepareStatement("INSERT INTO routines (name, created_by, created_on, description, total_time, data) VALUES (?,?,?,?,?,?)");
            statement.setString(1, routine.getName().get());
            statement.setString(2, routine.getCreatedBy());
            statement.setTimestamp(3, routine.getCreatedOn());
            statement.setString(4, routine.getDescription().get());
            statement.setInt(5, routine.getTotalTime());

            getbytesfromroutine(routine, statement);
            statement.execute();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRoutine(Routine routine) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE routines SET name = ?, created_by = ?, created_on = ?, description = ?, total_time = ?, data = ? WHERE id = ?");
            statement.setString(1, routine.getName().get());
            statement.setString(2, routine.getCreatedBy());
            statement.setTimestamp(3, routine.getCreatedOn());
            statement.setString(4, routine.getDescription().get());
            statement.setInt(5, routine.getTotalTime());
            statement.setInt(7, routine.getId().get());

            getbytesfromroutine(routine, statement);
            statement.executeUpdate();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void getbytesfromroutine(Routine routine, PreparedStatement statement) throws IOException, SQLException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(routine.getGroup());
        oos.flush();
        oos.close();
        InputStream is = new ByteArrayInputStream(baos.toByteArray());

        statement.setBytes(6, is.readAllBytes());
    }

    @Override
    public void deleteRoutine(Routine routine) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM routines WHERE id = ?");

            statement.setInt(1, routine.getId().get());
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
                Routine routine = makeRoutine(resultSet);
                routine.getId().set(id);
                return routine;
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Routine> getAllRoutines() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM routines");
            ResultSet resultSet = statement.executeQuery();
            List<Routine> routines = new ArrayList<>();
            while (resultSet.next()) {


                routines.add(makeRoutine(resultSet));
            }
            return routines;
        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private Routine makeRoutine(ResultSet resultSet) throws SQLException, IOException, ClassNotFoundException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String created_by = resultSet.getString("created_by");
        Timestamp created_on = resultSet.getTimestamp("created_on");
        String description = resultSet.getString("description");
        int total_time = resultSet.getInt("total_time");

        ObjectInputStream is = new ObjectInputStream(new ByteArrayInputStream(resultSet.getBytes("data")));
        Group group = (Group) is.readObject();

        Routine newRoutine = new Routine(name, created_by, created_on, description, total_time, group);
        newRoutine.getId().set(id);

        return newRoutine;

    }
}
