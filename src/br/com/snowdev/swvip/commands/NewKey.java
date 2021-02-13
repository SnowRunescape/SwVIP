package br.com.snowdev.swvip.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import br.com.snowdev.swvip.interfaces.CommandPermissions;

@CommandPermissions("swvip.command.admin.newkey")
public class NewKey implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		return false;
	}
}
