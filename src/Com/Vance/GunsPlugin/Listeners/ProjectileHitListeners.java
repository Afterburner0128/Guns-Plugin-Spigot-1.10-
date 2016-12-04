package Com.Vance.GunsPlugin.Listeners;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import Com.Vance.GunsPlugin.Main;
import Com.Vance.GunsPlugin.Database.Guns.Guns;
import Com.Vance.GunsPlugin.Listeners.DamageEvents.RunRepeatEffects;
import de.tr7zw.itemnbtapi.NBTItem;

public class ProjectileHitListeners implements Listener {

	@EventHandler
	@SuppressWarnings("deprecation")
	public void on(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Projectile)) {
			return;
		}
		Player p = (Player) ((Projectile) e.getDamager()).getShooter();
		NBTItem nbti = new NBTItem(p.getItemInHand());

		if (nbti.getBoolean("Weapon") == false) {
			return;
		}
		if (nbti.getBoolean("Gun") == false) {
			return;
		}
		Guns gun = Main.getInstance().guns.get(nbti.getString("Weapon Type"));
		RunRepeatEffects.runRepeatEffects(p, gun.getDamageSet(), e.getEntity());
		// SETS WEAPON DAMAGE, AND PLAYS THE HIT SOUND
		gun.getProjectileSet().playHitSound(p);
			
	}
	@EventHandler
	@SuppressWarnings("deprecation")
	public void on(ProjectileHitEvent e) {
		if (e.getEntity().getShooter() instanceof Player) {
			Player p = (Player) e.getEntity().getShooter();
			NBTItem nbti = new NBTItem(p.getItemInHand());

			if (nbti.getBoolean("Weapon") == true) {
				if (nbti.getBoolean("Gun") == true) {
					Guns gun = Main.getInstance().guns.get(nbti.getString("Weapon Type"));
					if (e.getEntity() instanceof Arrow) {
						if (gun.getProjectileSet().removeOnArrowHit() == true) {
							e.getEntity().remove();
						}
					}
					RunRepeatEffects.runRepeatEffects(p, gun.getDamageSet(), e.getEntity());
					// REMOVES THE ARROW ON HIT
				}
			}
		}
	}
	@EventHandler
	@SuppressWarnings("deprecation")
	public void on(PlayerEggThrowEvent e) {
		if (e.getEgg().getShooter() instanceof Player) {
			Player p = (Player) e.getEgg().getShooter();
			NBTItem nbti = new NBTItem(p.getItemInHand());

			if (nbti.getBoolean("Weapon") == false) {
				return;
			}
			if (nbti.getBoolean("Gun") == false) {
				return;
			}
			Guns gun = Main.getInstance().guns.get(nbti.getString("Weapon Type"));
			// CANCELS EGG HATCHING
			if (gun.getProjectileSet().cancelEggSpawn() == true) {
				e.setHatching(false);
			}
		}
	}
	@EventHandler
	@SuppressWarnings("deprecation")
	public void on(PlayerTeleportEvent e) {
		if (e.getCause() == TeleportCause.ENDER_PEARL) {
			Player p = e.getPlayer();
			NBTItem nbti = new NBTItem(p.getItemInHand());

			if (nbti.getBoolean("Weapon") == false) {
				return;
			}
			if (nbti.getBoolean("Gun") == false) {
				return;
			}
			Guns gun = Main.getInstance().guns.get(nbti.getString("Weapon Type"));
			// CANCEL ENDERPEARL TELEPORT
			if (gun.getProjectileSet().cancelTeleport() == true) {
				e.setCancelled(true);
			}
		}
	}
}
