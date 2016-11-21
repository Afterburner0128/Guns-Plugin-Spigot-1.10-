package Com.Vance.GunsPlugin.Database.Damage;

import java.util.Arrays;
import java.util.List;

import Com.Vance.GunsPlugin.Main;
import Com.Vance.GunsPlugin.Database.Particles.Particles;

public class Damage {
	
	private String name;
	private String Id;
	private float damage;
	private String repeatEffects;
	private String delayEffects;
	private String effectRedius;
	private String experiance;
	private boolean potionEffects;
	private List<String> potions;
	private boolean particleEffects;
	private String particles;
	private boolean explosive;
	private int explosiveSize;
	private boolean incendiary;
	private boolean blockDamage;
	private String fireworks;
	private List<String> fireworkColors;
	private boolean setBlock;
	private String blockType;
	private String removeBlocks;
	private boolean lightning;
	
	public Damage(String name, String ID, float damage, String repeatEffects, String delayEffects, String effectRadius, String experiance, boolean potionEffects,
			List<String> potions, boolean particleEffects, String particles, boolean explosive, int explosiveSize, boolean incendiary, boolean blockDamage,
			String fireworks, List<String> fireworkColors, boolean setBlock, String blockType, String removeBlocks, boolean lightning){
	
		this.name = name;
		this.Id = ID;
		this.damage = damage;
		this.repeatEffects = repeatEffects;
		this.delayEffects = delayEffects;
		this.effectRedius = effectRadius;
		this.experiance = experiance;
		this.potionEffects = potionEffects;
		this.potions = potions;
		this.particleEffects = particleEffects;
		this.particles = particles;
		this.explosive = explosive;
		this.explosiveSize = explosiveSize;
		this.incendiary = incendiary;
		this.blockDamage = blockDamage;
		this.fireworks = fireworks;
		this.fireworkColors = fireworkColors;
		this.setBlock = setBlock;
		this.blockType = blockType;
		this.removeBlocks = removeBlocks;
		this.lightning = lightning;
	}
	public String getName(){
		return name;
	}
	public String getID(){
		return Id;
	}
	public float getDamage(){
		return damage;
	}
	public boolean enableDelayEffects(){
		List<String> list = Arrays.asList(delayEffects.replaceAll(" ", "").split(","));
		if(list.size() < 2){
			Main.getInstance().clogger.sendMessage("§cTHE §bDELAY TIMER PARAMITER§c FOR THE §a" + name + " §cIS MISSING A VARIABLE.§9 [Enable(true/false), Time(Ticks)]");
			return false;
		}
		if(list.get(0).equalsIgnoreCase("TRUE")){
			return true;
		}
		if(list.get(0).equalsIgnoreCase("FALSE")){
			return false;
		}
		Main.getInstance().clogger.sendMessage("§cTHE §EFFECTS DELAY PARAMITER§c FOR THE §a" + name + " §cIS UNKNOWN. VARIABLE MUST BE §9true§c OR §9false");
		return false;
	}
	public int getDelayTimer(){
		List<String> list = Arrays.asList(delayEffects.replaceAll(" ", "").split(","));
		if(list.size() < 2){
			Main.getInstance().clogger.sendMessage("§cTHE §bDELAY TIMER PARAMITER§c FOR THE §a" + name + " §cIS MISSING A VARIABLE.§9 [Enable(true/false), Time(Ticks)]");
			return 0;
		}
		return Integer.parseInt(list.get(1));
	}
	public boolean enableRepeatEffects(){
		List<String> list = Arrays.asList(repeatEffects.replaceAll(" ", "").split(","));
		if(list.size() < 3){
			Main.getInstance().clogger.sendMessage("§cTHE §bDELAY TIMER PARAMITER§c FOR THE §a" + name + " §cIS MISSING A VARIABLE.§9 [Enable(true/false), Delay(Ticks) ,Time(Ticks)]");
			return false;
		}
		if(list.get(0).equalsIgnoreCase("TRUE")){
			return true;
		}
		if(list.get(0).equalsIgnoreCase("FALSE")){
			return false;
		}
		Main.getInstance().clogger.sendMessage("§cTHE §REPEAT EFFECTS PARAMITER§c FOR THE §a" + name + " §cIS UNKNOWN. VARIABLE MUST BE §9true§c OR §9false");
		return false;
	}
	public int getRepeatTimer(){
		List<String> list = Arrays.asList(repeatEffects.replaceAll(" ", "").split(","));
		if(list.size() < 3){
			Main.getInstance().clogger.sendMessage("§cTHE §bDELAY TIMER PARAMITER§c FOR THE §a" + name + " §cIS MISSING A VARIABLE.§9 [Enable(true/false), Delay(Ticks) ,Time(Ticks)]");
			return 0;
		}
		return Integer.parseInt(list.get(2));
	}
	public int getRepeatDelay(){
		List<String> list = Arrays.asList(repeatEffects.replaceAll(" ", "").split(","));
		if(list.size() < 3){
			Main.getInstance().clogger.sendMessage("§cTHE §bDELAY TIMER PARAMITER§c FOR THE §a" + name + " §cIS MISSING A VARIABLE.§9 [Enable(true/false), Delay(Ticks) ,Time(Ticks)]");
			return 0;
		}
		return Integer.parseInt(list.get(1));
	}
	public boolean useEffectsRadius(){
		List<String> list = Arrays.asList(effectRedius.replaceAll(" ", "").split(","));
		return Boolean.valueOf(list.get(0));
	}
	public int getEffectsRadius(){
		List<String> list = Arrays.asList(effectRedius.replaceAll(" ", "").split(","));
		return Integer.parseInt(list.get(1));
	}
	public boolean addsExperiance(){
		List<String> list = Arrays.asList(experiance.replaceAll(" ", "").split(","));
		if(list.get(0).equalsIgnoreCase("TRUE")){
			return true;
		}
		if(list.get(0).equalsIgnoreCase("FALSE")){
			return false;
		}
		return false;
	}
	public int addExperiance(){
		List<String> list = Arrays.asList(experiance.replaceAll(" ", "").split(","));
		return Integer.parseInt(list.get(1));
	}
	public boolean allowParticles(){
		return particleEffects;
	}
	public Particles getParticleEffects(){
		try {
			return Main.getInstance().particles.get(particles);
		} catch (Exception e) {
			Main.getInstance().clogger.sendMessage("§cTHE §PARTICLES EFFECTS PARAMITER§c FOR THE §a" + name + " §cIS ERRORED. UNKNOWN PARTICLE SET");
		}
		return null;
	}
	public boolean allowPotions(){
		return potionEffects;
	}
	public List<String> getPotionEffects(){
		return potions;
	}
	public boolean allowSetBlock(){
		return setBlock;
	}
	public int getBlockType(){
		List<String> list = Arrays.asList(blockType.replaceAll(" ", "").split(","));
		if(!(list.size() == 2)){
			Main.getInstance().clogger.sendMessage("§cTHE §bSET BLOCK PARAMITER§c FOR THE §a" + name + " §cIS MISSING A VARIABLE.§9 [Item ID, Item Data]");
			return 0;
		}
		return Integer.parseInt(list.get(0));
	}
	public int getBlockData(){
		List<String> list = Arrays.asList(blockType.replaceAll(" ", "").split(","));
		if(!(list.size() == 2)){
			Main.getInstance().clogger.sendMessage("§cTHE §bSET BLOCK PARAMITER§c FOR THE §a" + name + " §cIS MISSING A VARIABLE.§9 [Item ID, Item Data]");
			return 0;
		}
		return Integer.parseInt(list.get(1));
	}
	public boolean removeBlock(){
		List<String> list = Arrays.asList(removeBlocks.replaceAll(" ", "").split(","));
		if(list.get(0).equalsIgnoreCase("TRUE")){
			return true;
		}
		if(list.get(0).equalsIgnoreCase("FALSE")){
			return false;
		}
		Main.getInstance().clogger.sendMessage("§cTHE §REMOVE BLOCK PARAMITER§c FOR THE §a" + name + " §cIS UNKNOWN. VARIABLE MUST BE §9true§c OR §9false");
		return false;
	}
	public int removeBlockDelay(){
		List<String> list = Arrays.asList(removeBlocks.replaceAll(" ", "").split(","));
		if(list.size() < 2){
			Main.getInstance().clogger.sendMessage("§cTHE §bREMOVE BLOCK PARAMITER§c FOR THE §a" + name + " §cIS MISSING A VARIABLE.§9 [Enable(true, false), Item Data]");
			return 0;
		}
		return Integer.parseInt(list.get(1));
	}
	public boolean enabledFireworks(){
		List<String> list = Arrays.asList(fireworks.replaceAll(" ", "").split(","));
		if(list.get(0).equalsIgnoreCase("TRUE")){
			return true;
		}
		if(list.get(0).equalsIgnoreCase("FALSE")){
			return false;
		}
		Main.getInstance().clogger.sendMessage("§cTHE §FIREWORK ENABLE PARAMITER§c FOR THE §a" + name + " §cIS UNKNOWN. VARIABLE MUST BE §9true§c OR §9false");
		return false;
	}
	public boolean fireworkTrail(){
		List<String> list = Arrays.asList(fireworks.replaceAll(" ", "").split(","));
		if(list.get(2).equalsIgnoreCase("TRUE")){
			return true;
		}
		if(list.get(2).equalsIgnoreCase("FALSE")){
			return false;
		}
		Main.getInstance().clogger.sendMessage("§cTHE §FIREWORK TRAIL PARAMITER§c FOR THE §a" + name + " §cIS UNKNOWN. VARIABLE MUST BE §9true§c OR §9false");
		return false;
	}
	public boolean detonateFireworkOnSpawn(){
		List<String> list = Arrays.asList(fireworks.replaceAll(" ", "").split(","));
		if(list.get(3).equalsIgnoreCase("TRUE")){
			return true;
		}
		if(list.get(3).equalsIgnoreCase("FALSE")){
			return false;
		}
		Main.getInstance().clogger.sendMessage("§cTHE §FIREWORK DETONATE PARAMITER§c FOR THE §a" + name + " §cIS UNKNOWN. VARIABLE MUST BE §9true§c OR §9false");
		return false;
	}
	public boolean fireworkOnHit(){
		List<String> list = Arrays.asList(fireworks.replaceAll(" ", "").split(","));
		if(list.get(4).equalsIgnoreCase("TRUE")){
			return true;
		}
		if(list.get(4).equalsIgnoreCase("FALSE")){
			return false;
		}
		Main.getInstance().clogger.sendMessage("§cTHE §FIREWORK TRAIL PARAMITER§c FOR THE §a" + name + " §cIS UNKNOWN. VARIABLE MUST BE §9true§c OR §9false");
		return false;
	}
	public List<String> fireworkColors(){
		return fireworkColors;
	}
	public String getFireworkType(){
		List<String> list = Arrays.asList(fireworks.replaceAll(" ", "").split(","));
		return list.get(1);
	}
	public boolean enableLightning(){
		return lightning;
	}
	public boolean explosive(){
		return explosive;
	}
	public boolean incendiary(){
		return incendiary;
	}
	public boolean explosionBlockDamage(){
		return blockDamage;
	}
	public int explosionSize(){
		return explosiveSize;
	}

}
