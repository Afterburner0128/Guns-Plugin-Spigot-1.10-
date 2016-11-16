package Com.Vance.GunsPlugin.WeaponMethods.Runnables;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import Com.Vance.GunsPlugin.Database.Guns.Guns;
import Com.Vance.GunsPlugin.Listeners.ProjectileHitListeners;

public class RepeatEffectsRunnable extends BukkitRunnable{
	
	private Guns gun;
	private int i;
	private Player p;
	private Entity e;
	
	public RepeatEffectsRunnable(Player p, Guns gun, Entity e) {
		this.gun = gun;
		this.i = gun.getDamageSet().getRepeatTimer();
		this.e = e;
		this.p = p;
	}

	@Override
	public void run() {
		if(i == 0){
			this.cancel();
		}
		else{
			ProjectileHitListeners.runEffectRadius(p, gun, e);
		}
		i --;
		
	}

}
