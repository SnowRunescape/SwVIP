package br.com.snowdev.swvip.utilities;

import org.bukkit.ChatColor;

import br.com.snowdev.swvip.SwVIP;

public class Messaging
{
    public static String format(String message, Boolean usePrefix, Boolean getConfigMessage)
    {
        String newMessage = "";

        if (message == null || message.isEmpty()) {
            return "";
        }

        if (usePrefix) {
            newMessage += SwVIP.instance.getConfig().getString("prefix", "&b[SwVIP] &f");
        }

        if (getConfigMessage) {
            if (SwVIP.instance.ResourceMessage.getString(message) != null) {
                newMessage += SwVIP.instance.ResourceMessage.getString(message);
            } else {
                newMessage = "§b[SwVIP] §c" + message + " Not Found.";
            }
        } else {
            newMessage += message;
        }

        return ChatColor.translateAlternateColorCodes('&', newMessage);
    }
}
