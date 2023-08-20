package br.com.snowdev.swvip.commands;

import org.apache.commons.lang.WordUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import br.com.snowdev.swvip.SwKey;
import br.com.snowdev.swvip.SwVIP;
import br.com.snowdev.swvip.services.KeysServices;
import br.com.snowdev.swvip.utilities.Messaging;

public class Keys implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        SwKey[] keys = KeysServices.getKeys();

        if (keys.length > 0) {
            sender.sendMessage(Messaging.format("§fLista de keys:", true, false));

            for (SwKey key : keys) {
                String message = "§fKey: §a{key.code} §f({key.group}) - §a{key.days} §f{words.days}.";
                message = message.replace("{words.days}", WordUtils.capitalize(SwVIP.instance.ResourceMessage.getString("words.days")));
                message = message.replace("{key.code}", key.code);
                message = message.replace("{key.group}", key.group);
                message = message.replace("{key.days}", String.valueOf(key.days));

                sender.sendMessage(Messaging.format(message, false, false));
            }
        } else {
            sender.sendMessage(Messaging.format("error.dont_have_keys", true, true));
        }

        return true;
    }
}