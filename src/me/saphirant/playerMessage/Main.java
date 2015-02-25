package me.saphirant.playerMessage;


import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	

	@Override
	public void onDisable() {
		
		
	}
	
	@Override
	public void onEnable() {
	PluginManager pm = getServer().getPluginManager();
	pm.registerEvents(this, this);
		
	}
	
	@EventHandler
	public boolean onCommand(CommandSender sender,Command cmd,String cl,String[]args){
		Player p = (Player) sender;
		
		if(cl.equalsIgnoreCase("setjoin")){
			if(args != null){
				FileConfiguration config = this.getConfig();
				config.set("Join." + p.getName(), args);
				this.saveConfig();
				String message = Arrays.asList(args).toString();
				message = message.replace(',', ' ');
				message = message.replace('[', ' ');
				message = message.replace(']', ' ');
				p.sendMessage(ChatColor.GREEN + "Votre message de connection est maintenant : " + message );
			
			}else p.sendMessage(ChatColor.RED + "Veuillez rentrez un message de connection valide");
			
			return true;
		}
		
		if(cl.equalsIgnoreCase("setleave")){
			if(args != null){
				FileConfiguration config = this.getConfig();
				config.set("Leave." + p.getName(), args);
				this.saveConfig();
				String message = Arrays.asList(args).toString();
				message = message.replace(',', ' ');
				message = message.replace('[', ' ');
				message = message.replace(']', ' ');
				p.sendMessage(ChatColor.GREEN + "Votre message de deconnection est maintenant : " + message );
			
			}else p.sendMessage(ChatColor.RED + "Veuillez rentrez un message de deconnection valide");
			
			return true;
		}
		
	
		
		if(cl.equalsIgnoreCase("view")){
			FileConfiguration config = this.getConfig();
			Object ojoin = config.get("Join." + p.getName());
			String join = Arrays.asList(ojoin).toString();
			join = join.replace(',', ' ');
			join = join.replace('[', ' ');
			join = join.replace(']', ' ');
			Object olive = config.get("Leave." + p.getName());
			String leave = Arrays.asList(olive).toString();
			leave = leave.replace(',', ' ');
			leave = leave.replace('[', ' ');
			leave = leave.replace(']', ' ');
			p.sendMessage(ChatColor.DARK_GREEN + "Vos messages de connection/deconnection sont: " + join + " / " + leave);
			
		}
		
		return false;
	}
	@EventHandler
	public void PlayerJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		FileConfiguration config = this.getConfig();
		Object ojoin = config.get("Join." + p.getName());
		String join = Arrays.asList(ojoin).toString();
		join = join.replace(',', ' ');
		join = join.replace('[', ' ');
		join = join.replace(']', ' ');
		if(config.get("Join." + p.getName()) != null){
			e.setJoinMessage(ChatColor.GOLD + p.getName() + join);
		}else p.sendMessage(ChatColor.GOLD + "Vous pouvez définir un message de connection/deconnection particulier");
	}
	@EventHandler
	public void PlayerQuit(PlayerQuitEvent e){
		Player p = e.getPlayer();
		FileConfiguration config = this.getConfig();
		Object olive = config.get("Leave." + p.getName());
		String leave = Arrays.asList(olive).toString();
		leave = leave.replace(',', ' ');
		leave = leave.replace('[', ' ');
		leave = leave.replace(']', ' ');
		if(config.get("Leave." + p.getName()) != null){
			e.setQuitMessage(ChatColor.GOLD + p.getName() + leave);
		}
	}
	


}
