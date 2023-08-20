package br.com.snowdev.swvip.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.com.snowdev.swvip.SwVIP;
import br.com.snowdev.swvip.entities.Key;

public class KeysServices
{
    public static Key[] getKeys()
    {
        return SwVIP.flatFile ?
            KeysServices.getKeysFile() :
            KeysServices.getKeysDatabase();
    }

    private static Key[] getKeysFile()
    {
        List<Key> swKeysList = new ArrayList<Key>();

        if (SwVIP.instance.getConfig().contains("keys")) {
            Set<String> keys = SwVIP.instance.getConfig().getConfigurationSection("keys").getKeys(false);

            if (keys.size() > 0) {
                for (String key : keys) {
                    String group = SwVIP.instance.getConfig().getString("keys." + key).split(",")[0];
                    int days = Integer.valueOf(SwVIP.instance.getConfig().getString("keys." + key).split(",")[1]);

                    swKeysList.add(new Key(key, group, days));
                }
            }
        }

        return swKeysList.toArray(new Key[0]);
    }

    private static Key[] getKeysDatabase()
    {
        try {
            return br.com.snowdev.swvip.models.KeyModel.all();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
