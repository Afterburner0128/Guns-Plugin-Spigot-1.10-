package Com.Vance.GunsPlugin.Utilliites;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import Com.Vance.GunsPlugin.Main;
import Com.Vance.GunsPlugin.Database.Damage.Damage;

public class SetBlockMethods {

	private Damage damage;
	private Location loc;
	private HashMap<Location, Integer> id;
	private HashMap<Location, Byte> data;
	private int largestRadius;

	public SetBlockMethods(Damage damage, Location loc) {
		this.loc = loc;
		this.damage = damage;
		this.id = new HashMap<Location, Integer>();
		this.data = new HashMap<Location, Byte>();
		this.largestRadius = (damage.getEffectsRadius("X")>damage.getEffectsRadius("Y"))?(damage.getEffectsRadius("X")>damage.getEffectsRadius("Z")?damage.getEffectsRadius("X"):damage.getEffectsRadius("Z")):(damage.getEffectsRadius("Y")>damage.getEffectsRadius("Z")?damage.getEffectsRadius("Y"):damage.getEffectsRadius("Z"));
	}
	@SuppressWarnings("deprecation")
	public void setBlocks() {
		for (int x = -damage.getEffectsRadius("X"); x <= damage.getEffectsRadius("X"); x++) {
			for (int y = -damage.getEffectsRadius("Y"); y <= damage.getEffectsRadius("Y"); y++) {
				for (int z = -damage.getEffectsRadius("Z"); z <= damage.getEffectsRadius("Z"); z++) {
					Location center = loc.clone().add(x, y, z);
					//CIRCLE
					if(damage.getBlockShape().equals("CIRCLE")){
						if (center.distance(loc) <= largestRadius) {
							id.put(center, center.getBlock().getTypeId());
							data.put(center, center.getBlock().getData());
							center.getBlock().setType(Material.getMaterial(damage.getBlockType()));
							center.getBlock().setData((byte) damage.getBlockData());	
						}
					}
					//SQUARE
					if(damage.getBlockShape().equals("SQUARE")){
						id.put(center, center.getBlock().getTypeId());
						data.put(center, center.getBlock().getData());
						center.getBlock().setType(Material.getMaterial(damage.getBlockType()));
						center.getBlock().setData((byte) damage.getBlockData());	
						
					}
					
				}
			}
		}
	}

	public void removeBlocks(int radiusX, int radiusY, int radiusZ) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                blockRegen();
            }
        }, damage.getDelayTimer());
	}
	public void blockRegen() {
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				if (!id.isEmpty()) {
					HashMap<Double, Location> hashList = new HashMap<>();
					for (Location loc : id.keySet()) {
						hashList.put(loc.getY(), loc);
					}
					List<Double> doubles = new ArrayList<>();
					for (double d : hashList.keySet()) {
						doubles.add(d);
	                }
					Location loc = hashList.get(doubles.get(minIndex(doubles)));
					int b = id.get(loc);
					int a = data.get(loc);
					loc.getWorld().getBlockAt(loc).setTypeId(b);
					loc.getWorld().getBlockAt(loc).setData((byte) a);
					id.remove(loc);
					blockRegen();
				}
			}
		}, damage.removeBlockRepeat());

	}
	public static int minIndex(List<Double> list) {
		return list.indexOf(Collections.min(list));
	}

}