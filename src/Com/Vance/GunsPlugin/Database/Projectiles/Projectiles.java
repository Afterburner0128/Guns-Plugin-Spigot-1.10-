package Com.Vance.GunsPlugin.Database.Projectiles;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import Com.Vance.GunsPlugin.Main;
import Com.Vance.GunsPlugin.Database.Particles.Particles;

public class Projectiles {
	
	private String projectileType;
	private String name;
	private int velocity;
	private float spread;
	private int amount;
	private String particle;
	private boolean removeOnArrowHit;
	private boolean cancelTeleport;
	private boolean cancelEggSpawn;
	private String entityHitSound;
	private boolean projectileBounce;
	private boolean rideProjectile;
	
	public Projectiles(String name,String projectileType, int velocity, float spread, int amount, String particle, 
			boolean removeOnArrowHit, boolean cancelTeleport, boolean cancelEggSpawn, String entityHitSound, boolean projectileBounce, boolean rideProjectile){
		
		this.name = name;
		this.projectileType = projectileType;
		this.velocity = velocity;
		this.spread = spread;
		this.amount = amount;
		this.particle = particle;
		this.removeOnArrowHit = removeOnArrowHit;
		this.cancelTeleport = cancelTeleport;
		this.cancelEggSpawn = cancelEggSpawn;
		this.entityHitSound = entityHitSound;
		this.projectileBounce = projectileBounce;
		this.rideProjectile = rideProjectile;
	}
	public String getProjectileType(){
		if(projectileType.equalsIgnoreCase("ARROW")){
			return "ARROW";
		}
		if(projectileType.equalsIgnoreCase("TIPPED_ARROW")){
			return "TIPPED_ARROW";
		}
		if(projectileType.equalsIgnoreCase("SPECTRAL_ARROW")){
			return "SPECTRAL_ARROW";
		}
		if(projectileType.equalsIgnoreCase("EGG")){
			return "EGG";
		}
		if(projectileType.equalsIgnoreCase("FIREBALL")){
			return "FIREBALL";
		}
		if(projectileType.equalsIgnoreCase("SMALL_FIREBALL")){
			return "SMALL_FIREBALL";
		}
		if(projectileType.equalsIgnoreCase("SPLASH_POTION")){
			return "SPLASH_POTION";
		}
		if(projectileType.equalsIgnoreCase("DRAGON_FIREBALL")){
			return "DRAGON_FIREBALL";
		}
		if(projectileType.equalsIgnoreCase("ENDERPEARL")){
			return "ENDERPEARL";
		}
		if(projectileType.equalsIgnoreCase("THROWN_EXP_BOTTLE")){
			return "THROWN_EXP_BOTTLE";
		}
		if(projectileType.equalsIgnoreCase("WITHER_SKULL")){
			return "WITHER_SKULL";
		}
		if(projectileType.equalsIgnoreCase("SNOWBALL")){
			return "SNOWBALL";
		}
		Main.getInstance().clogger.sendMessage("§cTHE §bPROJECTILES PARAMITER§c FOR THE §a" + name + " §cIS UNKNOWN. PLEASE SPECIFY A PROJECTILE TYPE");
		return null;
	} 
	public String getName(){
		return name;
	}
	public int getVelocity(){
		if(velocity <= 0){
			Main.getInstance().clogger.sendMessage("§cTHE §bPROJECTILE VELOCITY PARAMITER§c FOR THE §a" + name + " §cIS UNKNOWN. VALUE CANNOT BE LESS THAN 1");
		}
		return velocity;
	}
	public float projectileSpread(){
		return spread;
	}
	public int getProjectileAmount(){
		return amount;
	}
	public Particles getParticle(){
		List<String> list = Arrays.asList(particle.replaceAll(" ", "").split(","));
		if(!Main.getInstance().particles.containsKey(list.get(1))){
			Main.getInstance().clogger.sendMessage("§cTHE §bPROJECTILE TRAIL PARTICLE PARAMITER§c FOR THE §a" + name + " §cIS UNKNOWN. CHECK NAMING CONVENTIONS");
			return null;
		}
		return Main.getInstance().particles.get(list.get(1));
	}
	public boolean allowParticles(){
		List<String> list = Arrays.asList(particle.replaceAll(" ", "").split(","));
		if(list.get(0).equalsIgnoreCase("TRUE")){
			return true;
		}
		if(list.get(0).equalsIgnoreCase("FALSE")){
			return false;
		}
		Main.getInstance().clogger.sendMessage("§cTHE §PARTICLES PARAMITER§c FOR THE §a" + name + " §cIS UNKNOWN. VARIABLE MUST BE §9true§c OR §9false");
		return false;
	}
	public boolean removeOnArrowHit(){
		return removeOnArrowHit;
	}
	public boolean cancelTeleport(){
		return cancelTeleport;
	}
	public boolean cancelEggSpawn(){
		return cancelEggSpawn;
	}
	public void playHitSound(Player p){
		List<String> list = Arrays.asList(entityHitSound.replaceAll(" ", "").split(","));
		try {
			p.playSound(p.getLocation(), Sound.valueOf(list.get(0)), Integer.parseInt(list.get(1)), Integer.parseInt(list.get(2)));
		} catch (Exception e) {
			Main.getInstance().clogger.sendMessage("§cTHE §ENTITY HIT SOUND  PARAMITER§c FOR THE §a" + name + " §cDOES NOT RECONIZE THE SOUND: §9" + list.get(1));
		}
	}
	public boolean projectileBounce(){
		return projectileBounce;
	}
	public boolean rideProjectile(){
		return rideProjectile;
	}

}
