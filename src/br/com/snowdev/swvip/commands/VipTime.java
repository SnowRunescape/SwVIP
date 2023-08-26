package br.com.snowdev.swvip.commands;

import java.sql.SQLException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.snowdev.swvip.entities.Vip;
import br.com.snowdev.swvip.models.VipModel;
import br.com.snowdev.swvip.utilities.Messaging;

public class VipTime implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Messaging.format("error.cmd_only_player", true, true));
            return false;
        }

        Player player = (Player) sender;

        try {
            Vip[] vips = VipModel.getAllByUsername(player.getName());

            if (vips.length < 1) {
                player.sendMessage(Messaging.format("error.dont_have_vip", true, true));
                return true;
            }

            sender.sendMessage(Messaging.format("§fLista de keys:", true, false));

            for (Vip vip : vips) {
                player.sendMessage("§f" + vip.group + " - " + vip + " Dias");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}