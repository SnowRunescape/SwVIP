package br.com.snowdev.swvip.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.snowdev.swvip.SwVIP;
import br.com.snowdev.swvip.entities.Vip;

public class VipModel
{
    public static void migrate()
    {
        SwVIP.SQLManager().update("CREATE TABLE IF NOT EXISTS `sw_vips` (`id` INT AUTO_INCREMENT PRIMARY KEY, `username` VARCHAR(16) NOT NULL, `group` VARCHAR(32) NOT NULL, `expires_at` DATETIME NOT NULL, `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP, `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP);");
    }

    public static Vip createOrUpdate(String username, String group, String expiresAt)
    {
        int rs = SwVIP.SQLManager().update("INSERT INTO `sw_vips` (`username`, `group`, `expires_at`) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE `expires_at` = VALUES(`expires_at`)", username, group, expiresAt);

        if (rs < 1) {
            return null;
        }

        return new Vip(0, username, group);
    }

    public static Vip[] getAllByUsername(String username) throws SQLException
    {
        List<Vip> vips = new ArrayList<Vip>();

        ResultSet result = SwVIP.SQLManager().select("SELECT * FROM `sw_vips` WHERE `username` = ?", username);

        while (result.next()) {
            vips.add(Vip.buildFromResultSet(result));
        }

        return vips.toArray(new Vip[0]);
    }
}