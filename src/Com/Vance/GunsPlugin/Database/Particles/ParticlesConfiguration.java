package Com.Vance.GunsPlugin.Database.Particles;

import java.io.File;
import java.util.Arrays;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import Com.Vance.GunsPlugin.Main;

public class ParticlesConfiguration {
	
	public static void setupConfiguration(){
		File file = new File("plugins/Guns Plugin/Utilities/ParticlesDatabase.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);	
		
		try {
			if(!file.exists()){
				ConfigurationSection section = config.createSection("M1GarandParticleTrail");
				section.set("Effect ID", "M1GarandParticleTrail");
				section.set("Particles", Arrays.asList("CRIT, 2, 2, 2, 0, 40", "COLOURED_DUST, 2, 2, 2, 0, 40, RED"));
				section.set("Block ID", 10);
				section.set("Block Data", 10);
				ConfigurationSection section1 = config.createSection("GrenadeThrowingParticles");
				section1.set("Effect ID", "GrenadeThrowingParticles");
				section1.set("Particles", Arrays.asList("CRIT, 2, 2, 2, 0, 40", "COLOURED_DUST, 2, 2, 2, 0, 40, RED"));
				section1.set("Block ID", 10);
				section1.set("Block Data", 10);
			}
			for(String s : config.getKeys(false)){
				ConfigurationSection section = config.getConfigurationSection(s);
				
				Main.getInstance().particles.put(section.getString("Effect ID"),new Particles(section.getString("Effect ID"), section.getStringList("Particles"), section.getInt("Block ID"), section.getInt("Block Data")));
			}
			config.save(file);
		} catch (Exception e) {
			Main.getInstance().clogger.sendMessage("§c**ERROR LOADING §PARTICLES CONFIGURATION§c. §6GUNS PLUGIN §cHAS BEEN DISABLED**");
			e.printStackTrace();
			Main.getInstance().clogger.sendMessage("§c*******************************************************************");
		
		}
	}

}
