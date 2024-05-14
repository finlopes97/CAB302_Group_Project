package org.trainer.interval_trainer.Model;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implements the database operations for managing routines using SQLite.
 * This class handles CRUD operations for routines stored in an SQLite database.
 */
public class SqliteRoutinesDAO implements IRoutinesDAO {
    private final Connection connection;

    /**
     * Constructs an instance of SqliteRoutinesDAO and initializes the database connection.
     * It also ensures the necessary table for routines exists.
     */
    public SqliteRoutinesDAO() {
        connection = SqliteConnection.getInstance();
        createTable();
    }

    /**
     * Ensures the creation of the routines table in the database if it does not exist.
     */
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
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            System.err.println("Error creating table in SqliteRoutinesDAO: " + e.getMessage());
        }
    }

    /**
     * Adds a new routine to the database.
     *
     * @param routine the routine to add
     */
    @Override
    public void addRoutine(Routine routine) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO routines (name, created_by, created_on, description, total_time, data) VALUES (?,?,?,?,?,?)");
            statement.setString(1, routine.getName().get());
            statement.setString(2, routine.getCreatedBy());
            statement.setTimestamp(3, routine.getCreatedOn());
            statement.setString(4, routine.getDescription().get());
            statement.setInt(5, routine.getTotalTime());

            getBytesFromRoutine(routine, statement);
            statement.execute();
        } catch (Exception e) {
            System.err.println("Error creating table in SqliteRoutinesDAO: " + e.getMessage());
        }
    }

    /**
     * Updates an existing routine in the database.
     *
     * @param routine the routine to update
     */
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

            getBytesFromRoutine(routine, statement);
            statement.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error creating table in SqliteRoutinesDAO: " + e.getMessage());
        }
    }

    /**
     * Converts a Routine object's group data into a byte array and sets it to the prepared statement.
     *
     * @param routine   the routine whose group data is to be serialized
     * @param statement the prepared statement for database operation
     * @throws IOException  if an I/O error occurs during serialization
     * @throws SQLException if an SQL error occurs during data setting
     */
    private void getBytesFromRoutine(Routine routine, PreparedStatement statement) throws IOException, SQLException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(routine.getGroup());
        oos.flush();
        oos.close();
        InputStream is = new ByteArrayInputStream(baos.toByteArray());

        statement.setBytes(6, is.readAllBytes());
    }

    /**
     * Deletes a routine from the database.
     *
     * @param routine the routine to delete
     */
    @Override
    public void deleteRoutine(Routine routine) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM routines WHERE id = ?");

            statement.setInt(1, routine.getId().get());
            statement.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error creating table in SqliteRoutinesDAO: " + e.getMessage());
        }
    }

    /**
     * Retrieves a routine from the database by its ID.
     *
     * @param id the ID of the routine to retrieve
     * @return the routine if found, otherwise null
     */
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

        } catch (Exception e) {
            System.err.println("Error creating table in SqliteRoutinesDAO: " + e.getMessage());
        }
        return null;
    }

    /**
     * Retrieves all routines from the database, optionally filtered by the creator's name.
     *
     * @param name the optional name of the creator to filter routines
     * @return a list of routines
     */
    public List<Routine> getAllRoutines(Optional<String> name) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM routines");
            if (name.isPresent()) {
                statement = connection.prepareStatement("SELECT * FROM routines WHERE created_by = ?");
                statement.setString(1, name.get());
            }
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

    /**
     * Retrieves all a limited number of routines from the database, optionally filtered by the creator's name.
     * @param name the optional name of the creator to filter routines
     * @param limit the maximum amount of routines this shitty method will fetch
     * @return a (small) list of routines (for this weak ass method)
     */
    public List<Routine> getSomeRoutines(Optional<String> name, int limit) {
        try {
            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM routines");
            if (name.isPresent()) {
                queryBuilder.append(" WHERE created_by = ?");
            }
            queryBuilder.append(" ORDER BY created_on DESC LIMIT ?");

            PreparedStatement statement = connection.prepareStatement(queryBuilder.toString());
            if (name.isPresent()) {
                statement.setString(1, name.get());
                statement.setInt(2, limit);
            } else {
                statement.setInt(1, limit);
            }

            ResultSet resultSet = statement.executeQuery();
            List<Routine> routines = new ArrayList<>();
            while (resultSet.next()) {
                routines.add(makeRoutine(resultSet));
            }
            return routines;
        } catch (SQLException | IOException | ClassNotFoundException e) {
            System.err.println("Error retrieving " + limit + " routines from SqliteRoutinesDAO: " + e.getMessage());
        }
        return null;
    }

    /**
     * Searches for routines in the database by name.
     * @param search the search term to match against routine names
     * @return a list of routines that match the search term
     */
    public List<Routine> searchRoutine(String search) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM routines WHERE name LIKE ?");
            statement.setString(1, "%" + search + "%");
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

    /**
     * Constructs a Routine object from a database result set.
     * @param resultSet the result set containing routine data
     * @return a constructed Routine object
     * @throws SQLException if a database access error occurs
     * @throws IOException if an I/O error occurs reading the data blob
     * @throws ClassNotFoundException if deserialization of a Group object fails
     */
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
