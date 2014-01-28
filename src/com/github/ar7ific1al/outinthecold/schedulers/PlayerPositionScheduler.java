/**
 * 
 * Basic function of this class is as follows:
 * Create an instance of this class when a player logs in, using the constructor
 * Pass in the Player who logged in with the constructor
 * Initialize X, Y and Z with the player's current X, Y and Z positions
 * Start the runnable with "delay" delay
 * Every "interval" milliseconds, test the online player list for the player associated with this runnable
 * If the player is online, continue; else, cancel this runnable
 * If player is online, test prevx/prevy/prevz against player's current X/Y/Z
 * If the absolute difference is greater than "offset", take necessary actions depending upon player's conditions
 * 
 */

package com.github.ar7ific1al.outinthecold.schedulers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.github.ar7ific1al.outinthecold.Main;

public class PlayerPositionScheduler implements Runnable{

	private Main plugin;
	
	private int TaskID;
	private long Delay;
	private long Interval;
	
	private Player player;
	private double x;
	private double prevx;
	private double y;
	private double prevy;
	private double z;
	private double prevz;
	private double offset = 3;
	
	public PlayerPositionScheduler(Player player, Main plugin, int id, long delay, long interval){
		this.plugin = plugin;
		this.player = player;
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, this, delay, interval);
	}
	
	
	
	@Override
	public void run() {
		boolean playerFound = false;
		for (Player online : Bukkit.getServer().getOnlinePlayers()){
			if(online.getName() == player.getName()){
				//	Do stuff
				//	TODO: Everything
				playerFound = true;
				break;
			}
		}
		if (!playerFound){
			Bukkit.getServer().getScheduler().cancelTask(TaskID);
		}
	}

}
