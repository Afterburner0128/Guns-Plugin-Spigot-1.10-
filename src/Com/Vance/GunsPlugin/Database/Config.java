package Com.Vance.GunsPlugin.Database;

import java.io.File;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import Com.Vance.GunsPlugin.Main;

public class Config {
	
	public static void setupConfig(){
		File file = new File("plugins/Guns Plugin/Settings.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		if(!file.exists()){
			ConfigurationSection permissions = config.createSection("Permissions");
			permissions.set("Require Command Permissions", true);
			permissions.set("Command Permission", "guns.commands.use");
			ConfigurationSection messages = config.createSection("Messages");
			messages.set("Shoot Message", "§9Ammunition Remaining: §6%AmmoAmount% Rounds");
			messages.set("Out of Ammo", "§9Ammunition Remaining: §4Out Of Ammo");
			messages.set("Empty Weapon", "§9Ammunition Remaining: §6Weapon Empty");
			messages.set("Weapon Jammed", "§9Weapon Status: §4Weapon Jammed");
			messages.set("Reloading Message", "§9Status: §6Reloading...");
			messages.set("Received Weapon", "§eYou have recieved the [%GunID%, %GunName%]");
			messages.set("Ammo Item Display", "§e%GunName%§r Ammo «%AmmoAmount%»");
			messages.set("Permissions.Weapon Use", "§cYou lack the required permissions to use this weapon.");
		}
		try {
			config.save(file);
		} catch (Exception e) {
			Main.getInstance().clogger.sendMessage("§c**ERROR LOADING §bSETINGS CONFIGURATION§c. §6GUNS PLUGIN §cHAS BEEN DISABLED**");
			e.printStackTrace();
			Main.getInstance().clogger.sendMessage("§c*******************************************************************");
		}
	}
	public static FileConfiguration getConfiguration(){
		File file = new File("plugins/Guns Plugin/Settings.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		return config;
	}

}
