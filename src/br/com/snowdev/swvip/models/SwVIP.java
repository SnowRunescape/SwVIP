package br.com.snowdev.swvip.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.snowdev.swvip.SwKey;

public class SwVIP
{
    public static void migrate()
    {
        br.com.snowdev.swvip.SwVIP.SQLManager().update("CREATE TABLE IF NOT EXISTS swvip (vip_key VARCHAR(16) NOT NULL, vip_group VARCHAR(32) NOT NULL, days INT NOT NULL);");
    }

    public static SwKey create(String key, String group, int days)
    {
        int rs2 = br.com.snowdev.swvip.SwVIP.SQLManager().update("INSERT INTO swvip VALUES (?, ?, ?)", key, group, days);

        if (rs2 < 1) {
            return null;
        }

        return new SwKey(key, group, days);
    }

    public static boolean delete(String key)
    {
        int rs = br.com.snowdev.swvip.SwVIP.SQLManager().update("DELETE FROM swvip WHERE vip_key = ?", key);

        return (rs > 0);
    }

    public static SwKey findByKey(String key) throws SQLException
    {
        ResultSet rs = br.com.snowdev.swvip.SwVIP.SQLManager().select("SELECT * FROM swvip WHERE vip_key = ?", key);

        if (!rs.next()) {
            return null;
        }

        return new SwKey(
            rs.getString("vip_key"),
            rs.getString("vip_group"),
            rs.getInt("days")
        );
    }

    public static SwKey[] all() throws SQLException
    {
        List<SwKey> swKeysList = new ArrayList<SwKey>();

        ResultSet rs = br.com.snowdev.swvip.SwVIP.SQLManager().select("SELECT * FROM swvip");

        while (rs.next()) {
            SwKey swKey = new SwKey(
                rs.getString("vip_key"),
                rs.getString("vip_group"),
                rs.getInt("days")
            );

            swKeysList.add(swKey);
        }

        return swKeysList.toArray(new SwKey[0]);
    }
}
