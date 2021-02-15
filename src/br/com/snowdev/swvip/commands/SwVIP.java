package br.com.snowdev.swvip.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import br.com.snowdev.swvip.utilities.Messaging;

public class SwVIP implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		sender.sendMessage(Messaging.format("§bComandos do SwVIP:", true, false));
		
		if(sender.hasPermission("swvip.usekey") || sender.hasPermission("swvip.user") || sender.hasPermission("swvip.admin")) {
			sender.sendMessage(Messaging.format("§b/usarkey §f- Utiliza uma key VIP.", false, false));
		}
		
		if(sender.hasPermission("swvip.viptime") || sender.hasPermission("swvip.user") || sender.hasPermission("swvip.admin")) {
			sender.sendMessage(Messaging.format("§b/tempovip §f- Mostra o ultimo dia de seu VIP.", false, false));
		}
		
		if(sender.hasPermission("swvip.changevip") || sender.hasPermission("swvip.user") || sender.hasPermission("swvip.admin")) {
			sender.sendMessage(Messaging.format("§b/trocarvip §f- Muda o VIP que você está usando.", false, false));
		}
		
		if(sender.hasPermission("swvip.keys") || sender.hasPermission("swvip.admin")) {
			sender.sendMessage(Messaging.format("§b/keys §f- Lista as keys disponiveis.", false, false));
		}
		
		if(sender.hasPermission("swvip.newkey") || sender.hasPermission("swvip.admin")) {
			sender.sendMessage(Messaging.format("§b/gerarkey §f- Gera uma key com X dias de VIP.", false, false));
		}
		
		if(sender.hasPermission("swvip.delelekey") || sender.hasPermission("swvip.admin")) {
			sender.sendMessage(Messaging.format("§b/apagarkey §f- Apaga apenas uma key.", false, false));
		}
		
		if(sender.hasPermission("swvip.givevip") || sender.hasPermission("swvip.admin")) {
			sender.sendMessage(Messaging.format("§b/darvip §f- Da VIP sem o uso de uma key.", false, false));
		}
		
		if(sender.hasPermission("swvip.removevip") || sender.hasPermission("swvip.admin")) {
			sender.sendMessage(Messaging.format("§b/tirarvip §f- Tira o VIP de um jogador.", false, false));
		}
		
		return false;
	}
}