package Com.Vance.GunsPlugin.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import Com.Vance.GunsPlugin.Main;
import Com.Vance.GunsPlugin.Database.Guns.Guns;
import Com.Vance.GunsPlugin.WeaponMethods.GrenadeMethods;
import Com.Vance.GunsPlugin.WeaponMethods.WeaponShoot;
import de.tr7zw.itemnbtapi.NBTItem;

public class PlayerInteractListener implements Listener{
	
	@EventHandler
	public void on(PlayerInteractEvent e){
		Player p = e.getPlayer();
		ItemStack item = e.getItem();
		//NULL CHECKS
		if (item == null) {
			return;
		}
		if (!item.hasItemMeta() || !item.getItemMeta().hasDisplayName()) {
			return;
		}
		NBTItem nbti = new NBTItem(item);
		if(nbti.getBoolean("Weapon") == false){
			return;
		}
		//INSTANCEOF GUN
		if(nbti.getBoolean("Gun") == true){
			Guns gun = Main.getInstance().guns.get(nbti.getString("Weapon Type"));
			if(gun.rightClickToShoot() == true){
				if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
					try {
						WeaponShoot.shootWeapon(p, nbti);
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
				if (e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
					try {
						//TODO ZOOM GUN
						//TODO ENABLE ATTACHMENT
					} catch (Exception e2) {
						
					}
				}
			}
			if(gun.rightClickToShoot() == false){
				if (e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
					try {
						WeaponShoot.shootWeapon(p, nbti);
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
				if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
					try {
						//TODO ZOOM GUN
						//TODO ENABLE ATTACHMENT
					} catch (Exception e2) {
							
					}
				}
			}
		}
		//INSTANCEOF GRENADE
		if(nbti.getBoolean("Grenade") == true){
			//Grenades grenade = Main.getInstance().grenades.get(nbti.getString("Weapon Type"));
			try {
				GrenadeMethods.throwGrenade(p, nbti);
			} catch (Exception e2) {
				
			}
			
		}

	}

}
