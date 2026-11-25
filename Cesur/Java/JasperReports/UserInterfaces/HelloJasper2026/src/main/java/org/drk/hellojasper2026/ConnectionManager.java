package org.drk.hellojasper2026;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/ad",
                        "root",
                        "root");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}
