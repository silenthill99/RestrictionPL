package fr.silenthill99.restrictionpl.mysql;

import fr.silenthill99.restrictionpl.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private final DbCredentials credentials;
    private Connection connection;
    Main main = Main.getInstance();

    public DbConnection(DbCredentials credentials) {
        this.credentials = credentials;
        connect();
    }

    public void connect() {
        try {
            connection = DriverManager.getConnection(credentials.toURI(), credentials.getUser(), credentials.getPassword());
            main.getLogger().info("Connection établie avec la BDD !");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() throws SQLException {
        if (this.connection != null) {
            if (!this.connection.isClosed()) {
                this.connection.close();
            }
        }
    }

    public Connection getConnection() throws SQLException {
        if (this.connection != null) {
            if (!this.connection.isClosed()) {
                return this.connection;
            }
        }
        connect();
        return this.connection;
    }
}
