package br.com.snowdev.swvip.utilities;

import java.util.Random;

import br.com.snowdev.swvip.SwVIP;

public class KeyHelper
{
    public static String genKey()
    {
        Random random = new Random();

        int max = SwVIP.instance.getConfig().getInt("SwVIP.key_length");

        String key = "";

        if ((max < 6) || (max > 12)) {
            max = 10;
        }

        for (int c = 0; c < max; c++) {
            key += String.valueOf(random.nextInt(10));
        }

        return key;
    }
}
