package br.com.snowdev.swvip.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLManager
{
    private String user = "root";
    private String pass = "vertrigo";
    private String host = "localhost";
    private String port = "3306";
    private String database = "teste";
    private String type = "jdbc:mysql://";
    private Connection connection;
    private int modications = 0;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getNewConnection() throws Exception
    {
        return DriverManager.getConnection(
                this.type + this.host + ":" + this.port + "/" + this.database, this.user, this.pass);
    }

    public synchronized void openConnection()
    {
        try {
            if (!this.hasConnection()) {
                this.connection = this.getNewConnection();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized boolean hasModifications()
    {
        return this.modications > 0;
    }

    public synchronized Connection getConnection()
    {
        return this.connection;
    }

    public synchronized int update(String query, Object... replacers)
    {
        this.startQuery();

        try {
            return this.query(query, replacers).executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            this.endQuery();
        }
    }

    public synchronized PreparedStatement query(String query, Object... replacers)
    {
        try {
            query = query.replace('?', '&');

            for (Object replacer:replacers) {
                query=query.replaceFirst("&", "'"+replacer.toString() + "'");
            }

            if (!query.endsWith(";")) {
                query += ";";
            }

            return this.connection.prepareStatement(query);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public synchronized ResultSet select(String query, Object... replacers) throws SQLException
    {
        this.startQuery();

        return this.query(query, replacers).executeQuery();
    }

    public synchronized void startQuery()
    {
        this.openConnection();
        this.modications++;
    }

    public synchronized void endQuery()
    {
        this.modications--;
        this.closeConnection();
    }

    public synchronized boolean hasConnection()
    {
        return this.connection != null;
    }

    public synchronized void closeConnection()
    {
        try {
            if (hasConnection() && !hasModifications()) {
                this.connection.close();
                this.connection = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SQLManager(String user, String pass, String host, String database)
    {
        super();

        this.user = user;
        this.pass = pass;
        this.host = host;
        this.database = database;
    }

    public String getUser()
    {
        return this.user;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public String getPass()
    {
        return this.pass;
    }

    public void setPass(String pass)
    {
        this.pass = pass;
    }

    public String getHost()
    {
        return this.host;
    }

    public void setHost(String host)
    {
        this.host = host;
    }

    public String getPort()
    {
        return this.port;
    }

    public void setPort(String port)
    {
        this.port = port;
    }

    public String getDatabase()
    {
        return this.database;
    }

    public void setDatabase(String database)
    {
        this.database = database;
    }

    public String getType()
    {
        return this.type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
}