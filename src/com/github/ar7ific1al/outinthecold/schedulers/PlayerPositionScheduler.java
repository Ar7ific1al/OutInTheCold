/**
 * <ClassDescription>
 * Basic function of this class is as follows:
 * Create an instance of this class when a player logs in, using the constructor
 * Pass in the Player who logged in with the constructor
 * Initialize X, Y and Z with the player's current X, Y and Z positions
 * Start the runnable with "delay" delay
 * Every "interval" milliseconds, test the online player list for the player associated with this runnable
 * If the player is online, continue; else, cancel this runnable
 * If player is online, test prevx/prevy/prevz against player's current X/Y/Z
 * If the absolute difference is greater than "offset", take necessary actions depending upon player's conditions
 * </ClassDescription>
 */

package com.github.ar7ific1al.outinthecold.schedulers;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import com.github.ar7ific1al.outinthecold.Main;
import com.github.ar7ific1al.outinthecold.events.PlayerEnvironmentEvent;
import com.github.ar7ific1al.outinthecold.utils.Log;

public class PlayerPositionScheduler implements Runnable{

	private int TaskID;
	
	private Player player;
	private Double prevx;
	private Double prevy;
	private Double prevz;
	private double offset = 3;
	
	public PlayerPositionScheduler(Player player, Main plugin, int id, long delay, long interval){
		this.player = player;
		TaskID = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, this, delay, interval);
	}
	
	
	
	@Override
	public void run() {
		boolean playerFound = false;
		for (Player online : Bukkit.getServer().getOnlinePlayers()){
			if(online.getName() == player.getName()){
				//	Do stuff
				//	TODO: Everything
				
				Bukkit.getServer().broadcastMessage("PLAYER " + player.getName() + " WAS FOUND!!");
				playerFound = true;
				
				if(Main.Settings.getBoolean("Ignore-Creative") && player.getGameMode() == GameMode.CREATIVE){
					player.sendMessage(Log.ColorMessage("&bGameMode of player " + player.getName() + " is CREATIVE, skipping operations!"));
					break;
				}
				else if (Main.Settings.getBoolean("Ignore-Adventure") && player.getGameMode() == GameMode.ADVENTURE){
					player.sendMessage(Log.ColorMessage("&bGameMode of player " + player.getName() + " is ADVENTURE, skipping operations!"));
					break;
				}
				
				//	Compare positions
				//if (prevx != null && prevy != null && prevz != null){
				//	if ((Math.abs(Math.abs(prevx) - Math.abs(player.getLocation().getX())) >= offset ||
				//			(Math.abs(Math.abs(prevy) - Math.abs(player.getLocation().getY())) >= offset ||
				//			(Math.abs(Math.abs(prevz) - Math.abs(player.getLocation().getZ())) >= offset)))){
							
							player.sendMessage(Log.ColorMessage("&bDifference in position of " + player.getName() + " is at least 5."));
							Bukkit.getServer().getPluginManager().callEvent(new PlayerEnvironmentEvent(online));
				//		}
				//}
				prevx = player.getLocation().getX();
				prevy = player.getLocation().getY();
				prevz = player.getLocation().getZ();
				break;
			}
		}
		if (!playerFound){
			Bukkit.getServer().broadcastMessage("PLAYER " + player.getName() + " WAS NOT FOUND!! CANCELLING THIS TASK.");
			Bukkit.getServer().getScheduler().cancelTask(TaskID);
		}
	}

}
