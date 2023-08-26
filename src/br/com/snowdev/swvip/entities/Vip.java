package br.com.snowdev.swvip.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Vip
{
    public int id;
    public String username;
    public String group;

    public Vip(int id, String username, String group)
    {
        this.id = id;
        this.username = username;
        this.group = group;
    }

    public static Vip buildFromResultSet(ResultSet result) throws SQLException
    {
        return new Vip(
            result.getInt("id"),
            result.getString("username"),
            result.getString("group")
        );
    }
}