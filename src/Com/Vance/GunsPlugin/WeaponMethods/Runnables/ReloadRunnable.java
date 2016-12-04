package Com.Vance.GunsPlugin.WeaponMethods.Runnables;

import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import com.connorlinfoot.bountifulapi.BountifulAPI;

import Com.Vance.GunsPlugin.Main;
import Com.Vance.GunsPlugin.Database.Config;
import Com.Vance.GunsPlugin.Database.Guns.Guns;
import Com.Vance.GunsPlugin.Utilliites.CreateItems;
import de.tr7zw.itemnbtapi.NBTItem;

public class ReloadRunnable extends BukkitRunnable{
	
	private Player p;
	private NBTItem nbti;
	private int slot;
	
	public ReloadRunnable(Player p, NBTItem nbti, int slot) {
		this.p = p;
		this.nbti = nbti;
		this.slot = slot;
	}
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		Guns gun = Main.getInstance().guns.get(nbti.getString("Weapon Type"));
		
		ItemStack is = new ItemStack(gun.getAmmoItem().getType(), nbti.getInteger("Current Amount"), gun.getAmmoItem().getDurability());
		ItemMeta ismeta = is.getItemMeta();
		ismeta.spigot().setUnbreakable(gun.getAmmoItem().getItemMeta().spigot().isUnbreakable());
		is.setItemMeta(ismeta);
		
