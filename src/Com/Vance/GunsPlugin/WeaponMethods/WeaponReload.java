package Com.Vance.GunsPlugin.WeaponMethods;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import com.connorlinfoot.bountifulapi.BountifulAPI;

import Com.Vance.GunsPlugin.Main;
import Com.Vance.GunsPlugin.Database.Config;
import Com.Vance.GunsPlugin.Database.Guns.Guns;
import Com.Vance.GunsPlugin.WeaponMethods.Runnables.ReloadRunnable;
import de.tr7zw.itemnbtapi.NBTItem;

public class WeaponReload {
	
	@SuppressWarnings({ "unused" })
	public static boolean reload(Player p, NBTItem nbti) {

		
		if(nbti.getBoolean("Weapon") != true){
			return false;
		}
		Guns gun = Main.getInstance().guns.get(nbti.getString("Weapon Type"));
		if(gun.usePermission() == true){
			if(!p.hasPermission(gun.getPermission())){
				p.sendMessage(Config.getConfiguration().getString("Messages.Permissions.Weapon Use"));
				return false;
			}
		}
		BountifulAPI.sendActionBar(p, Config.getConfiguration().getString("Messages.Reloading Message").replaceAll("%WeaponName%", gun.getName()).replaceAll("%MaxAmmo%", String.valueOf(gun.getCapacity())));
		nbti.setBoolean("Armed", false);
		if (p.getInventory().contains(gun.getAmmoItem().getType())) {
			gun.playReloadSound(p);
		}

		BukkitTask task = new ReloadRunnable(p, nbti, p.getInventory().getHeldItemSlot()).runTaskLater(Main.getInstance(), gun.getReloadDelay());
		nbti.setBoolean("Armed", true);

		return nbti.getBoolean("Armed");
	}

}
