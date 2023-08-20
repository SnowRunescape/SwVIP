package br.com.snowdev.swvip.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.snowdev.swvip.entities.Key;

public class KeyModel
{
    public static void migrate()
    {
        br.com.snowdev.swvip.SwVIP.SQLManager().update("CREATE TABLE IF NOT EXISTS sw_keys (vip_key VARCHAR(16) NOT NULL, group VARCHAR(32) NOT NULL, days INT NOT NULL);");
    }

    public static Key create(String key, String group, int days)
    {
        int rs = br.com.snowdev.swvip.SwVIP.SQLManager().update("INSERT INTO sw_keys VALUES (?, ?, ?)", key, group, days);

        if (rs < 1) {
            return null;
        }

        return new Key(key, group, days);
    }

    public static boolean delete(String key)
    {
        int rs = br.com.snowdev.swvip.SwVIP.SQLManager().update("DELETE FROM sw_keys WHERE vip_key = ?", key);

        return (rs > 0);
    }

    public static Key findByKey(String key) throws SQLException
    {
        ResultSet rs = br.com.snowdev.swvip.SwVIP.SQLManager().select("SELECT * FROM sw_keys WHERE vip_key = ?", key);

        if (!rs.next()) {
            return null;
        }

        return new Key(
            rs.getString("vip_key"),
            rs.getString("group"),
            rs.getInt("days")
        );
    }

    public static Key[] all() throws SQLException
    {
        List<Key> swKeysList = new ArrayList<Key>();

        ResultSet rs = br.com.snowdev.swvip.SwVIP.SQLManager().select("SELECT * FROM sw_keys");

        while (rs.next()) {
            Key swKey = new Key(
                rs.getString("vip_key"),
                rs.getString("group"),
                rs.getInt("days")
            );

            swKeysList.add(swKey);
        }

        return swKeysList.toArray(new Key[0]);
    }
}
