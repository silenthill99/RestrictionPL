package fr.silenthill99.restrictionpl.mysql;

import java.sql.SQLException;

public class DatabaseManager {
    private final DbConnection connection;

    public DatabaseManager() {
        this.connection = new DbConnection(new DbCredentials("minecraft_235640", "minecraft118.omgserv.com", "minecraft_235640", 3306, "Mylene.10"));
    }

    public DbConnection getConnection() {
        return connection;
    }

    public void close() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
