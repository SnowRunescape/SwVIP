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
        br.com.snowdev.swvip.SwVIP.SQLManager().update("CREATE TABLE IF NOT EXISTS `sw_keys` (`id` INT AUTO_INCREMENT PRIMARY KEY, `key` VARCHAR(16) NOT NULL, `group` VARCHAR(32) NOT NULL, `days` INT NOT NULL, `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP, `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP);");
    }

    public static Key create(String key, String group, int days)
    {
        int rs = br.com.snowdev.swvip.SwVIP.SQLManager().update("INSERT INTO `sw_keys` (`key`, `group`, `days`) VALUES (?, ?, ?)", key, group, days);

        if (rs < 1) {
            return null;
        }

        return new Key(key, group, days);
    }

    public static boolean delete(String key)
    {
        int rs = br.com.snowdev.swvip.SwVIP.SQLManager().update("DELETE FROM `sw_keys` WHERE `key` = ?", key);

        return (rs > 0);
    }

    public static Key findByKey(String key) throws SQLException
    {
        ResultSet result = br.com.snowdev.swvip.SwVIP.SQLManager().select("SELECT * FROM `sw_keys` WHERE `key` = ?", key);

        if (!result.next()) {
            return null;
        }

        return Key.buildFromResultSet(result);
    }

    public static Key[] all() throws SQLException
    {
        List<Key> keys = new ArrayList<Key>();

        ResultSet result = br.com.snowdev.swvip.SwVIP.SQLManager().select("SELECT * FROM `sw_keys`");

        while (result.next()) {
            keys.add(Key.buildFromResultSet(result));
        }

        return keys.toArray(new Key[0]);
    }
}
