package Com.Vance.GunsPlugin.WeaponMethods.Runnables;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import Com.Vance.GunsPlugin.Database.Damage.Damage;
import Com.Vance.GunsPlugin.Listeners.DamageEvents.RunEffectRadius;

public class RepeatEffectsRunnable extends BukkitRunnable{
	
	private Damage damage;
	private int i;
	private Player p;
	private Entity e;
	
	public RepeatEffectsRunnable(Player p, Damage damage, Entity e) {
		this.damage = damage;
		this.i = damage.getRepeatTimer();
		this.e = e;
		this.p = p;
	}

	@Override
	public void run() {
		if(i == 0){
			this.cancel();
		}
		else{
			RunEffectRadius.runEffectRadius(p, damage, e);
		}
		i --;
		
	}

}
