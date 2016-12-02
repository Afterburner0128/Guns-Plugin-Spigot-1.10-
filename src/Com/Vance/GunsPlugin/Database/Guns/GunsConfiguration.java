package Com.Vance.GunsPlugin.Database.Guns;

import java.io.File;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import Com.Vance.GunsPlugin.Main;

public class GunsConfiguration {
	
	public static void setupConfiguration(){
		File file = new File("plugins/Guns Plugin/Weapons/GunsDatabase.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		try {
			if(!file.exists()){
				ConfigurationSection section = config.createSection("M1Garand");
				ConfigurationSection itemParams = section.createSection("Item Parameters");
				itemParams.set("Name", "§eM1 Garand");
				itemParams.set("ID", "M1Garand");
				itemParams.set("Weapon Item", "292, 1, 1, true");
				itemParams.set("Weapon Description", "§aStandard Infantry Rifle");
				itemParams.set("Permission", "false, guns.use.m1garand");
				ConfigurationSection ammoParams = section.createSection("Ammunition Parameters");
				ammoParams.set("Ammo Item", "264, 0");
				ammoParams.set("Ammo Capacity", 8);
				ammoParams.set("Remove Ammo Individually", true);
				ammoParams.set("Projectile Set", "M1GarandBullet");
				
				ConfigurationSection weaponParams = section.createSection("Weapon Operation");
				weaponParams.set("Attachment", "true, M7GrenadeLauncher");
				weaponParams.set("Shoot Delay", 3);
				weaponParams.set("Reload Delay", 56);
				weaponParams.set("Firearm Type", "SEMI-AUTOMATIC");
				weaponParams.set("Damage Set", "M1GarandDamage");
				weaponParams.set("Automatic Reload", false);
				weaponParams.set("Right Click to Shoot", true);
				weaponParams.set("Jamming", "false, 1000");
				weaponParams.set("Scope", false);
				weaponParams.set("Zoom Level", 2);
				weaponParams.set("Weight", 0);
				weaponParams.set("Crafting", "false, M1GarandCrafting");
				weaponParams.set("Shift Weapon Drop", true);
				ConfigurationSection soundParams = section.createSection("Sound Parameters");
				soundParams.set("Shoot Sound", "ENTITY_BAT_LOOP, 1, 1");
				soundParams.set("Reload Sound", "ENTITY_BAT_AMBIENT, 1, 1");
				soundParams.set("Hammer Sound", "UI_BUTTON_CLICK, 2, 1");
			}
			for(String str : config.getKeys(false)){
				ConfigurationSection paramiters = config.getConfigurationSection(str);
				ConfigurationSection itemParams = paramiters.getConfigurationSection("Item Parameters");
				ConfigurationSection ammoParams = paramiters.getConfigurationSection("Ammunition Parameters");
				ConfigurationSection weaponParams = paramiters.getConfigurationSection("Weapon Operation");
				ConfigurationSection soundParams = paramiters.getConfigurationSection("Sound Parameters");
				
				Main.getInstance().guns.put(itemParams.getString("ID"), new Guns(itemParams.getString("Name"), itemParams.getString("ID"), itemParams.getString("Weapon Item"), itemParams.getString("Weapon Description"),
						ammoParams.getString("Ammo Item"), ammoParams.getInt("Ammo Capacity"), ammoParams.getBoolean("Remove Ammo Individually"), ammoParams.getString("Projectile Set"),
						weaponParams.getInt("Shoot Delay"), weaponParams.getInt("Reload Delay"), weaponParams.getString("Firearm Type"), weaponParams.getString("Damage Set"),
						weaponParams.getString("Jamming"), weaponParams.getBoolean("Scope"), weaponParams.getInt("Zoom Level"), weaponParams.getInt("Weight"),
						soundParams.getString("Shoot Sound"), soundParams.getString("Reload Sound"), soundParams.getString("Hammer Sound"), weaponParams.getBoolean("Right Click to Shoot"),
						weaponParams.getBoolean("Automatic Reload"), weaponParams.getString("Crafting"), weaponParams.getString("Attachment"), weaponParams.getBoolean("Shift Weapon Drop"), itemParams.getString("Permission")));
			}
			config.save(file);
		} catch (Exception e) {
			Main.getInstance().clogger.sendMessage("§c**ERROR LOADING §bGUNS CONFIGURATION§c. §6GUNS PLUGIN §cHAS BEEN DISABLED**");
			e.printStackTrace();
			Main.getInstance().clogger.sendMessage("§c*******************************************************************");
		}
	}

}
