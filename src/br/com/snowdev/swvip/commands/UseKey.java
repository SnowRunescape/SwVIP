package br.com.snowdev.swvip.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.snowdev.swvip.SwVIP;
import br.com.snowdev.swvip.services.UseKeyServices;
import br.com.snowdev.swvip.utilities.Messaging;

public class UseKey implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        Player player = (Player) sender;

        if (args.length != 1) {
            String message = "§f/usarkey <key>";
            message = message.replace("{words.days}", SwVIP.instance.ResourceMessage.getString("words.days"));
            message = message.replace("{words.group}", SwVIP.instance.ResourceMessage.getString("words.group"));

            sender.sendMessage(Messaging.format(message, true, false));
            return false;
        }

        String key = args[0].toUpperCase();

        if (SwVIP.instance.using_codes.containsKey(key)) {
            player.sendMessage(Messaging.format("error.key_being_processed", true, true));
            return false;
        }

        SwVIP.instance.using_codes.put(key, "");

        boolean result = UseKeyServices.useKey(player, key);

        SwVIP.instance.using_codes.remove(key);

        return result;
    }
}