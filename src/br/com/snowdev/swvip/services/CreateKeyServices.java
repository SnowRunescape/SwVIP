package br.com.snowdev.swvip.services;

import java.sql.SQLException;

import br.com.snowdev.swvip.SwKey;
import br.com.snowdev.swvip.SwVIP;

public class CreateKeyServices
{
    public static SwKey createKey(String group, int days)
    {
        return SwVIP.flatFile ?
            CreateKeyServices.createKeyFile(group, days) :
            CreateKeyServices.createKeyDatabase(group, days);
    }

    private static SwKey createKeyFile(String group, int days)
    {
        String key = SwVIP.FormatKey();

        while (SwVIP.instance.getConfig().contains("keys." + key)) {
            key = SwVIP.FormatKey();
        }

        SwVIP.instance.getConfig().set("keys." + key, group + ", " + Integer.toString(days));
        SwVIP.instance.saveConfig();
        SwVIP.instance.reloadConfig();

        return new SwKey(key, group, days);
    }

    private static SwKey createKeyDatabase(String group, int days)
    {
        String key = SwVIP.FormatKey();

        try {
            while (true) {
                SwKey swkey = br.com.snowdev.swvip.models.SwVIP.findByKey(key);

                if (swkey == null) {
                    return br.com.snowdev.swvip.models.SwVIP.create(key, group, days);
                }

                key = SwVIP.FormatKey();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
