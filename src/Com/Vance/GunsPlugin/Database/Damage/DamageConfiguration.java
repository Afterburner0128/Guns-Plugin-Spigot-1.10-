package Com.Vance.GunsPlugin.Database.Damage;

import java.io.File;
import java.util.Arrays;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import Com.Vance.GunsPlugin.Main;

public class DamageConfiguration {
	
	public static void setupConfiguration(){
		File file = new File("plugins/Guns Plugin/Utilities/DamageDatabase.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		try {
			if(!file.exists()){
				ConfigurationSection section = config.createSection("M1GarandDamage");
				ConfigurationSection settings = section.createSection("Set Parameters");
				settings.set("Name", "M1Garand Damage");
				settings.set("Set ID", "M1GarandDamage");
				settings.set("Damage", 20);
				settings.set("Repeat Effects", "false, 0, 20");
				settings.set("Delay Effects", "false, 100");
				settings.set("Use Effect Radius", "false, 5, 5 ,5");
				settings.set("Experience Gained", "true, 10");
				settings.set("Run on Projectile Hit", false);
				
				ConfigurationSection potion = section.createSection("Potions Parameters");
				potion.set("Potion Effects", false);
				potion.set("Potions", Arrays.asList("SLOWNESS, 150, 2","BLINDNESS, 100, 2"));
				
				ConfigurationSection particle = section.createSection("Particle Parameters");
				particle.set("Particle Effects", false);
				particle.set("Particles", "M1GarandTrailParticle");
				
				ConfigurationSection explosion = section.createSection("Explosive Parameters");
				explosion.set("Explosive", false);
				explosion.set("Explosive Size", 4);
				explosion.set("Incendiary", false);
				explosion.set("Block Damage", false);
				
				ConfigurationSection block = section.createSection("Set Block Parameters");
				block.set("Set Block", false);
				block.set("Block Type", "35,1");
				block.set("Remove Blocks", "false, 100, 1");
				block.set("Effect Shape", "SQUARE");
				block.set("Hollow", true);
				
				ConfigurationSection fireworks = section.createSection("Firework Parameters");
				fireworks.set("Firework", "false, BALL, false, false");
				fireworks.set("Firework Colors", Arrays.asList("RED", "GREEN"));
				
				ConfigurationSection lightning = section.createSection("Lightning Parameters");
				lightning.set("Strike Lightning", false);
				
				
			}
			config.save(file);
			for(String str : config.getKeys(false)){
				ConfigurationSection section = config.getConfigurationSection(str);
				ConfigurationSection particle = section.getConfigurationSection("Particle Parameters");
				ConfigurationSection settings = section.getConfigurationSection("Set Parameters");
				ConfigurationSection potion = section.getConfigurationSection("Potions Parameters");
				ConfigurationSection explosion = section.getConfigurationSection("Explosive Parameters");
				ConfigurationSection block = section.getConfigurationSection("Set Block Parameters");
				ConfigurationSection fireworks = section.getConfigurationSection("Firework Parameters");
				ConfigurationSection lightning = section.getConfigurationSection("Lightning Parameters");
				
				Main.getInstance().damage.put(settings.getString("Set ID"), new Damage(settings.getString("Name"), settings.getString("Set ID"),(float) settings.getDouble("Damage"), settings.getString("Repeat Effects"), settings.getString("Delay Effects"),
						settings.getString("Use Effect Radius"), settings.getString("Experience Gained"), potion.getBoolean("Potion Effects"), potion.getStringList("Potions"), particle.getBoolean("Particle Effects"),
						particle.getString("Particles"), explosion.getBoolean("Explosive"),explosion.getInt("Explosive Size"), explosion.getBoolean("Incendiary"), explosion.getBoolean("Block Damage"), fireworks.getString("Firework"), 
						fireworks.getStringList("Firework Colors"), block.getBoolean("Set Block"), block.getString("Block Type"), block.getString("Remove Blocks"), lightning.getBoolean("Strike Lightning"), block.getBoolean("Hollow"), block.getString("Effect Shape"), settings.getBoolean("Run on Projectile Hit")));
			}
			
		} catch (Exception e) {
			Main.getInstance().clogger.sendMessage("§c**ERROR LOADING §bDAMAGE CONFIGURATION§c. §6GUNS PLUGIN §cHAS BEEN DISABLED**");
			e.printStackTrace();
			Main.getInstance().clogger.sendMessage("§c*******************************************************************");
		}
	}

}
