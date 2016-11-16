package Com.Vance.GunsPlugin.Database.Attachments;

import java.io.File;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import Com.Vance.GunsPlugin.Main;

public class AttachmentsConfiguration {
	
	public static void setupConfiguration(){
		File file = new File("plugins/Guns Plugin/Utilities/AttachmentsDatabase.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		if(!file.exists()){
			ConfigurationSection section = config.createSection("M7GrenadeLauncher");
			ConfigurationSection itemParams = section.createSection("Item Paramiters");
			itemParams.set("Name", "§eM1 Garand");
			itemParams.set("ID", "M7GrenadeLauncher");
			itemParams.set("Weapon Item", "192, 1, 1, true");
			itemParams.set("Weapon Description", "§aM1 Garand Grenade Launcher");
			
			ConfigurationSection ammoParams = section.createSection("Ammunition Paramiters");
			ammoParams.set("Ammo Item", "264, 0, 1");
			ammoParams.set("Ammo Capacity", 1);
			ammoParams.set("Remove Ammo Individualy", true);
			ammoParams.set("Projectile Set", "M7Grenade");
			
			ConfigurationSection weaponParams = section.createSection("Weapon Operation");
			weaponParams.set("Melee", false);
			weaponParams.set("Ranged Weapon", false);
			weaponParams.set("Shoot Delay", 3);
			weaponParams.set("Reload Delay", 56);
			weaponParams.set("Firearm Type", "BOLT-ACTION");
			weaponParams.set("Damage Set", "GrenadeDamage");
			weaponParams.set("Automatic Reload", false);
			weaponParams.set("Right Click to Shoot", true);
			weaponParams.set("Left Click to Enable", true);
			weaponParams.set("Emits Light", false);
			weaponParams.set("Laser On Crosshair", "false, RED, 10");
			weaponParams.set("Jamming", "false, 1000");
			weaponParams.set("Scope", false);
			weaponParams.set("Zoom Level", 2);
			weaponParams.set("Weight", 0);
			weaponParams.set("Crafting", "false, M7Crafting");
			
			ConfigurationSection soundParams = section.createSection("Sound Paramiters");
			soundParams.set("Shoot Sound", "BAT_IDLE, 1, 1");
			soundParams.set("Reload Sound", "BAT_AMBIENT, 1, 1");
			soundParams.set("Hammer Sound", "UI_BUTTON_CLICK, 2, 1");
		}
		for(String str : config.getKeys(false)){
			ConfigurationSection paramiters = config.getConfigurationSection(str);
			ConfigurationSection itemParams = paramiters.getConfigurationSection("Item Paramiters");
			ConfigurationSection ammoParams = paramiters.getConfigurationSection("Ammunition Paramiters");
			ConfigurationSection weaponParams = paramiters.getConfigurationSection("Weapon Operation");
			ConfigurationSection soundParams = paramiters.getConfigurationSection("Sound Paramiters");
			
			Main.getInstance().attachments.put(itemParams.getString("ID"), new Attachments(itemParams.getString("Name"), itemParams.getString("ID"), itemParams.getString("WeaponItem"), itemParams.getString("Weapon Description"), ammoParams.getString("Ammo Item"),
					ammoParams.getInt("Ammo Capacity"), ammoParams.getBoolean("Remove Ammo Individualy"), ammoParams.getString("Projectile Set"), weaponParams.getBoolean("Melee"), weaponParams.getBoolean("Ranged"), weaponParams.getInt("Shoot Delay"),
					weaponParams.getInt("Reload Delay"), weaponParams.getString("Firearm Type"), weaponParams.getString("Damage Set"), weaponParams.getBoolean("Automatic Reload"), weaponParams.getBoolean("Right Click to Shoot"), weaponParams.getBoolean("Left Click to Enable"),
					weaponParams.getBoolean("Emits Light"), weaponParams.getBoolean("Laser on Crosshair"), weaponParams.getString("Jamming"), weaponParams.getBoolean("Scope"), weaponParams.getInt("Zoom Level"), weaponParams.getInt("Weight"), weaponParams.getString("Crafting"),
					soundParams.getString("Shoot Sound"), soundParams.getString("Reload Sound"), soundParams.getString("Hammer Sound")));
		}
		try {
			config.save(file);
		} catch (Exception e) {
			Main.getInstance().clogger.sendMessage("§c**ERROR LOADING §bATTACHMENTS CONFIGURATION§c. §6GUNS PLUGIN §cHAS BEEN DISABLED**");
			e.printStackTrace();
			Main.getInstance().clogger.sendMessage("§c*******************************************************************");
		}
	}
}
