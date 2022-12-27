package br.com.snowdev.swvip.commands;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.snowdev.swvip.SwVIP;
import br.com.snowdev.swvip.utilities.Messaging;

public class UseKey implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        Player p = (Player) sender;

        if (args.length==1) {
            String key = args[0].toUpperCase();

            if (!SwVIP.instance.using_codes.containsKey(key)) {
                SwVIP.instance.using_codes.put(key,"");

                this.processKeyVIP(p, key);

                SwVIP.instance.using_codes.remove(key);
            } else {
                p.sendMessage(Messaging.format("error.key_being_processed", true, true));
            }
        } else {
            String message = "§f/usarkey <key>";
            message = message.replace("{words.days}", SwVIP.instance.ResourceMessage.getString("words.days"));
            message = message.replace("{words.group}", SwVIP.instance.ResourceMessage.getString("words.group"));

            sender.sendMessage(Messaging.format(message, true, false));
        }

        return false;
    }

    private void processKeyVIP(Player p, String key)
    {
        if (SwVIP.flatFile) {
            if (SwVIP.instance.getConfig().contains("keys." + key)) {
                String group = SwVIP.instance.getConfig().getString("keys." + key).split(",")[0].trim();
                int days = Integer.parseInt(SwVIP.instance.getConfig().getString("keys." + key).split(",")[1].trim());

                if (SwVIP.instance.getConfig().contains("vips." + p.getName())) {
                    if (SwVIP.instance.getConfig().contains("vips." + p.getName() + "." + group)) {
                        SwVIP.instance.getConfig().set("vips." + p.getName() + "." + group, (SwVIP.instance.getConfig().getInt("vips." + p.getName() + "." + group) + days));
                    } else {
                        SwVIP.instance.getConfig().set("vips." + p.getName() + "." + group, days);
                    }
                } else {
                    Calendar now = Calendar.getInstance();

                    SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");

                    SwVIP.instance.getConfig().set("vips." + p.getName() + ".inicio", fmt.format(now.getTime()));
                    SwVIP.instance.getConfig().set("vips." + p.getName() + ".usando", group);
                    SwVIP.instance.getConfig().set("vips." + p.getName() + "." + group, days);
                    SwVIP.instance.saveConfig();

                    SwVIP.instance.giveVIP(p, group, days);
                }
            } else {
                p.sendMessage(Messaging.format("error.key_not_found", true, true));
            }
        } else {

        }
    }
}