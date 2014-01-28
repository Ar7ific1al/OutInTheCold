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