		//CHECKS IF THE PLAYER DOESNT HAVE AMMO
		if(!p.getInventory().contains(gun.getAmmoItem().getType())){
			//REMOVES WHEN THE GUN IS STILL LOADED
			if(nbti.getInteger("Current Amount") > 0){
				//REMOVE AMMO INDIVIDUALY PARAMITER [TRUE]
				if(gun.removeAmmoIndividualy() == true){
					p.getInventory().addItem(new ItemStack(gun.getAmmoItem().getType(), nbti.getInteger("Current Amount"),gun.getAmmoItem().getDurability()));
				}
				//REMOVE AMMO INDIVIDUALY PARAMITER [FALSE]
				if(gun.removeAmmoIndividualy() == false){
					p.getInventory().addItem(CreateItems.createAmmo(gun, nbti.getInteger("Current Amount"), 1));
				}	
				//RESET THE GUN
				nbti.setInteger("Current Amount", -1);
				p.getInventory().setItem(slot, nbti.getItem());
				BountifulAPI.sendActionBar(p, Config.getConfiguration().getString("Messages.Empty Weapon").replaceAll("%WeaponName%", gun.getName()).replaceAll("%MaxAmmo%", String.valueOf(gun.getCapacity())).replaceAll("%AmmoAmount%", String.valueOf(nbti.getInteger("Current Amount"))));
			}
			else{
				BountifulAPI.sendActionBar(p, Config.getConfiguration().getString("Messages.Out of Ammo").replaceAll("%WeaponName%", gun.getName()).replaceAll("%MaxAmmo%", String.valueOf(gun.getCapacity())).replaceAll("%AmmoAmount%", String.valueOf(nbti.getInteger("Current Amount"))));
			}
			return;
		}
		//CHECKS IF THE PLAYER HAS AMMO
		for(Map.Entry<Integer, ? extends ItemStack> set: p.getInventory().all(gun.getAmmoItem().getType()).entrySet()){
			NBTItem ammonbti = new NBTItem(set.getValue());
			//CHECK IF THE AMMO HAS NEVER BEEN USED
			
			if(!ammonbti.hasKey("Capacity")){
				//REMOVES WHEN THE GUN IS STILL LOADED
				
				if(nbti.getInteger("Current Amount") > 0){
					//REMOVE AMMO INDIVIDUALY PARAMITER [TRUE]
					if(gun.removeAmmoIndividualy() == true){
						p.getInventory().addItem(new ItemStack(gun.getAmmoItem().getType(), nbti.getInteger("Current Amount"),gun.getAmmoItem().getDurability()));
					}
					//REMOVE AMMO INDIVIDUALY PARAMITER [FALSE]
					if(gun.removeAmmoIndividualy() == false){
						p.getInventory().addItem(CreateItems.createAmmo(gun, nbti.getInteger("Current Amount"), 1));
					}	
					//RESET THE GUN
					nbti.setInteger("Current Amount", -1);
					p.getInventory().setItem(slot, nbti.getItem());
					BountifulAPI.sendActionBar(p, Config.getConfiguration().getString("Messages.Empty Weapon").replaceAll("%WeaponName%", gun.getName()).replaceAll("%MaxAmmo%", String.valueOf(gun.getCapacity())).replaceAll("%AmmoAmount%", String.valueOf(nbti.getInteger("Current Amount"))));
					return;
				}
				//*IF THE GUN IS NOT LOADED DO THIS*
				if(gun.removeAmmoIndividualy() == true){
					//IF THE PLAYER HAS MORE AMMO THAN THE GUN CAN HOLD
					if(set.getValue().getAmount() < gun.getCapacity()){
						nbti.setInteger("Current Amount", set.getValue().getAmount());
						p.getInventory().removeItem(new ItemStack(gun.getAmmoItem().getType(), set.getValue().getAmount(), gun.getAmmoItem().getDurability()));
					}
					//IF THE PLAYER HAS LESS AMMO THAN THE GUN CAN HOLD
					if(set.getValue().getAmount() >= gun.getCapacity()){
						nbti.setInteger("Current Amount", gun.getCapacity());
						p.getInventory().removeItem(new ItemStack(gun.getAmmoItem().getType(),gun.getCapacity(), gun.getAmmoItem().getDurability()));
					}
					
				}
				if(gun.removeAmmoIndividualy() == false){
					
					nbti.setInteger("Current Amount", gun.getCapacity());
					p.getInventory().removeItem(CreateItems.createAmmo(gun, nbti.getInteger("Current Amount"),1));
				}
				BountifulAPI.sendActionBar(p, Config.getConfiguration().getString("Messages.Shoot Message").replaceAll("%AmmoAmount%", nbti.getInteger("Current Amount") + "").replaceAll("%WeaponName%", gun.getName()).replaceAll("%MaxAmmo%", String.valueOf(gun.getCapacity())));
				p.getInventory().setItem(slot, nbti.getItem());
				return;
				
			}
			//IF THE AMMO STILL HAS BULLETS LEFT
			if(ammonbti.hasKey("Capacity")){
				int NBTAmmoCount = ammonbti.getItem().getAmount() -1;
				//CHECK IF THE GUN HAS AMMO LEFT
				if(nbti.getInteger("Current Amount") > 0){
					if(gun.removeAmmoIndividualy() == true){
						//IF THE PLAYER HAS MORE AMMO THAN THE GUN CAN HOLD
						if(set.getValue().getAmount() < gun.getCapacity()){
							nbti.setInteger("Current Amount", set.getValue().getAmount());
							p.getInventory().removeItem(new ItemStack(gun.getAmmoItem().getType(), set.getValue().getAmount(), gun.getAmmoItem().getDurability()));
						}
						//IF THE PLAYER HAS LESS AMMO THAN THE GUN CAN HOLD
						if(set.getValue().getAmount() >= gun.getCapacity()){
							nbti.setInteger("Current Amount", gun.getCapacity());
							p.getInventory().removeItem(new ItemStack(gun.getAmmoItem().getType(),gun.getCapacity(), gun.getAmmoItem().getDurability()));
						}
					}
					if(gun.removeAmmoIndividualy() == false){
						p.getInventory().addItem(CreateItems.createAmmo(gun, nbti.getInteger("Current Amount"), 1));
					}
					nbti.setInteger("Current Amount", -1);
					p.getInventory().setItem(slot, nbti.getItem());
					BountifulAPI.sendActionBar(p, Config.getConfiguration().getString("Messages.Empty Weapon").replaceAll("%WeaponName%", gun.getName()).replaceAll("%MaxAmmo%", String.valueOf(gun.getCapacity())).replaceAll("%AmmoAmount%", String.valueOf(nbti.getInteger("Current Amount"))));
					return;
				}
				//IF THE GUN DOESNT HAVE AMMO LEFT
				if(gun.removeAmmoIndividualy() == true){
					if(set.getValue().getAmount() < gun.getCapacity()){
						nbti.setInteger("Current Amount", set.getValue().getAmount());
						p.getInventory().removeItem(ammonbti.getItem());
						p.getInventory().removeItem(new ItemStack(gun.getAmmoItem().getType(), set.getValue().getAmount(), gun.getAmmoItem().getDurability()));
					}
					if(set.getValue().getAmount() >= gun.getCapacity()){
						nbti.setInteger("Current Amount", ammonbti.getInteger("Capacity"));
						p.getInventory().removeItem(ammonbti.getItem());
						p.getInventory().removeItem(new ItemStack(gun.getAmmoItem().getType(), gun.getCapacity(), gun.getAmmoItem().getDurability()));
					}
					
				}
				if(gun.removeAmmoIndividualy() == false){
					nbti.setInteger("Current Amount", ammonbti.getInteger("Capacity"));
					p.getInventory().removeItem(ammonbti.getItem());
					p.getInventory().addItem(CreateItems.createAmmo(gun, nbti.getInteger("Current Amount"), NBTAmmoCount));	
				}
					
				BountifulAPI.sendActionBar(p, Config.getConfiguration().getString("Messages.Shoot Message").replaceAll("%AmmoAmount%", nbti.getInteger("Current Amount") + "").replaceAll("%WeaponName%", gun.getName()).replaceAll("%MaxAmmo%", String.valueOf(gun.getCapacity())));
				p.getInventory().setItem(slot, nbti.getItem());
				return;
				}
			
		}
		
	}

}
