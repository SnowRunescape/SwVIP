package br.com.snowdev.swvip.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.com.snowdev.swvip.SwKey;
import br.com.snowdev.swvip.SwVIP;

public class KeysServices
{
    public static SwKey[] getKeys()
    {
        return SwVIP.flatFile ?
            KeysServices.getKeysFile() :
            KeysServices.getKeysDatabase();
    }

    private static SwKey[] getKeysFile()
    {
        List<SwKey> swKeysList = new ArrayList<SwKey>();

        if (SwVIP.instance.getConfig().contains("keys")) {
            Set<String> keys = SwVIP.instance.getConfig().getConfigurationSection("keys").getKeys(false);

            if (keys.size() > 0) {
                for (String key : keys) {
                    String group = SwVIP.instance.getConfig().getString("keys." + key).split(",")[0];
                    int days = Integer.valueOf(SwVIP.instance.getConfig().getString("keys." + key).split(",")[1]);

                    swKeysList.add(new SwKey(key, group, days));
                }
            }
        }

        return swKeysList.toArray(new SwKey[0]);
    }

    private static SwKey[] getKeysDatabase()
    {
        try {
            return br.com.snowdev.swvip.models.SwVIP.all();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
