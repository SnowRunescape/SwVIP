package br.com.snowdev.swvip.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.snowdev.swvip.interfaces.CommandPermissions;
import br.com.snowdev.swvip.utilities.Messaging;

@CommandPermissions("swvip.command.swvip")
public class ChangeVip implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Messaging.format("error.cmd_only_player", true, true));
            return false;
        }

        return false;
    }
}