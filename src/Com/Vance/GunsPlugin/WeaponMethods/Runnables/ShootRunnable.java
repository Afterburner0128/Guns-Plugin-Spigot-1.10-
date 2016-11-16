package Com.Vance.GunsPlugin.WeaponMethods.Runnables;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.connorlinfoot.bountifulapi.BountifulAPI;

import Com.Vance.GunsPlugin.Main;
import Com.Vance.GunsPlugin.Database.Config;
import Com.Vance.GunsPlugin.Database.Guns.Guns;
import Com.Vance.GunsPlugin.Listeners.PlayerReloadListener;
import Com.Vance.GunsPlugin.Utilliites.ProjectileMethods;
import Com.Vance.GunsPlugin.WeaponMethods.WeaponReload;
import de.tr7zw.itemnbtapi.NBTItem;

public class ShootRunnable extends BukkitRunnable{
	
	private Player p;
	private NBTItem nbti;
	private static int taskId;
	
	public ShootRunnable(Player p, NBTItem nbti) {
		this.p = p;
		this.nbti = nbti;
	}

	@Override
	@SuppressWarnings("deprecation")
	public void run() {

		Guns gun = Main.getInstance().guns.get(nbti.getString("Weapon Type"));
		//JAMMING PARAMITER
		if(gun.canJam() == true){
			Random r = new Random();
			if(r.nextInt(gun.jamRate()) == 1){
				nbti.setBoolean("Jammed", true);				
			}
		}
		if(nbti.getInteger("Current Amount") > 0){
			
		//LAUNCH PROJECTILE
			
			ProjectileMethods.launchProjectile(gun.getProjectileSet(), p);
			gun.playShootSound(p);

			nbti.setInteger("Current Amount", nbti.getInteger("Current Amount") - 1);
			
			//BOLT ACTION PARAMITER
			if(gun.getFirearmType().equals("BOLT-ACTION")){
				nbti.setBoolean("Armed", false);
			}
			//RESET THE GUN ITEM
			p.setItemInHand(nbti.getItem());
			BountifulAPI.sendActionBar(p, Config.getConfiguration().getString("Messages.Shoot Message").replaceAll("%AmmoAmount%", nbti.getInteger("Current Amount") + "").replaceAll("%WeaponName%", gun.getName()).replaceAll("%MaxAmmo%", String.valueOf(gun.getCapacity())));
		}
		if(nbti.getInteger("Current Amount") == 0){
			
			//CHECK IF THE PLAYER HAS AMMO
			if(p.getInventory().contains(new ItemStack(gun.getAmmoItem().getType(), 1, gun.getAmmoItem().getDurability()))){
				BountifulAPI.sendActionBar(p, Config.getConfiguration().getString("Messages.Empty Weapon").replaceAll("%WeaponName%", gun.getName()).replaceAll("%MaxAmmo%", String.valueOf(gun.getCapacity())).replaceAll("%AmmoAmount%", String.valueOf(nbti.getInteger("Current Amount"))));
			}
			else{
				BountifulAPI.sendActionBar(p, Config.getConfiguration().getString("Messages.Out of Ammo").replaceAll("%WeaponName%", gun.getName()).replaceAll("%MaxAmmo%", String.valueOf(gun.getCapacity())).replaceAll("%AmmoAmount%", String.valueOf(nbti.getInteger("Current Amount"))));
			}
			
			//BOLT ACTION PARAMITER
			if(gun.getFirearmType().equals("BOLT-ACTION")){
				nbti.setBoolean("Armed", false);
			}
			//RESET THE GUN ITEM
			nbti.setInteger("Current Amount", - 1);
			p.setItemInHand(nbti.getItem());
			
			//HAMMER STRIKE PARAMITER
			gun.playHammerSound(p);
			
		}
		if(nbti.getInteger("Current Amount") < 0){
			//CHECK IF THE PLAYER HAS AMMO
			if(p.getInventory().contains(new ItemStack(gun.getAmmoItem().getType(), gun.getAmmoItem().getDurability()))){
				BountifulAPI.sendActionBar(p, Config.getConfiguration().getString("Messages.Empty Weapon").replaceAll("%WeaponName%", gun.getName()).replaceAll("%MaxAmmo%", String.valueOf(gun.getCapacity())).replaceAll("%AmmoAmount%", String.valueOf(nbti.getInteger("Current Amount"))));
			}
			else{
				BountifulAPI.sendActionBar(p, Config.getConfiguration().getString("Messages.Out of Ammo").replaceAll("%WeaponName%", gun.getName()).replaceAll("%MaxAmmo%", String.valueOf(gun.getCapacity())).replaceAll("%AmmoAmount%", String.valueOf(nbti.getInteger("Current Amount"))));
			}
			if(gun.automaticReload() == true){
				if(nbti.getBoolean("Weapon") == true){
					if(PlayerReloadListener.cooldown.containsKey(p.getName())){
						if(PlayerReloadListener.cooldown.get(p.getName()) > 0){
							return;
						}
					}
					taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable(){

						@Override
						public void run() {
							if(PlayerReloadListener.cooldown.get(p.getName()) > 0){
								PlayerReloadListener.cooldown.put(p.getName(), PlayerReloadListener.cooldown.get(p.getName()) -1);
								return;
							}
							else{
								PlayerReloadListener.cooldown.remove(p.getName());
								Bukkit.getScheduler().cancelTask(taskId);
							}
							
						}
						
					}, 0, 1);
					WeaponReload.reload(p,nbti);
					PlayerReloadListener.cooldown.put(p.getName(), gun.getReloadDelay() + 30);
				}

			}
			
		}
	}

}
