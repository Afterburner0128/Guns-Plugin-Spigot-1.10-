package Com.Vance.GunsPlugin.Listeners.DamageEvents;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;

import Com.Vance.GunsPlugin.Main;
import Com.Vance.GunsPlugin.Database.Damage.Damage;
import Com.Vance.GunsPlugin.Utilliites.SetBlockMethods;

public class RunEffectRadius {
	
	public static void runEffectRadius(Player p,Damage damage, Entity e){
		//RUNS RADIUS
		if(damage.useEffectsRadius() == true){
			//SETS BLOCK
			if(damage.allowSetBlock() == true){
				if(damage.enableRepeatEffects() == false){
					if(e instanceof Projectile){
						SetBlockMethods block = new SetBlockMethods(damage, e.getLocation());
						block.setBlocks();
						if(damage.removeBlock() == true){
							Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(),  new Runnable(){

								@Override
								public void run() {
									block.removeBlocks(damage.getEffectsRadius("X"), damage.getEffectsRadius("Y"), damage.getEffectsRadius("Z"));
										
								}
								
							}, damage.removeBlockDelay());
						}
					}
				}	
			
			}
			for(Entity entity : e.getNearbyEntities(damage.getEffectsRadius("X"), damage.getEffectsRadius("Y"), damage.getEffectsRadius("Z"))){
				if(entity instanceof LivingEntity || entity instanceof Projectile){
					if(!(entity  instanceof Player)){
						ReturnEffects.returnEffects(p, damage, entity);
					}
				}	
			}
		}
		else{
			//RUNS SINGLE LOCATION
			ReturnEffects.returnEffects(p, damage, e);
		}
		
	}
}
