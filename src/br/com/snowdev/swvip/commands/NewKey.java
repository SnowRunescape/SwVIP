package br.com.snowdev.swvip.commands;

import org.apache.commons.lang.WordUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import br.com.snowdev.swvip.SwKey;
import br.com.snowdev.swvip.SwVIP;
import br.com.snowdev.swvip.interfaces.CommandPermissions;
import br.com.snowdev.swvip.services.CreateKeyServices;
import br.com.snowdev.swvip.utilities.Messaging;
import br.com.snowdev.swvip.utilities.ParserNumber;

@CommandPermissions({"swvip.admin", "swvip.keys"})
public class NewKey implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if (args.length == 2) {
            String group = args[0];

            Boolean group_exists = false;

            for (String iGroup : SwVIP.instance.getConfig().getConfigurationSection("vip_groups").getKeys(false)) {
                if (iGroup.trim().equalsIgnoreCase(group)) {
                    group_exists = true;
                    group = iGroup.trim();
                    break;
                }
            }

            if (group_exists) {
                int days = ParserNumber.parseInt(args[1]);

                if (days > 0) {
                    SwKey key = CreateKeyServices.createKey(group, days);

                    if (key != null) {
                        String message = "§fKey: §a{key.code} §f({key.group}) - §a{key.days} §f{words.days}.";
                        message = message.replace("{words.days}", WordUtils.capitalize(SwVIP.instance.ResourceMessage.getString("words.days")));
                        message = message.replace("{key.code}", key.code);
                        message = message.replace("{key.group}", key.group);
                        message = message.replace("{key.days}", String.valueOf(key.days));

                        sender.sendMessage(Messaging.format(message, true, false));
                    } else {
                        sender.sendMessage(Messaging.format("error.internal_error", true, true));
                    }
                } else {
                    sender.sendMessage(Messaging.format("error.days_invalid", true, true));
                }
            } else {
                sender.sendMessage(Messaging.format("error.group_not_found", true, true));
            }
        } else {
            String message = "§f/gerarkey <{words.group}> <{words.days}>";
            message = message.replace("{words.days}", SwVIP.instance.ResourceMessage.getString("words.days"));
            message = message.replace("{words.group}", SwVIP.instance.ResourceMessage.getString("words.group"));

            sender.sendMessage(Messaging.format(message, true, false));
        }

        return false;
    }
}
