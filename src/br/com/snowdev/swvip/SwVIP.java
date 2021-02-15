package br.com.snowdev.swvip;

import java.io.File;
import java.util.Random;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import br.com.snowdev.swvip.SwVIP;
import br.com.snowdev.swvip.commands.MainCommand;
import br.com.snowdev.swvip.storage.SQLManager;

public class SwVIP extends JavaPlugin {
	private static SQLManager SQLManager;
	
	public YamlConfiguration ResourceMessage;
	
	public static Boolean flatFile = true;
	public static SwVIP instance;
	
	public void onEnable(){
		instance = this;
		
		if(!new File(getDataFolder(), "config.yml").exists()) saveDefaultConfig();
		
		this.loadMessages();
		
		MainCommand MainCommand = new MainCommand();
		
		getCommand("swvip").setExecutor(MainCommand);
		getCommand("newkey").setExecutor(MainCommand);
		getCommand("deletekey").setExecutor(MainCommand);
		getCommand("usekey").setExecutor(MainCommand);
		getCommand("viptime").setExecutor(MainCommand);
		getCommand("changevip").setExecutor(MainCommand);
		getCommand("givevip").setExecutor(MainCommand);
		getCommand("removevip").setExecutor(MainCommand);
	}
	
	public static String FormatKey(){
		Random n = new Random();
		
		String key = "";
		
		int tmax = SwVIP.instance.getConfig().getInt("SwVIP.key_length");
		
		if((tmax < 6) || (tmax > 12)){
			tmax = 10;
		}
		
		for(int c = 0; c < tmax; c++){
			key += String.valueOf(n.nextInt(10));
		}
		
		return key;
	}
	
	
    private void loadMessages(){
    	File resourceMessage = new File(getDataFolder(), "messages.yml");
    	
    	if(!resourceMessage.exists()) saveResource("messages.yml", false);
    	
    	this.ResourceMessage = YamlConfiguration.loadConfiguration(resourceMessage);
    }

	public static SQLManager SQLManager(){
		if(SwVIP.SQLManager == null){
			String host = SwVIP.instance.getConfig().getString("database.host");
			String user= SwVIP.instance.getConfig().getString("database.user");
			String pass= SwVIP.instance.getConfig().getString("database.pass");
			String db = SwVIP.instance.getConfig().getString("database.db");
			
			SwVIP.SQLManager = new SQLManager(user, pass, host, db);
		}
		
		return SwVIP.SQLManager;
	}
}
