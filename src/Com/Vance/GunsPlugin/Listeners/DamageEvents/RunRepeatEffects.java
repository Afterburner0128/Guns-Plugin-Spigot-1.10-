package Com.Vance.GunsPlugin.Listeners.DamageEvents;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import Com.Vance.GunsPlugin.Main;
import Com.Vance.GunsPlugin.Database.Damage.Damage;
import Com.Vance.GunsPlugin.WeaponMethods.Runnables.RepeatEffectsRunnable;

public class RunRepeatEffects {
	
	@SuppressWarnings("unused")
	public static void runRepeatEffects(Player p,Damage damage, Entity e){
		if(damage.enableDelayEffects() == true){
			if(damage.enableRepeatEffects() == true){
				//RUNS DELAY
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable(){

					@Override
					public void run() {
						BukkitTask task = new RepeatEffectsRunnable(p, damage, e).runTaskTimer(Main.getInstance(), 0, damage.getRepeatDelay());
	
					}
					
				}, damage.getDelayTimer());
				return;
			}
			//RUNS DELAY
			if(damage.enableDelayEffects() == true){
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable(){

					@Override
					public void run() {
						RunEffectRadius.runEffectRadius(p, damage, e);
					}
					
				}, damage.getDelayTimer());
			}
			return;
		}
		if(damage.enableDelayEffects() == false){
			if(damage.enableRepeatEffects() == true){
				//RUNS REPEAT
				BukkitTask task = new RepeatEffectsRunnable(p, damage, e).runTaskTimer(Main.getInstance(), 0, damage.getRepeatDelay());
				return;
			}
			RunEffectRadius.runEffectRadius(p, damage, e);
			return;
		}
	}

}
