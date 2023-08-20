package br.com.snowdev.swvip.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import br.com.snowdev.swvip.interfaces.CommandPermissions;
import br.com.snowdev.swvip.services.RemoveVipServices;
import br.com.snowdev.swvip.utilities.Messaging;

@CommandPermissions("swvip.command.admin.removevip")
public class RemoveVip implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if (args.length != 1 && args.length != 2) {
            String message = "§f/removevip <player> <group>";

            sender.sendMessage(Messaging.format(message, true, false));
            return false;
        }

        String player = args[0].toLowerCase();

        if (!RemoveVipServices.removeVip(player)) {
            String message = "§f/Falha ao remover o VIP do jogador " + player;
            sender.sendMessage(Messaging.format(message, true, false));
            return false;
        }

        return true;
    }
}