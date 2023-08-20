package br.com.snowdev.swvip;

import java.io.File;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import br.com.snowdev.swvip.SwVIP;
import br.com.snowdev.swvip.commands.MainCommand;
import br.com.snowdev.swvip.storage.SQLManager;

public class SwVIP extends JavaPlugin
{
    private static SQLManager SQLManager;

    public YamlConfiguration ResourceMessage;

    public HashMap<String, String> using_codes = new HashMap<String, String>();

    public static Boolean flatFile = true;
    public static SwVIP instance;

    public void onEnable()
    {
        instance = this;

        if(!new File(getDataFolder(), "config.yml").exists()) saveDefaultConfig();

        this.loadMessages();

        if (SwVIP.instance.getConfig().getBoolean("database.enable")) {
            SwVIP.SQLManager();
            SwVIP.flatFile =  false;
        }

        MainCommand MainCommand = new MainCommand();

        getCommand("swvip").setExecutor(MainCommand);
        getCommand("keys").setExecutor(MainCommand);
        getCommand("newkey").setExecutor(MainCommand);
        getCommand("deletekey").setExecutor(MainCommand);
        getCommand("usekey").setExecutor(MainCommand);
        getCommand("viptime").setExecutor(MainCommand);
        getCommand("changevip").setExecutor(MainCommand);
        getCommand("givevip").setExecutor(MainCommand);
        getCommand("removevip").setExecutor(MainCommand);
    }

    public static String FormatKey()
    {
        Random n = new Random();

        int tmax = SwVIP.instance.getConfig().getInt("SwVIP.key_length");

        String key = "";

        if ((tmax < 6) || (tmax > 12)) {
            tmax = 10;
        }

        for (int c = 0; c < tmax; c++) {
            key += String.valueOf(n.nextInt(10));
        }

        return key;
    }

    public Boolean giveVIP(Player p, String Group, int days)
    {
        return false;
    }

    private void loadMessages()
    {
        String language = getConfig().getString("SwVIP.language", "en").trim();

        String fileLanguage = "language_" + language + ".yml";

        File resourceMessage = new File(getDataFolder(), fileLanguage);

        if (!resourceMessage.exists()) {
            saveResource(fileLanguage, false);
        } else {
            resourceMessage = new File(getDataFolder(), "language_en.yml");

            if (!resourceMessage.exists()) {
                saveResource("language_en.yml", false);
            }
        }

        this.ResourceMessage = YamlConfiguration.loadConfiguration(resourceMessage);
    }

    public static SQLManager SQLManager()
    {
        if (SwVIP.SQLManager == null) {
            String host = SwVIP.instance.getConfig().getString("database.host");
            String user = SwVIP.instance.getConfig().getString("database.user");
            String pass = SwVIP.instance.getConfig().getString("database.pass");
            String db = SwVIP.instance.getConfig().getString("database.database");

            SwVIP.SQLManager = new SQLManager(user, pass, host, db);

            br.com.snowdev.swvip.models.KeyModel.migrate();
        }

        return SwVIP.SQLManager;
    }
}