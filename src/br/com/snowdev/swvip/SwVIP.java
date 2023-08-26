package br.com.snowdev.swvip;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import br.com.snowdev.swvip.SwVIP;
import br.com.snowdev.swvip.models.KeyModel;
import br.com.snowdev.swvip.models.VipModel;
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

        (new OnEnable()).enabled();
    }

    public static String FormatKey()
    {
        Random random = new Random();

        int tmax = SwVIP.instance.getConfig().getInt("SwVIP.key_length");

        String key = "";

        if ((tmax < 6) || (tmax > 12)) {
            tmax = 10;
        }

        for (int c = 0; c < tmax; c++) {
            key += String.valueOf(random.nextInt(10));
        }

        return key;
    }

    public Boolean giveVIP(Player p, String Group, int days)
    {
        return false;
    }

    public static SQLManager SQLManager()
    {
        if (SwVIP.SQLManager == null) {
            String host = SwVIP.instance.getConfig().getString("database.host");
            String user = SwVIP.instance.getConfig().getString("database.user");
            String pass = SwVIP.instance.getConfig().getString("database.pass");
            String db = SwVIP.instance.getConfig().getString("database.database");

            SwVIP.SQLManager = new SQLManager(user, pass, host, db);

            KeyModel.migrate();
            VipModel.migrate();
        }

        return SwVIP.SQLManager;
    }
}