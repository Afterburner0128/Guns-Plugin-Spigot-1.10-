package Com.Vance.GunsPlugin.Utilliites;

import java.util.Random;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.DragonFireball;
import org.bukkit.entity.Egg;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.SmallFireball;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.SpectralArrow;
import org.bukkit.entity.SplashPotion;
import org.bukkit.entity.ThrownExpBottle;
import org.bukkit.entity.TippedArrow;
import org.bukkit.entity.WitherSkull;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import Com.Vance.GunsPlugin.Main;
import Com.Vance.GunsPlugin.Database.Projectiles.Projectiles;

public class ProjectileMethods {
	
	public static void launchProjectile(Projectiles projectile, Player p){
		
		//LAUNCHES THE PROJECTILE
		for (int i = 1; i <= projectile.getProjectileAmount(); i++) {			
			//SETS VELOCITY, AND ACCURACY PARAMITERS
			Vector vector = p.getLocation().getDirection().multiply(projectile.getVelocity());
			Random rand = new Random();
			vector.add(new Vector(rand.nextDouble() - (projectile.projectileSpread()/100),rand.nextDouble() - (projectile.projectileSpread()/100), rand.nextDouble() - (projectile.projectileSpread()/100)));
			if(projectile.getProjectileType().equals("ARROW")){
				Arrow arrow = p.launchProjectile(Arrow.class);
				setParameters(arrow, projectile, p);
				arrow.setVelocity(vector);
				provideParticleTrail(projectile, arrow, p);
				
			}
			if(projectile.getProjectileType().equals("TIPPED_ARROW")){
				TippedArrow arrow = p.launchProjectile(TippedArrow.class);
				arrow.setVelocity(vector);
				provideParticleTrail(projectile, arrow, p);
				setParameters(arrow, projectile, p);
			}
			if(projectile.getProjectileType().equals("SPECTRAL_ARROW")){
				SpectralArrow arrow = p.launchProjectile(SpectralArrow.class);
				arrow.setVelocity(vector);
				provideParticleTrail(projectile, arrow, p);
				setParameters(arrow, projectile, p);
			}
			if(projectile.getProjectileType().equals("EGG")){
				Egg arrow = p.launchProjectile(Egg.class);
				arrow.setVelocity(vector);
				provideParticleTrail(projectile, arrow, p);
				setParameters(arrow, projectile, p);
			}
			if(projectile.getProjectileType().equals("FIREBALL")){
				Fireball arrow = p.launchProjectile(Fireball.class);
				arrow.setVelocity(vector);
				provideParticleTrail(projectile, arrow, p);
				setParameters(arrow, projectile, p);
			}
			if(projectile.getProjectileType().equals("SMALL_FIREBALL")){
				SmallFireball arrow = p.launchProjectile(SmallFireball.class);
				arrow.setVelocity(vector);
				provideParticleTrail(projectile, arrow, p);
				setParameters(arrow, projectile, p);
			}
			if(projectile.getProjectileType().equals("SPLASH_POTION")){
				SplashPotion arrow = p.launchProjectile(SplashPotion.class);
				arrow.setVelocity(vector);
				provideParticleTrail(projectile, arrow, p);
				setParameters(arrow, projectile, p);
			}
			if(projectile.getProjectileType().equals("DRAGON_FIREBALL")){
				DragonFireball arrow = p.launchProjectile(DragonFireball.class);
				arrow.setVelocity(vector);
				provideParticleTrail(projectile, arrow, p);
				setParameters(arrow, projectile, p);
			}
			if(projectile.getProjectileType().equals("ENDERPEARL")){
				EnderPearl arrow = p.launchProjectile(EnderPearl.class);
				arrow.setVelocity(vector);
				provideParticleTrail(projectile, arrow, p);
				setParameters(arrow, projectile, p);
			}
			if(projectile.getProjectileType().equals("WITHER_SKULL")){
				WitherSkull arrow = p.launchProjectile(WitherSkull.class);
				arrow.setVelocity(vector);
				provideParticleTrail(projectile, arrow, p);
				setParameters(arrow, projectile, p);
			}
			if(projectile.getProjectileType().equals("THROWN_EXP_BOTTLE")){
				ThrownExpBottle arrow = p.launchProjectile(ThrownExpBottle.class);
				arrow.setVelocity(vector);
				provideParticleTrail(projectile, arrow, p);
				setParameters(arrow, projectile, p);
			}
			if(projectile.getProjectileType().equals("SNOWBALL")){
				Snowball arrow = p.launchProjectile(Snowball.class);
				arrow.setVelocity(vector);
				provideParticleTrail(projectile, arrow, p);
				setParameters(arrow, projectile, p);
			}
			
		}
		
	}
	public static void provideParticleTrail(Projectiles projectile, Projectile pro, Player p){
		if(projectile.allowParticles() == true){
			new BukkitRunnable() {
				
				@Override
				public void run() {
					if(pro == null){
						this.cancel();
					}
					if(pro.isDead() == true){
						this.cancel();
					}
					if(pro.isOnGround()){
						this.cancel();
					}
					ParticleMethods.spawnParticles(projectile.getParticle(), pro.getLocation());
					
				}
				
			}.runTaskTimer(Main.getInstance(), 0, 1);
		}
	}
	public static void setParameters(Projectile p, Projectiles pro, Player player){
		p.setBounce(pro.projectileBounce());
		if(pro.rideProjectile() == true){
			p.setPassenger(player);
		}
		
	}

}
