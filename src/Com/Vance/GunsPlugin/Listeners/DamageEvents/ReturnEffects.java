package Com.Vance.GunsPlugin.Listeners.DamageEvents;

import java.util.Arrays;
import java.util.List;

import org.bukkit.FireworkEffect;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import Com.Vance.GunsPlugin.Database.Damage.Damage;
import Com.Vance.GunsPlugin.Utilliites.Colors;

public class ReturnEffects {
	
	public static void returnEffects(Player p,Damage damage, Entity e){
		if(e instanceof LivingEntity){
			((LivingEntity) e).damage(damage.getDamage());
		}
		//ADDS EXPERIANCE
		if(damage.addsExperiance() == true){
			p.giveExp(damage.addExperiance());
		}
		//GENERATES EXPLOSION
		if(damage.explosive() == true){
			p.getLocation().getWorld().createExplosion(e.getLocation().getX(), e.getLocation().getY(), e.getLocation().getZ(), damage.explosionSize(), damage.incendiary(), damage.explosionBlockDamage());
		}
		//LIGHTNING
		if(damage.enableLightning() == true){
			if(e instanceof Projectile){
				if(damage.runOnHit() == true){
					p.getWorld().strikeLightning(e.getLocation());
				}
			}
			else{
				p.getWorld().strikeLightning(e.getLocation());
			}	
			
		}
		//FIREWORKS
		if(damage.enabledFireworks() == true){
			if(e instanceof Projectile){
				if(damage.runOnHit() == true){
					Firework firework = e.getLocation().getWorld().spawn(e.getLocation().clone().add(0,2,0), Firework.class);
					FireworkMeta meta = firework.getFireworkMeta();
					
					for(String a : damage.fireworkColors()){
						meta.addEffect(FireworkEffect.builder().flicker(false).trail(damage.fireworkTrail()).withColor(Colors.getColor(a)).build());
					}
					firework.setFireworkMeta(meta);
						
					if(damage.detonateFireworkOnSpawn() == true){
						firework.detonate();
					}
					
				}
			}
			else{
				Firework firework = e.getLocation().getWorld().spawn(e.getLocation().clone().add(0,2,0), Firework.class);
				FireworkMeta meta = firework.getFireworkMeta();
				
				for(String a : damage.fireworkColors()){
					meta.addEffect(FireworkEffect.builder().flicker(false).trail(damage.fireworkTrail()).withColor(Colors.getColor(a)).build());
				}
				firework.setFireworkMeta(meta);
				
				if(damage.detonateFireworkOnSpawn() == true){
					firework.detonate();
				}
			}	
			
		}
		//POTION EFFECTS
		if(damage.allowPotions() == true){
			if(!(e instanceof LivingEntity)){
				return;
			}
			LivingEntity entity = (LivingEntity) e;
			for(String s : damage.getPotionEffects()){
				List<String> list = Arrays.asList(s.replaceAll(" ", "").split(","));
				entity.addPotionEffect(new PotionEffect(PotionEffectType.getByName(list.get(0)), Integer.parseInt(list.get(1)),Integer.parseInt(list.get(2))));
			}
			
		}
	}
}
