package br.com.snowdev.swvip.commands;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.WordUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import br.com.snowdev.swvip.SwKey;
import br.com.snowdev.swvip.SwVIP;
import br.com.snowdev.swvip.utilities.Messaging;

public class Keys implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		List<SwKey> keys = this.listKeys();
		
		if(keys.size() > 0){
			sender.sendMessage(Messaging.format("§fLista de keys:", true, false));
			
			for(SwKey key : keys){
				String message = "§fKey: §a{key.code} §f({key.group}) - §a{key.days} §f{words.days}.";
				message.replace("{words.days}", WordUtils.capitalize(SwVIP.instance.ResourceMessage.getString("words.days")));
				message.replace("{key.code}", key.code);
				message.replace("{key.group}", key.group);
				message.replace("{key.days}", String.valueOf(key.days));
				
				sender.sendMessage(Messaging.format(message, false, false));
			}
		} else {
			sender.sendMessage(Messaging.format("error.dont_have_keys", true, true));
		}
		
		return false;
	}
	
	private List<SwKey> listKeys(){
		List<SwKey> listKeys = new ArrayList<SwKey>();
		
		if(SwVIP.flatFile){
			if(SwVIP.instance.getConfig().contains("keys")){
				Set<String> keys = SwVIP.instance.getConfig().getConfigurationSection("keys").getKeys(false);
				
				if(keys.size() > 0){
					for(String key : keys){
						String group = SwVIP.instance.getConfig().getString("keys." + key).split(",")[0];
						int days = Integer.valueOf(SwVIP.instance.getConfig().getString("keys." + key).split(",")[1]);
						
						listKeys.add(new SwKey(key, group, days));
					}
				}
			}
		} else {
			try {
				ResultSet rs = SwVIP.SQLManager().select("SELECT * FROM swvip");
				
				while(rs.next()){
					String key = rs.getString("key");
					String group = rs.getString("group");
					int days = rs.getInt("days");
					
					listKeys.add(new SwKey(key, group, days));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return listKeys;
	}
}