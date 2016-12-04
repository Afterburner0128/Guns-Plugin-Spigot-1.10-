package Com.Vance.GunsPlugin.Utilliites;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import Com.Vance.GunsPlugin.Main;
import Com.Vance.GunsPlugin.Database.Config;
import Com.Vance.GunsPlugin.Database.Grenades.Grenades;
import Com.Vance.GunsPlugin.Database.Guns.Guns;
import de.tr7zw.itemnbtapi.NBTItem;

public class CreateItems {
	
	public static ItemStack createGun(String id) {
		for(Guns gun : Main.getInstance().guns.values()){
			if(gun.getID() == id){
				
				NBTItem nbti = new NBTItem(gun.getWeaponItem());
				
				nbti.setBoolean("Armed", true);
				nbti.setBoolean("Gun", true);
				nbti.setBoolean("Weapon", true);
				nbti.setInteger("Current Amount", gun.getCapacity());
				nbti.setString("Weapon Type", gun.getID());
				return nbti.getItem();
			}
		}
		return null;
	}
	@SuppressWarnings("deprecation")
	public static ItemStack createGrenade(String id) {
		for(Grenades grenade : Main.getInstance().grenades.values()){
			if(grenade.getID() == id){
				
				ItemStack gunItem = new ItemStack(Material.getMaterial(grenade.getItem()), grenade.getItemAmount(), (short) grenade.getItemData());
				ItemMeta gunItemMeta = gunItem.getItemMeta();
				gunItemMeta.setDisplayName(grenade.getName());
				gunItemMeta.spigot().setUnbreakable(grenade.getUnbreakable());

				gunItemMeta.setLore(grenade.getItemDescription());
				gunItem.setItemMeta(gunItemMeta);
				
				NBTItem nbti = new NBTItem(gunItem);
				
				nbti.setBoolean("Armed", true);
				nbti.setBoolean("Grenade", true);
				nbti.setBoolean("Weapon", true);
				nbti.setString("Weapon Type", grenade.getID());
				return nbti.getItem();
			}
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	public static ItemStack createAmmo(Guns gun, int weaponAmmo, int amount){
		if(amount <= 0){
			return new ItemStack(Material.AIR);
		}
	
		
		ItemStack ammo = new ItemStack(gun.getAmmoItem().getType(),amount ,gun.getAmmoItem().getDurability());
		
		ItemMeta ammoMeta = ammo.getItemMeta();
		ammoMeta.setDisplayName(Config.getConfiguration().getString("Messages.Ammo Item Display").replaceAll("%GunName%", gun.getWeaponItem().getItemMeta().getDisplayName()).replaceAll("%AmmoAmount%", String.valueOf(weaponAmmo)));
		ammoMeta.spigot().setUnbreakable(gun.getAmmoItem().getItemMeta().spigot().isUnbreakable());
		ammo.setItemMeta(ammoMeta);
		
		NBTItem nbti = new NBTItem(ammo);
		nbti.setInteger("Capacity", weaponAmmo);
		return nbti.getItem();
	}
}
