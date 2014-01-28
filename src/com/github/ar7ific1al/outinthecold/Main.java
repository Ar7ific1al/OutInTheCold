package com.github.ar7ific1al.outinthecold;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.ar7ific1al.outinthecold.listeners.PlayerLoginListener;
import com.github.ar7ific1al.outinthecold.utils.Log;

public class Main extends JavaPlugin{
	
	public static String version;
	
	public static List<String> authors;
	
	public static File SettingsDir = new File("plugins/OutInTheCold/settings/");
	public static File SettingsFile = new File("plugins/OutInTheCold/settings/settings.yml");
	public static FileConfiguration Settings = new YamlConfiguration();
	
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
		
		if (!SettingsFile.exists()){
			try {
				SettingsFile.createNewFile();
				GenerateDefaultSettings();
			} catch (IOException e) {
				Log.LogMessage(Log.ColorMessage("&b[OutInTheCold] ERROR, STACK TRACE PRINTED BELOW."), getServer().getConsoleSender());
				e.printStackTrace();
				return;
			} catch (InvalidConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			try {
				Settings.load(SettingsFile);
			} catch (IOException | InvalidConfigurationException e) {
				Log.LogMessage(Log.ColorMessage("&b[OutInTheCold] ERROR, STACK TRACE PRINTED BELOW."), getServer().getConsoleSender());
				e.printStackTrace();
			}
		}
		
		pm.registerEvents(new PlayerLoginListener(this), this);
		
	}
	
	private void GenerateDefaultSettings() throws FileNotFoundException, IOException, InvalidConfigurationException {
		Settings.load(SettingsFile);
		Settings.addDefault("Ignore-Creative", true);
		Settings.addDefault("Ignore-Adventure", true);
		Settings.addDefault("Timer Settings.Player.Position", 90L);
		Settings.addDefault("Timer Settings.Player.SomeOtherSetting", 20L);
		Settings.options().copyDefaults(true);
		Settings.save(SettingsFile);
	}
	
	private void ReloadSettings() throws FileNotFoundException, IOException, InvalidConfigurationException{
		SettingsFile = new File(getDataFolder() + "/settings/settings.yml");
		Settings.load(SettingsFile);
	}

	@Override
	public void onDisable(){
		Log.LogMessage(Log.ColorMessage("&b[OutInTheCold] version " + version + " by " + authors + " disabled."),
				getServer().getConsoleSender());
	}

}
