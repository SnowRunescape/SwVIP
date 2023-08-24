package br.com.snowdev.swvip.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.snowdev.swvip.entities.Vip;

public class VipModel
{
    public static void migrate()
    {
        br.com.snowdev.swvip.SwVIP.SQLManager().update("CREATE TABLE IF NOT EXISTS `sw_vips` (`id` INT AUTO_INCREMENT PRIMARY KEY, `username` VARCHAR(16) NOT NULL, `group` VARCHAR(32) NOT NULL, `expires_at` DATETIME NOT NULL, `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP, `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP);");
    }

    public static Vip[] getAllByUsername(String username) throws SQLException
    {
        List<Vip> swKeysList = new ArrayList<Vip>();

        ResultSet rs = br.com.snowdev.swvip.SwVIP.SQLManager().select("SELECT * FROM `sw_vips` WHERE `username` = ?", username);

        while (rs.next()) {
            Vip swKey = new Vip(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("group")
            );

            swKeysList.add(swKey);
        }

        return swKeysList.toArray(new Vip[0]);
    }
}