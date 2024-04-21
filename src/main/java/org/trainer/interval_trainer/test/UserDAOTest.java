package org.trainer.interval_trainer.test;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserDAOTest {
    private Connection connection;
    private UserDAO userDAO;

    @BeforeAll
    public void setUp() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:test.db");
        userDAO = new UserDAO(connection);
    }

    @AfterAll
    public void tearDown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    public void testAddUser() throws SQLException {
        String email = "test@test.com";
        String username = "jc_denton";
        String password = "guest123";

        userDAO.addUser(email, username, password);

        boolean userExists = userDAO.checkUserExists(username);
        assertTrue(userExists);
    }
}
