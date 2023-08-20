package br.com.snowdev.swvip.services;

import br.com.snowdev.swvip.SwVIP;

public class RemoveVipServices
{
    public static boolean removeVip(String player)
    {
        return SwVIP.flatFile ?
            RemoveVipServices.removeVipFile(player) :
            RemoveVipServices.removeVipDatabase(player);
    }

    private static boolean removeVipFile(String player)
    {
        return false;
    }

    private static boolean removeVipDatabase(String player)
    {
        return false;
    }
}
