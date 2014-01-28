package com.github.ar7ific1al.outinthecold.utils;

import org.bukkit.command.ConsoleCommandSender;

public class Log {
	
	public static void LogMessage(String message, ConsoleCommandSender console){
		console.sendMessage(message);
	}
	
	public static String ColorMessage(String input) {

		String output = "";

		output = input.replaceAll("&([0-9a-f])", "\u00A7$1");
		output = output.replaceAll("&([k-o])", "\u00A7$1");

		return output;
	}
	
}
