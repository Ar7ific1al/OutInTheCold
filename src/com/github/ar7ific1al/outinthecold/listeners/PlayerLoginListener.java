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
		/**
		 * <Explanation>
		 * When a player joins the server, an instance of PlayerPositionScheduler is created.
		 * Passed into this object's constructor are parameters for the player who logged in, the plugin instance,
		 * the start delay, and the interval for the repeating timer ("interval"). Every "interval" ticks (90 by default,
		 * or 4.5 seconds), the server will fire off the code within run() in the PlayerPositionScheduler class.
		 * </Explanation>
		 */
		long interval = plugin.Settings.getLong("Timer Settings.Player.Position");
		PlayerPositionScheduler posTimer = new PlayerPositionScheduler(event.getPlayer(), plugin, 5, interval, interval);
	}

}
