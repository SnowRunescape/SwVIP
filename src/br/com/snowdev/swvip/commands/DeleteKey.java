package br.com.snowdev.swvip.commands;

import java.sql.SQLException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import br.com.snowdev.swvip.SwKey;
import br.com.snowdev.swvip.SwVIP;
import br.com.snowdev.swvip.interfaces.CommandPermissions;
import br.com.snowdev.swvip.utilities.Messaging;

@CommandPermissions("swvip.command.admin.deletekey")
public class DeleteKey implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if (args.length == 1) {
            String key = args[0].toUpperCase();

            if (this.deleteKey(key)) {
                String message = SwVIP.instance.ResourceMessage.getString("success.delete_key");
                message = message.replace("{key.code}", key);

                sender.sendMessage(Messaging.format(message, true, false));
            } else {
                sender.sendMessage(Messaging.format("error.internal_error", true, true));
            }
        } else {
            String message = "§f/tirarvip <key>";

            sender.sendMessage(Messaging.format(message, true, false));
        }

        return false;
    }

    private Boolean deleteKey(String key)
    {
        if (SwVIP.flatFile) {
            if (SwVIP.instance.getConfig().contains("keys." + key)) {
                SwVIP.instance.getConfig().set("keys." + key, null);
                SwVIP.instance.saveConfig();
                SwVIP.instance.reloadConfig();

                return true;
            }
        } else {
            try {
                SwKey swkey = br.com.snowdev.swvip.models.SwVIP.findByKey(key);

                if (swkey != null) {
                    return br.com.snowdev.swvip.models.SwVIP.delete(key);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}