package br.com.snowdev.swvip;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

import br.com.snowdev.swvip.commands.MainCommand;

public class OnEnable {
    public void enabled()
    {
        if(!new File(SwVIP.instance.getDataFolder(), "config.yml").exists()) {
            SwVIP.instance.saveDefaultConfig();
        }

        this.loadMessages();

        if (SwVIP.instance.getConfig().getBoolean("database.enable")) {
            SwVIP.SQLManager();
            SwVIP.flatFile =  false;
        }

        this.registerCommands();
    }

    private void loadMessages()
    {
        String language = SwVIP.instance.getConfig().getString("SwVIP.language", "en").trim();

        String fileLanguage = "language_" + language + ".yml";

        File resourceMessage = new File(SwVIP.instance.getDataFolder(), fileLanguage);

        if (!resourceMessage.exists()) {
            SwVIP.instance.saveResource(fileLanguage, false);
        } else {
            resourceMessage = new File(SwVIP.instance.getDataFolder(), "language_en.yml");

            if (!resourceMessage.exists()) {
                SwVIP.instance.saveResource("language_en.yml", false);
            }
        }

        SwVIP.instance.ResourceMessage = YamlConfiguration.loadConfiguration(resourceMessage);
    }

    private void registerCommands()
    {
        MainCommand mainCommand = new MainCommand();

        SwVIP.instance.getCommand("swvip").setExecutor(mainCommand);
        SwVIP.instance.getCommand("keys").setExecutor(mainCommand);
        SwVIP.instance.getCommand("newkey").setExecutor(mainCommand);
        SwVIP.instance.getCommand("deletekey").setExecutor(mainCommand);
        SwVIP.instance.getCommand("usekey").setExecutor(mainCommand);
        SwVIP.instance.getCommand("viptime").setExecutor(mainCommand);
        SwVIP.instance.getCommand("changevip").setExecutor(mainCommand);
        SwVIP.instance.getCommand("givevip").setExecutor(mainCommand);
        SwVIP.instance.getCommand("removevip").setExecutor(mainCommand);
    }
}
