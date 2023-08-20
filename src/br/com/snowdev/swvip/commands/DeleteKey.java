package br.com.snowdev.swvip.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import br.com.snowdev.swvip.SwVIP;
import br.com.snowdev.swvip.interfaces.CommandPermissions;
import br.com.snowdev.swvip.services.DeleteKeyServices;
import br.com.snowdev.swvip.utilities.Messaging;

@CommandPermissions("swvip.command.admin.deletekey")
public class DeleteKey implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if (args.length != 1) {
            String message = "§f/tirarvip <key>";

            sender.sendMessage(Messaging.format(message, true, false));
            return false;
        }

        String key = args[0].toUpperCase();

        if (!DeleteKeyServices.deleteKey(key)) {
            sender.sendMessage(Messaging.format("error.internal_error", true, true));
            return false;
        }

        String message = SwVIP.instance.ResourceMessage.getString("success.delete_key");
        message = message.replace("{key.code}", key);

        sender.sendMessage(Messaging.format(message, true, false));

        return true;
    }
}