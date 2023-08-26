package br.com.snowdev.swvip;

import java.util.HashMap;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import br.com.snowdev.swvip.SwVIP;
import br.com.snowdev.swvip.database.Database;
import br.com.snowdev.swvip.models.KeyModel;
import br.com.snowdev.swvip.models.VipModel;

public class SwVIP extends JavaPlugin
{
    private static Database SQLManager;

    public YamlConfiguration ResourceMessage;

    public HashMap<String, String> using_codes = new HashMap<String, String>();

    public static Boolean flatFile = true;
    public static SwVIP instance;

    public void onEnable()
    {
        instance = this;

        (new OnEnable()).enabled();
    }

    public static Database SQLManager()
    {
        if (SwVIP.SQLManager == null) {
            String host = SwVIP.instance.getConfig().getString("database.host");
            String user = SwVIP.instance.getConfig().getString("database.user");
            String pass = SwVIP.instance.getConfig().getString("database.pass");
            String db = SwVIP.instance.getConfig().getString("database.database");

            SwVIP.SQLManager = new Database(user, pass, host, db);

            KeyModel.migrate();
            VipModel.migrate();
        }

        return SwVIP.SQLManager;
    }
}