package Com.Vance.GunsPlugin.Utilliites;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import Com.Vance.GunsPlugin.Database.Damage.Damage;

public class SetBlockMethods {
	
	private static HashMap<Location,Block> map = new HashMap<Location,Block>();
	
	@SuppressWarnings("deprecation")
	public static void setBlocks(Damage damage, Location loc){
		for (int x = -damage.getEffectsRadius(); x <= damage.getEffectsRadius(); x++) {
			for (int y = -damage.getEffectsRadius(); y <= damage.getEffectsRadius(); y++) {
				for (int z = -damage.getEffectsRadius(); z <= damage.getEffectsRadius(); z++) {
					if (loc.distance(loc) <= damage.getEffectsRadius()) {
						//map.put(loc, loc.getBlock());
						loc.getBlock().setType(Material.getMaterial(damage.getBlockType()));
						loc.getBlock().setData((byte) damage.getBlockData());
						
					}
				}
			}
		}
	}
	@SuppressWarnings("deprecation")
	public static void removeBlocks(Damage damage, Location loc){
		for (int x = -damage.getEffectsRadius(); x <= damage.getEffectsRadius(); x++) {
			for (int y = -damage.getEffectsRadius(); y <= damage.getEffectsRadius(); y++) {
				for (int z = -damage.getEffectsRadius(); z <= damage.getEffectsRadius(); z++) {
					if (loc.distance(loc) <= damage.getEffectsRadius()) {
						if(loc.getBlock().getType().equals(Material.getMaterial(damage.getBlockType()))){
							if(loc.getBlock().getData() == (byte) damage.getBlockData()){
								loc.getBlock().setType(map.get(loc).getType());
								loc.getBlock().setData((map.get(loc).getData()));
							}
						}
						
						
					}
				}
			}
		}
	}

}
