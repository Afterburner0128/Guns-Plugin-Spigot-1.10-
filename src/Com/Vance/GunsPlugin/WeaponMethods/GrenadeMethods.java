package Com.Vance.GunsPlugin.WeaponMethods;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import Com.Vance.GunsPlugin.Main;
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
		System.out.println(1);
		Item thrownGrenade = p.getWorld().dropItem(p.getEyeLocation(), p.getItemInHand());
		thrownGrenade.setVelocity(p.getLocation().getDirection().multiply(nbti.getInteger("Throwing Velocity")));
		p.setItemInHand(new ItemStack(Material.AIR));
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable(){

			@Override
			public void run() {
				if(nbti.getBoolean("Enable Repeat Effects") == true){
					//@SuppressWarnings("unused")
					//BukkitTask task = new RepeatEffectsRunnable(p,thrownGrenade, nbti, nbti.getInteger("Effects Repeat Time")).runTaskTimer(Main.getInstance(), nbti.getInteger("Effects Repeat Delay"), 20);
					return;
				}
				if(nbti.getBoolean("Explosive") == true){
					p.getWorld().createExplosion(thrownGrenade.getLocation().getX(),thrownGrenade.getLocation().getY(),thrownGrenade.getLocation().getZ(), (float) nbti.getDouble("Explosion Size"), nbti.getBoolean("Incendiary"), nbti.getBoolean("Explosion Block Damage"));
	
				}
				if(nbti.getBoolean("Allow Particles") == true){
					String[] s = nbti.getString("Particles").split(",");
					for(String a : s){
						p.getWorld().playEffect(thrownGrenade.getLocation(), Effect.valueOf(a), 10);
					}
					
				}
				if(nbti.getBoolean("Allow Potion")){
					String[] s = nbti.getString("Potion").split(",");
					for(String a : s){
						for(Entity e : thrownGrenade.getNearbyEntities(nbti.getInteger("Effects Radius"), nbti.getInteger("Effects Radius"), nbti.getInteger("Effects Radius"))){
							if(e instanceof LivingEntity){
								LivingEntity l = (LivingEntity) e;
								String[] f = a.split(";");
								List<String> string = Arrays.asList(f);
								l.addPotionEffect(new PotionEffect(PotionEffectType.getByName(string.get(0)), Integer.valueOf(string.get(1)), Integer.valueOf(string.get(2))));
							}
						}
						
					}
				}
				if(nbti.getBoolean("Enabled Fireworks")){
					Firework firework = thrownGrenade.getWorld().spawn(thrownGrenade.getLocation().clone().add(0,2,0), Firework.class);
					FireworkMeta meta = firework.getFireworkMeta();
					meta.addEffect(FireworkEffect.builder().flicker(false).trail(false).build());
					
					//String[] s = nbti.getString("Colors").split(",");
					//for(String a : s){
						//meta.addEffect(FireworkEffect.builder().withColor(Colors.getColor(nbti.getString(a))).build());
					//}
					firework.setFireworkMeta(meta);
					
					if(nbti.getBoolean("Detonate on Spawn")){
						firework.detonate();
					}
					
				}
				if(nbti.getBoolean("Set Block")){
					for (int x = -nbti.getInteger("Effects Radius"); x <= nbti.getInteger("Effects Radius"); x++) {
						for (int y = -nbti.getInteger("Effects Radius"); y <= nbti.getInteger("Effects Radius"); y++) {
							for (int z = -nbti.getInteger("Effects Radius"); z <= nbti.getInteger("Effects Radius"); z++) {
								Location loc = thrownGrenade.getLocation().clone().add(x, y, z);
								if (loc.distance(thrownGrenade.getLocation()) <= nbti.getInteger("Effects Radius")) {
									if (loc.getBlock().getType() == Material.AIR) {
										loc.getBlock().setType(Material.getMaterial(nbti.getInteger("Block Type")));
									}
								}
							}
						}
						
					}
					if(nbti.getBoolean("Enabled Block Despawn")){
						Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable(){

							@Override
							public void run() {
								for (int x = -nbti.getInteger("Effects Radius"); x <= nbti.getInteger("Effects Radius"); x++) {
									for (int y = -nbti.getInteger("Effects Radius"); y <= nbti.getInteger("Effects Radius"); y++) {
										for (int z = -nbti.getInteger("Effects Radius"); z <= nbti.getInteger("Effects Radius"); z++) {
											Location loc = thrownGrenade.getLocation().clone().add(x, y, z);
											if (loc.distance(thrownGrenade.getLocation()) <= nbti.getInteger("Effects Radius")) {
												if (loc.getBlock().getType() == Material.getMaterial(nbti.getInteger("Block Type"))) {
													loc.getBlock().setType(Material.AIR);
												}
											}
										}
									}
								}
								
							}
							
						}, nbti.getInteger("Block Despawn Timer"));
					}
					
				}
				/*if(thrownGrenade.getItemStack().getType().equals(Material.PRISMARINE_CRYSTALS)){
					
					BukkitTask task = new SmokeGrenadeRunnable(thrownGrenade, grenade).runTaskTimer(Main.getInstance(), 0, 20);
					thrownGrenade.remove();
					
				}
				//INCENDIARY GRENADE
				if(thrownGrenade.getItemStack().getType().equals(Material.BEETROOT_SEEDS)){
					BukkitTask task = new IncendiaryGrenadeRunnable(grenade, thrownGrenade).runTaskTimer(Main.getInstance(), 0, 20);
				}
				//MOLOTOV
				if(thrownGrenade.getItemStack().getType().equals(Material.RABBIT_HIDE)){
					BukkitTask task = new IncendiaryGrenadeRunnable(grenade, thrownGrenade).runTaskTimer(Main.getInstance(), 0, 20);
				}
				//FLASHBANG
				if(thrownGrenade.getItemStack().getType().equals(Material.INK_SACK)){
					//CHECKS IF THE PLAYER IS WITHIN THE GRENADES EFFECT RANGE
					for(Player all :thrownGrenade.getWorld().getPlayers()){
						if (all.getLocation().distance(thrownGrenade.getLocation()) <= grenade.getBlastRange()) {
							all.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 260, 2));
							all.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 260, 2));
							all.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 260, 4));
						}
					}
					
					
					thrownGrenade.remove();
				}*/
				
			}
			
		}, nbti.getInteger("Detonation Timer"));
		
	}
}
