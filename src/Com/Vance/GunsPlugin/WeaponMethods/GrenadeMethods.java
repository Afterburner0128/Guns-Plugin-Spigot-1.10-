package Com.Vance.GunsPlugin.WeaponMethods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import Com.Vance.GunsPlugin.Main;
import Com.Vance.GunsPlugin.Database.Grenades.Grenades;
import Com.Vance.GunsPlugin.Listeners.DamageEvents.RunRepeatEffects;
import de.tr7zw.itemnbtapi.NBTItem;

public class GrenadeMethods {

	@SuppressWarnings("deprecation")
	public static void throwGrenade(Player p, NBTItem nbti){
		
		if(nbti.getBoolean("Armed") == false){
			return;
		}
		if(nbti.getBoolean("Grenade") == false){
			return;
		}
		Grenades grenade = Main.getInstance().grenades.get(nbti.getString("Weapon Type"));
		
		Entity thrownGrenade = p.getWorld().dropItem(p.getLocation(), p.getItemInHand());
		thrownGrenade.setVelocity(p.getLocation().getDirection().multiply(grenade.getVelocity()));
		
		ItemStack i = new ItemStack(p.getItemInHand().getType(), 1, p.getItemInHand().getDurability());
		p.getInventory().remove(i);
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable(){

			@Override
			public void run() {
				RunRepeatEffects.runRepeatEffects(p, grenade.getDamageSet(), thrownGrenade);
			}
		}, grenade.getTimer());
	}
}
