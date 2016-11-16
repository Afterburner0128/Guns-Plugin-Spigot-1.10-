package Com.Vance.GunsPlugin.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import Com.Vance.GunsPlugin.Main;
import Com.Vance.GunsPlugin.Database.Guns.Guns;
import Com.Vance.GunsPlugin.WeaponMethods.WeaponShoot;
import de.tr7zw.itemnbtapi.NBTItem;

public class PlayerInteractListener implements Listener{
	
	@EventHandler
	public void on(PlayerInteractEvent e){
		Player p = e.getPlayer();
		@SuppressWarnings("deprecation")
		ItemStack item = p.getItemInHand();
		
		if (item == null) {
			return;
		}
		if (!item.hasItemMeta() || !item.getItemMeta().hasDisplayName()) {
			return;
		}
		NBTItem nbti = new NBTItem(item);
		Guns gun = Main.getInstance().guns.get(nbti.getString("Weapon Type"));
		if(nbti.getBoolean("Weapon") == true){
			if(gun.rightClickToShoot() == true){
				if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
					try {
						if(nbti.getBoolean("Gun") == true){
							WeaponShoot.shootWeapon(p, nbti);
						}
						if(nbti.getBoolean("Grenade") == true){
							
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
				if (e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
					try {
						//ZOOM GUN
						//ENABLE ATTACHMENT
					} catch (Exception e2) {
						
					}
				}
			}
			if(gun.rightClickToShoot() == false){
				if (e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
					try {
						if(nbti.getBoolean("Gun") == true){
							WeaponShoot.shootWeapon(p, nbti);
						}
						if(nbti.getBoolean("Grenade") == true){
							
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
				if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
					try {
						//ZOOM GUN
						//ENABLE ATTACHMENT
					} catch (Exception e2) {
						
					}
				}
			}
			
		}
	}

}
