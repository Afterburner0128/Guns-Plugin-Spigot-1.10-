package Com.Vance.GunsPlugin.Database.Crafting;

import java.io.File;
import java.util.Arrays;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import Com.Vance.GunsPlugin.Main;

public class CraftingConfiguration {
	
	public static void setupConfiguration(){
		File file = new File("plugins/Guns Plugin/Utilities/CraftingDatabase.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		try {
			if(!file.exists()){
				ConfigurationSection section = config.createSection("M1GarandCrafting");
				section.set("Crafting Name", "M1 Garand Crafting");
				section.set("ID", "M1GarandCrafting");
				section.set("Item Abbreviations", Arrays.asList("265, 0, I", "5, 0, W", "259, 0, F"));
				section.set("Item Ammount", 1);
				section.set("Crafting Row 1", "  F");
				section.set("Crafting Row 2", "IWI");
				section.set("Crafting Row 3", "  W");
				section.set("Enable Recipe", true);
			}
			for(String str : config.getKeys(false)){
				ConfigurationSection section = config.getConfigurationSection(str);
				
				Main.getInstance().crafting.put(section.getString("ID"), new Crafting(section.getString("Name"), section.getString("ID"), section.getStringList("Item Abbreviations"),
						section.getInt("Item Amount"), section.getString("Crafting Row 1"), section.getString("Crafting Row 2"), section.getString("Crafting Row 3"),
								section.getBoolean("Enable Recipe")));
			}
			config.save(file);
		} catch (Exception e) {
			Main.getInstance().clogger.sendMessage("§c**ERROR LOADING §bCRAFTING CONFIGURATION§c. §6GUNS PLUGIN §cHAS BEEN DISABLED**");
			e.printStackTrace();
			Main.getInstance().clogger.sendMessage("§c*******************************************************************");
		}
	}

}
