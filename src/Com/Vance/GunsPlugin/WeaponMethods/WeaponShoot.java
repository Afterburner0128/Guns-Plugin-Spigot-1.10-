package Com.Vance.GunsPlugin.WeaponMethods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import com.connorlinfoot.bountifulapi.BountifulAPI;

import Com.Vance.GunsPlugin.Main;
import Com.Vance.GunsPlugin.Database.Config;
import Com.Vance.GunsPlugin.Database.Guns.Guns;
import Com.Vance.GunsPlugin.WeaponMethods.Runnables.ShootRunnable;
import de.tr7zw.itemnbtapi.NBTItem;

public class WeaponShoot {
	
	@SuppressWarnings({ "deprecation", "unused" })
	public static boolean shootWeapon(Player p, NBTItem nbti){
		if(nbti.getBoolean("Armed") == false){
			return false;
		}
		Guns gun = Main.getInstance().guns.get(nbti.getString("Weapon Type"));
		if(gun.usePermission() == true){
			if(!p.hasPermission(gun.getPermission())){
				p.sendMessage(Config.getConfiguration().getString("Messages.Permissions.Weapon Use"));
				return false;
			}
		}
		if(nbti.getBoolean("Jammed") == true){
			BountifulAPI.sendActionBar(p, Config.getConfiguration().getString("Messages.Weapon Jammed").replaceAll("%WeaponName%", gun.getName()));
			return false;
		}		
	
		ItemStack item = p.getItemInHand();
		int itemSlot = p.getInventory().getHeldItemSlot();
		
		if(gun.getFirearmType().equals("SEMI-AUTOMATIC")){
			BukkitTask task = new ShootRunnable(p, nbti).runTaskLater(Main.getInstance(), gun.getShootDelay());
		}
		if(gun.getFirearmType().equals("AUTOMATIC")){
			//SHOOTS THE PROJECTILE TWICE, BECAUSE I DONT KNOW HOW TO SHOOT THE GUN BASED ON ROUNDS PER MINUITE, WITHN BUKKIT (AND NOT MAKE THE SERVER LAG LIKE CRAP)
			//THIS IS THE MOST SIMPLE, AND EFFECTIVE METHOD
			BukkitTask task = new ShootRunnable(p, nbti).runTaskLater(Main.getInstance(), 0);
			BukkitTask task1 = new ShootRunnable(p, nbti).runTaskLater(Main.getInstance(), gun.getShootDelay());
		}
		if(gun.getFirearmType().equals("BOLT-ACTION")){
			BukkitTask task = new ShootRunnable(p, nbti).runTaskLater(Main.getInstance(), 0);
			
			if(nbti.getInteger("Current Amount") >= 1){
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable(){
					
					@Override
					public void run() {
						nbti.setBoolean("Armed", true);
						p.getInventory().setItem(itemSlot, nbti.getItem());
						
					}
					
				}, gun.getShootDelay());
			}
		}
		return nbti.getBoolean("Armed");
	}

}
