package br.com.snowdev.swvip.services;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import br.com.snowdev.swvip.SwVIP;
import br.com.snowdev.swvip.entities.Vip;
import br.com.snowdev.swvip.models.VipModel;

public class GiveVipService
{
    public static Vip giveVIP(Player player, String group, int days)
    {
        return SwVIP.flatFile ?
            GiveVipService.giveVipFile(player, group, days) :
            GiveVipService.giveVipDatabase(player, group, days);
    }

    private static Vip giveVipFile(Player player, String group, int days)
    {
        return null;
    }

    private static Vip giveVipDatabase(Player player, String group, int days)
    {
        Vip vip = VipModel.createOrUpdate(player.getName().toLowerCase(), group, "" + days);

        if (vip == null) {
            return null;
        }

        GiveVipService.addGroup(player, group);

        return vip;
    }

    private static void addGroup(Player player, String group)
    {
        String commandAddGroup = SwVIP.instance.getConfig().getString("cmd_add_group");
        commandAddGroup = commandAddGroup.replace("{player}", player.getName());
        commandAddGroup = commandAddGroup.replace("{group}", group);

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commandAddGroup);
    }
}
