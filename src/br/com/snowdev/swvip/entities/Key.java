package br.com.snowdev.swvip.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Key
{
    public String code;
    public String group;
    public int days;

    public Key(String code, String group, int days)
    {
        this.code = code;
        this.group = group;
        this.days = days;
    }

    public static Key buildFromResultSet(ResultSet result) throws SQLException
    {
        return new Key(
            result.getString("key"),
            result.getString("group"),
            result.getInt("days")
        );
    }
}