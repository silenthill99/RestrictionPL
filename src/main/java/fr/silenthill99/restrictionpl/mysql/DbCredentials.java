package fr.silenthill99.restrictionpl.mysql;

public class DbCredentials {
    private final String dbName;
    private final String host;
    private final String user;
    private final int port;
    private final String password;

    public DbCredentials(String dbName, String host, String user, int port, String password) {
        this.dbName = dbName;
        this.host = host;
        this.user = user;
        this.port = port;
        this.password = password;
    }

    public String toURI() {
        StringBuilder url = new StringBuilder();
        url.append("jdbc:mysql://")
                .append(host)
                .append(":")
                .append(port)
                .append("/")
                .append(dbName);
        return url.toString();
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

}
