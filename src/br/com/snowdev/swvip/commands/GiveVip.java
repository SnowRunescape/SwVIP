package br.com.snowdev.swvip.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.snowdev.swvip.interfaces.CommandPermissions;
import br.com.snowdev.swvip.services.GiveVipService;
import br.com.snowdev.swvip.utilities.Messaging;
import br.com.snowdev.swvip.utilities.ParserNumber;

@CommandPermissions("swvip.command.swvip")
public class GiveVip implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if (args.length != 3) {
            String message = "§f/givevip <username> <group> <days>";

            sender.sendMessage(Messaging.format(message, true, false));
            return false;
        }

        String username = args[0].toLowerCase();

        Player player = Bukkit.getPlayer(username);
        String group = args[1];
        int days = ParserNumber.parseInt(args[2]);

        if (GiveVipService.giveVIP(player, group, days) != null) {
            sender.sendMessage(Messaging.format("Erro ao dar o Vip", true, false));
            return false;
        }

        return true;
    }
}