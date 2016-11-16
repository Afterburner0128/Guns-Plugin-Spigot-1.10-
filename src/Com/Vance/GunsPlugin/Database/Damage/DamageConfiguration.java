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
				settings.set("Use Effect Radius", "false, 5");
				settings.set("Experience Gained", "true, 10");
				
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
				block.set("Remove Blocks", "false, 100");
				
				ConfigurationSection fireworks = section.createSection("Firework Parameters");
				fireworks.set("Firework", "false, BALL, false, false");
				fireworks.set("Firework Colors", Arrays.asList("RED", "GREEN"));
				
				ConfigurationSection lightning = section.createSection("Lightning Parameters");
				lightning.set("Strike Lightning", true);
				lightning.set("Silent", false);
				lightning.set("Does Damage", true);
				
				ConfigurationSection section1 = config.createSection("Grenade");
				ConfigurationSection settings1 = section1.createSection("Set Parameters");
				settings1.set("Name", "Grenade Damage");
				settings1.set("Set ID", "GrenadeDamage");
				settings1.set("Damage", 20);
				settings1.set("Repeat Effects", "false, 0, 20");
				settings1.set("Delay Effects", "false, 100");
				settings1.set("Use Effect Radius", false);
				settings1.set("Experience Gained", "true, 10");
				
				ConfigurationSection potion1 = section1.createSection("Potions Parameters");
				potion1.set("Potion Effects", false);
				potion1.set("Potions", Arrays.asList("BLINDNESS, 100, 2", "SLOWNESS, 150, 2"));
				
				ConfigurationSection particle1 = section1.createSection("Particle Parameters");
				particle1.set("Particle Effects", false);
				particle1.set("Particles", "M1GarandTrailParticle");
				
				ConfigurationSection explosion1 = section1.createSection("Explosive Parameters");
				explosion1.set("Explosive", true);
				explosion1.set("Explosive Size", 6);
				explosion1.set("Incendiary", false);
				explosion1.set("Block Damage", false);
				
				ConfigurationSection block1 = section1.createSection("Set Block Parameters");
				block1.set("Set Block", false);
				block1.set("Block Type", "35,1");
				block1.set("Remove Blocks", "false, 100");
				
				ConfigurationSection fireworks1 = section1.createSection("Firework Parameters");
				fireworks1.set("Firework", "false, BALL, false, false, true");
				fireworks1.set("Firework Colors", Arrays.asList("RED", "GREEN"));
				
				ConfigurationSection lightning1 = section1.createSection("Lightning Parameters");
				lightning1.set("Strike Lightning", false);
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
						fireworks.getStringList("Firework Colors"), block.getBoolean("Set Block"), block.getString("Block Type"), block.getString("Remove Blocks"), lightning.getBoolean("Strike Lightning")));
			}
			
		} catch (Exception e) {
			Main.getInstance().clogger.sendMessage("§c**ERROR LOADING §bDAMAGE CONFIGURATION§c. §6GUNS PLUGIN §cHAS BEEN DISABLED**");
			e.printStackTrace();
			Main.getInstance().clogger.sendMessage("§c*******************************************************************");
		}
	}

}
