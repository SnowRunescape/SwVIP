package br.com.snowdev.swvip.services;

import java.sql.SQLException;

import br.com.snowdev.swvip.SwVIP;
import br.com.snowdev.swvip.entities.Key;
import br.com.snowdev.swvip.models.KeyModel;

public class CreateKeyServices
{
    public static Key createKey(String group, int days)
    {
        return SwVIP.flatFile ?
            CreateKeyServices.createKeyFile(group, days) :
            CreateKeyServices.createKeyDatabase(group, days);
    }

    private static Key createKeyFile(String group, int days)
    {
        String key = SwVIP.FormatKey();

        while (SwVIP.instance.getConfig().contains("keys." + key)) {
            key = SwVIP.FormatKey();
        }

        SwVIP.instance.getConfig().set("keys." + key, group + ", " + Integer.toString(days));
        SwVIP.instance.saveConfig();
        SwVIP.instance.reloadConfig();

        return new Key(key, group, days);
    }

    private static Key createKeyDatabase(String group, int days)
    {
        String key = SwVIP.FormatKey();

        try {
            while (true) {
                Key swkey = KeyModel.findByKey(key);

                if (swkey == null) {
                    return KeyModel.create(key, group, days);
                }

                key = SwVIP.FormatKey();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
