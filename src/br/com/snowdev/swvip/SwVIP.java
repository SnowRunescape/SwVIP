package br.com.snowdev.swvip;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import br.com.snowdev.commands.MainCommand;

public class SwVIP extends JavaPlugin {
	public YamlConfiguration ResourceMessage;
	
	public static SwVIP instance;
	
	public void onEnable(){
		instance = this;
		
		if(!new File(getDataFolder(), "config.yml").exists()) saveDefaultConfig();
		
		this.loadMessages();
		
		getCommand("resgatarvip").setExecutor(new MainCommand());
	}
	
    private void loadMessages(){
    	File resourceMessage = new File(getDataFolder(), "messages.yml");
    	
    	if(!resourceMessage.exists()) saveResource("messages.yml", false);
    	
    	this.ResourceMessage = YamlConfiguration.loadConfiguration(resourceMessage);
    }
}