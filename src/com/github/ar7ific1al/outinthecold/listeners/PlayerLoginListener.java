package com.github.ar7ific1al.outinthecold.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.github.ar7ific1al.outinthecold.Main;
import com.github.ar7ific1al.outinthecold.schedulers.PlayerPositionScheduler;
import com.github.ar7ific1al.outinthecold.utils.Log;

public class PlayerLoginListener implements Listener{
	
	private Main plugin;
	
	public PlayerLoginListener(Main instance){
		plugin = instance;
		Log.LogMessage(Log.ColorMessage("&b[OutInTheCold] PlayerLoginListener registered."), Bukkit.getServer().getConsoleSender());
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		long interval = 90;
		PlayerPositionScheduler posTimer = new PlayerPositionScheduler(event.getPlayer(), plugin, 5, interval, interval);
	}

}
