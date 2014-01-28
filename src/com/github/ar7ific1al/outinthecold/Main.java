package com.github.ar7ific1al.outinthecold;

import java.io.File;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.ar7ific1al.outinthecold.utils.Log;

public class Main extends JavaPlugin{
	
	public static String version;
	
	public static List<String> authors;
	
	public static File SettingsDir = new File("plugins/SoulJars/settings/");
	
	public static Random random = new Random();
	
	@SuppressWarnings("unused")
	@Override
	public void onEnable(){
		PluginManager pm = Bukkit.getServer().getPluginManager();
		PluginDescriptionFile pdFile = this.getDescription();
		version = pdFile.getVersion();
		authors = pdFile.getAuthors();
		Log.LogMessage(Log.ColorMessage("&b[OutInTheCold] version " + version + " by " + authors + " enabled."),
				getServer().getConsoleSender());
		
		if (!getDataFolder().exists()){
			getDataFolder().mkdir();
		}
		
		if (!SettingsDir.exists()){
			SettingsDir.mkdir();
		}
	}
	
	@Override
	public void onDisable(){
		Log.LogMessage(Log.ColorMessage("&b[OutInTheCold] version " + version + " by " + authors + " disabled."),
				getServer().getConsoleSender());
	}

}
