package com.github.ar7ific1al.outinthecold.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World.Environment;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.github.ar7ific1al.outinthecold.utils.Log;

public class PlayerEnvironmentEvent extends Event implements Cancellable{
	
	private Player player;
	private Location playerLocation;
	private Environment playerEnvironment;
	private Biome playerBiome;
	private double playerTemperature;
	private int lightLevel;
	private boolean isCancelled;
	
	@Override
	public boolean isCancelled(){
		return this.isCancelled;
	}
	
	@Override
	public void setCancelled(boolean arg0){
		this.isCancelled = arg0;
	}
	
	private static final HandlerList handlers = new HandlerList();
	
	public HandlerList getHandlers(){
		return handlers;
	}
	
	public static HandlerList getHandlerList(){
		return handlers;
	}
	
	private Player getPlayer(){
		return this.player;
	}
	
	private Location getLocation(){
		return player.getLocation();
	}
	
	private double getTemperature(){
		return this.playerTemperature;
	}
	
	public PlayerEnvironmentEvent(Player player){
		this.player = player;
		this.playerLocation = player.getLocation();
		this.playerEnvironment = player.getLocation().getWorld().getEnvironment();
		Block block = player.getLocation().getBlock();
		this.playerTemperature = block.getTemperature();
		this.lightLevel = block.getLightLevel();
		this.playerBiome = block.getBiome();
		
		player.sendMessage(Log.ColorMessage("&bCurrent Environment: " + playerEnvironment.toString()));
		player.sendMessage(Log.ColorMessage("&bCurrent Biome: " + playerBiome.toString()));
		player.sendMessage(Log.ColorMessage("&bCurrent Block Temperature: " + playerTemperature));
		player.sendMessage(Log.ColorMessage("&bCurrent Block Light Level: " + lightLevel));
	}

}
