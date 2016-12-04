package Com.Vance.GunsPlugin.Database.Grenades;

import java.io.File;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import Com.Vance.GunsPlugin.Main;

public class GrenadesConfiguration {
	
	public static void setupConfiguration(){
		File file = new File("plugins/Guns Plugin/Weapons/GrenadesDatabase.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		try {

			if(!file.exists()){
				ConfigurationSection section = config.createSection("Grenade");
				
				ConfigurationSection itemParams = section.createSection("Item Parameters");
				itemParams.set("Name", "§eGrenade");
				itemParams.set("ID", "Grenade");
				itemParams.set("Item", "35, 12, 1, true");
				itemParams.set("Description", "§aStandard Grenade");
				ConfigurationSection weaponParams = section.createSection("Weapon Operation");
				weaponParams.set("Throwing Velocity", 1);
				weaponParams.set("Detonation Timer", 60);
				weaponParams.set("Damage Set", "GrenadeDamage");
				weaponParams.set("Throwing Particles", "GrenadeThrowingParticles");
				weaponParams.set("Remove Item After Detonation", true);
				weaponParams.set("Can Pickup", false);
			}
			for(String str : config.getKeys(false)){
				ConfigurationSection section = config.getConfigurationSection(str);
				ConfigurationSection itemParams = section.getConfigurationSection("Item Parameters");
				ConfigurationSection weaponParams = section.getConfigurationSection("Weapon Operation");
				
				Main.getInstance().grenades.put(itemParams.getString("ID"),new Grenades(itemParams.getString("Name"), itemParams.getString("ID"), itemParams.getString("Item"), itemParams.getString("Description"), weaponParams.getInt("Throwing Velocity"),
						weaponParams.getInt("Detonation Timer"), weaponParams.getString("Damage Set"), weaponParams.getString("Throwing Particles"), weaponParams.getBoolean("Remove Item After Detonation"), weaponParams.getBoolean("Can Pickup")));
						
			}
			config.save(file);
		} catch (Exception e) {
			Main.getInstance().clogger.sendMessage("§c**ERROR LOADING §bGRENADES CONFIGURATION§c. §6GUNS PLUGIN §cHAS BEEN DISABLED**");
			e.printStackTrace();
			Main.getInstance().clogger.sendMessage("§c*******************************************************************");
		}
	}

}
