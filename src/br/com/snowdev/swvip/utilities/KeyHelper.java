package br.com.snowdev.swvip.utilities;

import java.util.Random;

import br.com.snowdev.swvip.SwVIP;

public class KeyHelper
{
    public static String genKey()
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
}
