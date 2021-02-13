package br.com.snowdev.swvip;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import br.com.snowdev.swvip.commands.MainCommand;

public class SwVIP extends JavaPlugin {
	public YamlConfiguration ResourceMessage;
	
	public static SwVIP instance;
	
	public void onEnable(){
		instance = this;
		
		if(!new File(getDataFolder(), "config.yml").exists()) saveDefaultConfig();
		
		this.loadMessages();
		
		MainCommand MainCommand = new MainCommand();
		
		getCommand("swvip").setExecutor(MainCommand);
		getCommand("newkey").setExecutor(MainCommand);
		getCommand("usekey").setExecutor(MainCommand);
		getCommand("viptime").setExecutor(MainCommand);
		getCommand("changevip").setExecutor(MainCommand);
		getCommand("givevip").setExecutor(MainCommand);
		getCommand("removevip").setExecutor(MainCommand);
	}
	
    private void loadMessages(){
    	File resourceMessage = new File(getDataFolder(), "messages.yml");
    	
    	if(!resourceMessage.exists()) saveResource("messages.yml", false);
    	
    	this.ResourceMessage = YamlConfiguration.loadConfiguration(resourceMessage);
    }
}