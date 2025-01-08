package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:h2:mem:tenis_db";
    private static final String USER = "maxim";
    private static final String PASSWORD = "maxim";

    public static Connection getConnection()throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Could not load driver", e);
        }
    }
}
