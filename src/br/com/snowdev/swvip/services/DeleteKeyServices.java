package br.com.snowdev.swvip.services;

import java.sql.SQLException;

import br.com.snowdev.swvip.SwVIP;
import br.com.snowdev.swvip.entities.Key;
import br.com.snowdev.swvip.models.KeyModel;

public class DeleteKeyServices
{
    public static boolean deleteKey(String key)
    {
        return SwVIP.flatFile ?
            DeleteKeyServices.deleteKeyFile(key) :
            DeleteKeyServices.deleteKeyDatabase(key);
    }

    private static boolean deleteKeyFile(String key)
    {
        if (SwVIP.instance.getConfig().contains("keys." + key)) {
            SwVIP.instance.getConfig().set("keys." + key, null);
            SwVIP.instance.saveConfig();
            SwVIP.instance.reloadConfig();

            return true;
        }

        return false;
    }

    private static boolean deleteKeyDatabase(String key)
    {
        try {
            Key swkey = KeyModel.findByKey(key);

            if (swkey != null) {
                return KeyModel.delete(key);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
