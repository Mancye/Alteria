package me.mancy.core;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		System.out.println(ChatColor.GREEN + ">> Alteria Core Enabled");
	}
	
	@Override
	public void onDisable() {
		
	}
	
}
