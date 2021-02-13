package br.com.snowdev.swvip.commands;

import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.collect.Maps;

import br.com.snowdev.swvip.interfaces.CommandPermissions;
import br.com.snowdev.swvip.utilities.Messaging;

public class MainCommand implements CommandExecutor {
	private Map<String, CommandExecutor> CommandMap = Maps.newHashMap();
	
	public MainCommand(){
		CommandMap.put("swvip", new SwVIP());
		CommandMap.put("newkey", new SwVIP());
		CommandMap.put("usekey", new SwVIP());
		CommandMap.put("viptime", new SwVIP());
		CommandMap.put("changevip", new SwVIP());
		CommandMap.put("givevip", new SwVIP());
		CommandMap.put("removevip", new SwVIP());
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		Player player = (Player) sender;
		commandLabel = commandLabel.toLowerCase();
		
		if(CommandMap.containsKey(commandLabel)){
			CommandExecutor command = CommandMap.get(commandLabel);

			if(!hasPermission(player, command)){
				player.sendMessage(Messaging.format("error.insufficient-permissions", true, true));

				return true;
			}

			return command.onCommand(sender, cmd, commandLabel, args);
		}
		
		return false;
	}
	
	private boolean hasPermission(Player bukkitPlayer, CommandExecutor cmd){
		CommandPermissions permissions = cmd.getClass().getAnnotation(CommandPermissions.class);
		
        if(permissions == null) return true;

        for(String permission : permissions.value()){
            if(bukkitPlayer.hasPermission(permission)){
            	return true;
            }
        }

        return false;
    }
}
