package Com.Vance.GunsPlugin.Listeners;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import Com.Vance.GunsPlugin.Main;
import Com.Vance.GunsPlugin.Database.Guns.Guns;
import Com.Vance.GunsPlugin.WeaponMethods.WeaponReload;
import de.tr7zw.itemnbtapi.NBTItem;

public class PlayerReloadListener implements Listener{
	
	public static Map<String, Integer> cooldown = new HashMap<String, Integer>();
	private static int taskId;
	
	@EventHandler
	public void on(PlayerDropItemEvent e){
		Player p = e.getPlayer();
		ItemStack item = e.getItemDrop().getItemStack();
		
		if(item == null){
			return;
		}
		if(!item.hasItemMeta() || !item.getItemMeta().hasDisplayName()){
			return;
		}
		NBTItem nbti = new NBTItem(item);
		Guns gun = Main.getInstance().guns.get(nbti.getString("Weapon Type"));
		if(nbti.getBoolean("Weapon") == true){
			if(gun.canDrop() == true){
				if(p.isSneaking() == true){
					return;
				}
			}
			if(cooldown.containsKey(p.getName())){
				if(cooldown.get(p.getName()) > 0){
					e.setCancelled(true);
					return;
				}
			}
			e.setCancelled(true);
			taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable(){

				@Override
				public void run() {
					if(cooldown.get(p.getName()) > 0){
						cooldown.put(p.getName(), cooldown.get(p.getName()) -1);
						return;
					}
					else{
						cooldown.remove(p.getName());
						Bukkit.getScheduler().cancelTask(taskId);
					}
					
				}
				
			}, 0, 1);
			WeaponReload.reload(p,nbti);
			cooldown.put(p.getName(), gun.getReloadDelay() + 30);
		}
		
	}

}
