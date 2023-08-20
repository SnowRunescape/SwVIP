package br.com.snowdev.swvip.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.bukkit.entity.Player;

import br.com.snowdev.swvip.SwVIP;
import br.com.snowdev.swvip.utilities.Messaging;

public class UseKeyServices
{
    public static boolean useKey(Player player, String key)
    {
        return SwVIP.flatFile ?
            UseKeyServices.useKeyFile(player, key) :
            UseKeyServices.useKeyDatabase(player, key);
    }

    private static boolean useKeyFile(Player player, String key)
    {
        if (!SwVIP.instance.getConfig().contains("keys." + key)) {
            player.sendMessage(Messaging.format("error.key_not_found", true, true));
            return false;
        }

        String group = SwVIP.instance.getConfig().getString("keys." + key).split(",")[0].trim();
        int days = Integer.parseInt(SwVIP.instance.getConfig().getString("keys." + key).split(",")[1].trim());

        if (SwVIP.instance.getConfig().contains("vips." + player.getName())) {
            if (SwVIP.instance.getConfig().contains("vips." + player.getName() + "." + group)) {
                days = SwVIP.instance.getConfig().getInt("vips." + player.getName() + "." + group) + days;
            }

            SwVIP.instance.getConfig().set("vips." + player.getName() + "." + group, days);
        } else {
            Calendar now = Calendar.getInstance();

            SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");

            SwVIP.instance.getConfig().set("vips." + player.getName() + ".inicio", fmt.format(now.getTime()));
            SwVIP.instance.getConfig().set("vips." + player.getName() + ".usando", group);
            SwVIP.instance.getConfig().set("vips." + player.getName() + "." + group, days);
            SwVIP.instance.saveConfig();

            SwVIP.instance.giveVIP(player, group, days);
        }

        return true;
    }

    private static boolean useKeyDatabase(Player player, String key)
    {
        return false;
    }
}
