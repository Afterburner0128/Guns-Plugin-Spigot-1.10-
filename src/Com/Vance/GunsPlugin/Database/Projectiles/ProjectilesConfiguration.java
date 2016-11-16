package Com.Vance.GunsPlugin.Database.Projectiles;

import java.io.File;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import Com.Vance.GunsPlugin.Main;

public class ProjectilesConfiguration {
	
	public static void setupConfiguration(){
		File file = new File("plugins/Guns Plugin/Utilities/ProjectilesDatabase.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		try {
			if(!file.exists()){
				ConfigurationSection section = config.createSection("M1GarandBullet");
				section.set("Projectile Set Name", "M1GarandBullet");
				section.set("Projectile Type", "ARROW");
				section.set("Projectile Velocity", 5);
				section.set("Projectile Spread", 1);
				section.set("Projectile Amount", 1);
				section.set("Particle Trail", "true, M1GarandParticleTrail");
				section.set("Ride Projectile", true);
				section.set("Remove Arrow on Hit", true);
				section.set("Projectile Bounce", false);
				section.set("Cancel Enderpearl Teleport", true);
				section.set("Cancel Egg Spawn", true);
				section.set("Entity Hit Sound", "ENTITY_EXPERIENCE_ORB_PICKUP, 2, 1");
			}
			for(String str : config.getKeys(false)){
				ConfigurationSection section = config.getConfigurationSection(str);
				
				Main.getInstance().projectiles.put(section.getString("Projectile Set Name"),new Projectiles(section.getString("Projectile Set Name"), section.getString("Projectile Type"), section.getInt("Projectile Velocity"),
						(float) section.getDouble("Projectile Spread"), section.getInt("Projectile Amount"), section.getString("Particle Trail"), section.getBoolean("Remove Arrow on Hit"),
						section.getBoolean("Cancel Enderpearl Teleport"), section.getBoolean("Cancel Egg Spawn"), section.getString("Entity Hit Sound"), section.getBoolean("Projectile Bounce"), section.getBoolean("Ride Projectile")));
			}
			config.save(file);
		} catch (Exception e) {
			Main.getInstance().clogger.sendMessage("§c**ERROR LOADING §PROJECTILES CONFIGURATION§c. §6GUNS PLUGIN §cHAS BEEN DISABLED**");
			e.printStackTrace();
			Main.getInstance().clogger.sendMessage("§c*******************************************************************");
		}
	}

}
