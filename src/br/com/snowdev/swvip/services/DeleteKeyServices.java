package br.com.snowdev.swvip.services;

import java.sql.SQLException;

import br.com.snowdev.swvip.SwKey;
import br.com.snowdev.swvip.SwVIP;

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
            SwKey swkey = br.com.snowdev.swvip.models.SwVIP.findByKey(key);

            if (swkey != null) {
                return br.com.snowdev.swvip.models.SwVIP.delete(key);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
