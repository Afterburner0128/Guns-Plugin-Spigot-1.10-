package Com.Vance.GunsPlugin.Listeners;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import Com.Vance.GunsPlugin.Main;
import Com.Vance.GunsPlugin.Database.Guns.Guns;
import Com.Vance.GunsPlugin.Utilliites.Colors;
import Com.Vance.GunsPlugin.Utilliites.SetBlockMethods;
import Com.Vance.GunsPlugin.WeaponMethods.Runnables.RepeatEffectsRunnable;
import de.tr7zw.itemnbtapi.NBTItem;

public class ProjectileHitListeners implements Listener{
	
	@EventHandler
	@SuppressWarnings("deprecation")
	public void on(EntityDamageByEntityEvent e){
		if(!(e.getDamager() instanceof Projectile)){
			return;
		}
		Player p = (Player) ((Projectile) e.getDamager()).getShooter();
		NBTItem nbti = new NBTItem(p.getItemInHand());
		
		if(nbti.getBoolean("Weapon") == true){
			if(nbti.getBoolean("Gun") == true){
				Guns gun = Main.getInstance().guns.get(nbti.getString("Weapon Type"));
				runRepeatEffects(p, gun, e.getEntity());
				
				//SETS WEAPON DAMAGE, AND PLAYS THE HIT SOUND
				
				gun.getProjectileSet().playHitSound(p);
			}
		}
	}
	@EventHandler
	@SuppressWarnings("deprecation")
	public void on(ProjectileHitEvent e){
		if(e.getEntity().getShooter() instanceof Player){
			Player p = (Player) e.getEntity().getShooter();
			NBTItem nbti = new NBTItem(p.getItemInHand());
			
			if(nbti.getBoolean("Weapon") == true){
				if(nbti.getBoolean("Gun") == true){
					Guns gun = Main.getInstance().guns.get(nbti.getString("Weapon Type"));
					if(e.getEntity() instanceof Arrow){
						if(gun.getProjectileSet().removeOnArrowHit() == true){
							e.getEntity().remove();
						}
					}
					runRepeatEffects(p, gun, e.getEntity());
					//REMOVES THE ARROW ON HIT
			
				}
			}
		}
	}
	@EventHandler
	@SuppressWarnings("deprecation")
	public void on(PlayerEggThrowEvent e){
		if(e.getEgg().getShooter() instanceof Player){
			Player p = (Player) e.getEgg().getShooter();		
			NBTItem nbti = new NBTItem(p.getItemInHand());
			
			if(nbti.getBoolean("Weapon") == true){
				if(nbti.getBoolean("Gun") == true){
					Guns gun = Main.getInstance().guns.get(nbti.getString("Weapon Type"));
					//CANCELS EGG HATCHING
					if(gun.getProjectileSet().cancelEggSpawn() == true){
						e.setHatching(false);
					}
				}
			}
		}
	}
	@EventHandler
	@SuppressWarnings("deprecation")
	public void on(PlayerTeleportEvent e){
		if(e.getCause() == TeleportCause.ENDER_PEARL){
			Player p = e.getPlayer();
			NBTItem nbti = new NBTItem(p.getItemInHand());
			
			if(nbti.getBoolean("Weapon") == true){
				if(nbti.getBoolean("Gun") == true){
					Guns gun = Main.getInstance().guns.get(nbti.getString("Weapon Type"));
					//CANCEL ENDERPEARL TELEPORT
					if(gun.getProjectileSet().cancelTeleport() == true){
						e.setCancelled(true);
					}
				}
			}
		}
	}
	@SuppressWarnings("unused")
	public static void runRepeatEffects(Player p,Guns gun, Entity e){
		if(gun.getDamageSet().enableDelayEffects() == true){
			if(gun.getDamageSet().enableRepeatEffects() == true){
				//RUNS DELAY
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable(){

					@Override
					public void run() {
						BukkitTask task = new RepeatEffectsRunnable(p, gun, e).runTaskTimer(Main.getInstance(), 0, gun.getDamageSet().getRepeatDelay());
	
					}
					
				}, gun.getDamageSet().getDelayTimer());
				return;
			}
			//RUNS DELAY
			if(gun.getDamageSet().enableDelayEffects() == true){
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable(){

					@Override
					public void run() {
						runEffectRadius(p, gun, e);
					}
					
				}, gun.getDamageSet().getDelayTimer());
			}
			return;
		}
		if(gun.getDamageSet().enableDelayEffects() == false){
			if(gun.getDamageSet().enableRepeatEffects() == true){
				//RUNS REPEAT
				BukkitTask task = new RepeatEffectsRunnable(p, gun, e).runTaskTimer(Main.getInstance(), 0, gun.getDamageSet().getRepeatDelay());
				return;
			}
			runEffectRadius(p, gun, e);
			return;
		}
	}
	public static void runEffectRadius(Player p,Guns gun, Entity e){
		//RUNS RADIUS
		if(gun.getDamageSet().useEffectsRadius() == true){
			//SETS BLOCK
			if(gun.getDamageSet().allowSetBlock() == true){
				if(gun.getDamageSet().enableRepeatEffects() == false){
					if(e instanceof Projectile){
						SetBlockMethods block = new SetBlockMethods(gun.getDamageSet(), e.getLocation());
						block.setBlocks();
						if(gun.getDamageSet().removeBlock() == true){
							Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(),  new Runnable(){

								@Override
								public void run() {
									block.removeBlocks(gun.getDamageSet().getEffectsRadius("X"), gun.getDamageSet().getEffectsRadius("Y"), gun.getDamageSet().getEffectsRadius("Z"));
										
								}
								
							}, gun.getDamageSet().removeBlockDelay());
						}
					}
				}	
			
			}
			for(Entity entity : e.getNearbyEntities(gun.getDamageSet().getEffectsRadius("X"), gun.getDamageSet().getEffectsRadius("Y"), gun.getDamageSet().getEffectsRadius("Z"))){
				if(entity instanceof LivingEntity || entity instanceof Projectile){
					if(!(entity  instanceof Player)){
						returnEffects(p, gun, entity);
					}
				}	
			}
		}
		else{
			//RUNS SINGLE LOCATION
			returnEffects(p, gun, e);
		}
		
	}
	public static void returnEffects(Player p,Guns gun, Entity e){
		if(e instanceof LivingEntity){
			((LivingEntity) e).damage(gun.getDamageSet().getDamage());
		}
		//ADDS EXPERIANCE
		if(gun.getDamageSet().addsExperiance() == true){
			p.giveExp(gun.getDamageSet().addExperiance());
		}
		//GENERATES EXPLOSION
		if(gun.getDamageSet().explosive() == true){
			p.getLocation().getWorld().createExplosion(e.getLocation().getX(), e.getLocation().getY(), e.getLocation().getZ(), gun.getDamageSet().explosionSize(), gun.getDamageSet().incendiary(), gun.getDamageSet().explosionBlockDamage());
		}
		//LIGHTNING
		if(gun.getDamageSet().enableLightning() == true){
			if(e instanceof Projectile){
				if(gun.getDamageSet().runOnHit() == true){
					p.getWorld().strikeLightning(e.getLocation());
				}
			}
			else{
				p.getWorld().strikeLightning(e.getLocation());
			}	
			
		}
		//FIREWORKS
		if(gun.getDamageSet().enabledFireworks() == true){
			if(e instanceof Projectile){
				if(gun.getDamageSet().runOnHit() == true){
					Firework firework = e.getLocation().getWorld().spawn(e.getLocation().clone().add(0,2,0), Firework.class);
					FireworkMeta meta = firework.getFireworkMeta();
					
					for(String a : gun.getDamageSet().fireworkColors()){
						meta.addEffect(FireworkEffect.builder().flicker(false).trail(gun.getDamageSet().fireworkTrail()).withColor(Colors.getColor(a)).build());
					}
					firework.setFireworkMeta(meta);
						
					if(gun.getDamageSet().detonateFireworkOnSpawn() == true){
						firework.detonate();
					}
					
				}
			}
			else{
				Firework firework = e.getLocation().getWorld().spawn(e.getLocation().clone().add(0,2,0), Firework.class);
				FireworkMeta meta = firework.getFireworkMeta();
				
				for(String a : gun.getDamageSet().fireworkColors()){
					meta.addEffect(FireworkEffect.builder().flicker(false).trail(gun.getDamageSet().fireworkTrail()).withColor(Colors.getColor(a)).build());
				}
				firework.setFireworkMeta(meta);
				
				if(gun.getDamageSet().detonateFireworkOnSpawn() == true){
					firework.detonate();
				}
			}	
			
		}
		//POTION EFFECTS
		if(gun.getDamageSet().allowPotions() == true){
			if(!(e instanceof LivingEntity)){
				return;
			}
			LivingEntity entity = (LivingEntity) e;
			for(String s : gun.getDamageSet().getPotionEffects()){
				List<String> list = Arrays.asList(s.replaceAll(" ", "").split(","));
				entity.addPotionEffect(new PotionEffect(PotionEffectType.getByName(list.get(0)), Integer.parseInt(list.get(1)),Integer.parseInt(list.get(2))));
			}
			
		}
	}

}
