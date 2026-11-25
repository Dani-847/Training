package org.example;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public final class DataProvider {
    private DataProvider() {}

    public static DataSource getDataSource(String url, String user, String pass) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Driver de MySQL no encontrado. Agrega mysql-connector-j al pom.", e);
        }
        return new SimpleDriverManagerDataSource(url, user, pass);
    }

    private static final class SimpleDriverManagerDataSource implements DataSource {
        private final String url;
        private final String user;
        private final String pass;

        private SimpleDriverManagerDataSource(String url, String user, String pass) {
            this.url = url;
            this.user = user;
            this.pass = pass;
        }

        @Override
        public Connection getConnection() throws SQLException {
            return DriverManager.getConnection(url, user, pass);
        }

        @Override
        public Connection getConnection(String username, String password) throws SQLException {
            return DriverManager.getConnection(url, username, password);
        }

        @Override
        public PrintWriter getLogWriter() { return DriverManager.getLogWriter(); }

        @Override
        public void setLogWriter(PrintWriter out) { DriverManager.setLogWriter(out); }

        @Override
        public void setLoginTimeout(int seconds) { DriverManager.setLoginTimeout(seconds); }

        @Override
        public int getLoginTimeout() { return DriverManager.getLoginTimeout(); }

        @Override
        public Logger getParentLogger() { return Logger.getLogger("org.example"); }

        @Override
        public <T> T unwrap(Class<T> iface) throws SQLException { throw new SQLException("Not a wrapper"); }

        @Override
        public boolean isWrapperFor(Class<?> iface) { return false; }
    }
}
