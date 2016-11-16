package Com.Vance.GunsPlugin.Database.Attachments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import Com.Vance.GunsPlugin.Main;
import Com.Vance.GunsPlugin.Database.Crafting.Crafting;
import Com.Vance.GunsPlugin.Database.Damage.Damage;

public class Attachments {
	
	private String name;
	private String ID;
	private String weaponItem;
	private String weaponDescription;
	private String ammoItem;
	private int ammoCapacity;
	private boolean removeIndividually;
	private String projectileSet;
	private boolean melee;
	private boolean ranged;
	private int shootDelay;
	private int reloadDelay;
	private String firearmType;
	private String damage;
	private boolean autoReload;
	private boolean rightclickShoot;
	private boolean leftclickEnable;
	private boolean emitsLight;
	private boolean lazer;
	private String jamming;
	private boolean scope;
	private int zoom;
	private int weight;
	private String crafting;
	private String shootSound;
	private String reloadSound;
	private String clickSound;
	
	public Attachments(String name, String ID, String weaponItem, String weaponDescription, String ammoItem, int ammoCapacity, boolean removeIndividually, String projectileSet, boolean melee, boolean ranged,
			int shootDelay, int reloadDelay, String firearmType, String damage, boolean autoReload, boolean rightclickShoot, boolean leftclickEnable, boolean emitsLight, boolean lazer, String jamming, boolean scope,
			int zoom,  int weight, String crafting, String shootSound, String reloadSound, String clickSound){
		
		this.name = name;
		this.ID = ID;
		this.weaponDescription = weaponDescription;
		this.weaponItem = weaponItem;
		this.ammoItem = ammoItem;
		this.ammoCapacity = ammoCapacity;
		this.removeIndividually = removeIndividually;
		this.projectileSet = projectileSet;
		this.melee = melee;
		this.ranged = ranged;
		this.shootDelay = shootDelay;
		this.reloadDelay = reloadDelay;
		this.firearmType = firearmType;
		this.damage = damage;
		this.autoReload = autoReload;
		this.rightclickShoot = rightclickShoot;
		this.leftclickEnable = leftclickEnable;
		this.emitsLight = emitsLight;
		this.lazer = lazer;
		this.jamming = jamming;
		this.scope = scope;
		this.zoom = zoom;
		this.weight = weight;
		this.crafting = crafting;
		this.shootSound = shootSound;
		this.reloadSound = reloadSound;
		this.clickSound = clickSound;
		
	}
	public String getName(){
		return name;
	}
	public String getID(){
		return ID;
	}
	public int getWeaponItem(){
		List<String> list = Arrays.asList(weaponItem.replaceAll(" ", "").split(","));
		if(list.size() != 4){
			Main.getInstance().clogger.sendMessage("§cTHE §bWEAPON ITEM  PARAMITER§c FOR THE §a" + name + " §cIS MISSING A VARIABLE.§9 [Item ID, Durability, Amount, Unbreakable(true/false)]");
			return 0;
		}
		return Integer.parseInt(list.get(0));
	}
	public int getWeaponData(){
		List<String> list = Arrays.asList(weaponItem.replaceAll(" ", "").split(","));
		if(list.size() != 4){
			Main.getInstance().clogger.sendMessage("§cTHE §bWEAPON ITEM  PARAMITER§c FOR THE §a" + name + " §cIS MISSING A VARIABLE.§9 [Item ID, Durability, Amount, Unbreakable(true/false)]");
			return 0;
		}
		return Integer.parseInt(list.get(1));
	}
	public int getWeaponAmount(){
		List<String> list = Arrays.asList(weaponItem.replaceAll(" ", "").split(","));
		if(list.size() != 4){
			Main.getInstance().clogger.sendMessage("§cTHE §bWEAPON ITEM  PARAMITER§c FOR THE §a" + name + " §cIS MISSING A VARIABLE.§9 [Item ID, Durability, Amount, Unbreakable(true/false)]");
			return 0;
		}
		return Integer.parseInt(list.get(2));
	}
	public boolean getWeaponUnbreakable(){
		List<String> list = Arrays.asList(weaponItem.replaceAll(" ", "").split(","));
		if(list.size() != 4){
			Main.getInstance().clogger.sendMessage("§cTHE §bWEAPON ITEM  PARAMITER§c FOR THE §a" + name + " §cIS MISSING A VARIABLE.§9 [Item ID, Durability, Amount, Unbreakable(true/false)]");
			return false;
		}
		if(list.get(0).equalsIgnoreCase("TRUE")){
			return true;
		}
		if(list.get(0).equalsIgnoreCase("FALSE")){
			return false;
		}
		return false;
	}
	public List<String> getWeaponDescription(){
		ArrayList<String> lore = new ArrayList<String>();
		String[] list = weaponDescription.split("%");

		for(String s : list){
			lore.add(s);
		}
		return lore;
		
	}public int getAmmoItem(){
		List<String> list = Arrays.asList(ammoItem.replaceAll(" ", "").split(","));
		if(list.size() != 3){
			Main.getInstance().clogger.sendMessage("§cTHE §bAMMO ITEM  PARAMITER§c FOR THE §a" + name + " §cIS MISSING A VARIABLE.§9 [Item ID, Durability, Amount]");
		}
		return Integer.parseInt(list.get(0));
	}
	public int getAmmoData(){
		List<String> list = Arrays.asList(ammoItem.replaceAll(" ", "").split(","));
		if(list.size() != 3){
			Main.getInstance().clogger.sendMessage("§cTHE §bAMMO ITEM  PARAMITER§c FOR THE §a" + name + " §cIS MISSING A VARIABLE.§9 [Item ID, Durability, Amount]");
		}
		return Integer.parseInt(list.get(1));
	}
	public int getAmmoAmount(){
		List<String> list = Arrays.asList(ammoItem.replaceAll(" ", "").split(","));
		if(list.size() != 3){
			Main.getInstance().clogger.sendMessage("§cTHE §bAMMO ITEM  PARAMITER§c FOR THE §a" + name + " §cIS MISSING A VARIABLE.§9 [Item ID, Durability, Amount]");
		}
		return Integer.parseInt(list.get(2));
	}
	public int getAmmoCapacity(){
		return ammoCapacity;
	}
	public boolean removeAmmoIndividualy(){
		return removeIndividually;
	}
	public String getProjectileSet(){
		return projectileSet;
	}
	public boolean isMelee(){
		return melee;
	}
	public boolean isRanged(){
		return ranged;
	}
	public int getShootDelay(){
		return shootDelay;
	}
	public int getReloadDelay(){
		return reloadDelay;
	}
	public String getFirearmType(){
		return firearmType;
	}
	public Damage getDamageSet(){
		return Main.getInstance().damage.get(damage);
	}
	public boolean autoReload(){
		return autoReload;
	}
	public boolean rightClickToShoot(){
		return rightclickShoot;
	}
	public boolean leftClickToEnable(){
		return leftclickEnable;
	}
	public boolean emitsLight(){
		return emitsLight;
	}
	public boolean laser(){
		return lazer;
	}
	public boolean canJam(){
		List<String> list = Arrays.asList(jamming.replaceAll(" ", "").split(","));
		if(list.get(0).equalsIgnoreCase("TRUE")){
			return true;
		}
		if(list.get(0).equalsIgnoreCase("FALSE")){
			return false;
		}
		Main.getInstance().clogger.sendMessage("§cTHE §bJAMMING PARAMITER§c FOR THE §a" + name + " §cIS UNKNOWN. VARIABLE MUST BE §9true§c OR §9false");
		return false;
	}
	public int jamRate(){
		List<String> list = Arrays.asList(jamming.replaceAll(" ", "").split(","));
		return Integer.parseInt(list.get(2));
	}
	public boolean isScoped(){
		return scope;
	}
	public int getZoomLevel(){
		return zoom;
	}
	public int getWeight(){
		return weight;
	}
	public boolean isCrafting(){
		List<String> list = Arrays.asList(crafting.replaceAll(" ", "").split(","));
		if(list.size() != 3){
			Main.getInstance().clogger.sendMessage("§cTHE §bAMMO ITEM  PARAMITER§c FOR THE §a" + name + " §cIS MISSING A VARIABLE.§9 [Item ID, Durability, Amount]");
		}
		if(list.get(0).equalsIgnoreCase("TRUE")){
			return true;
		}
		if(list.get(0).equalsIgnoreCase("FALSE")){
			return false;
		}
		Main.getInstance().clogger.sendMessage("§cTHE §bCRAFTING PARAMITER§c FOR THE §a" + name + " §cIS UNKNOWN. VARIABLE MUST BE §9true§c OR §9false");
		return false;
	}
	public Crafting craftingRecipe(){
		List<String> list = Arrays.asList(crafting.replaceAll(" ", "").split(","));
		return Main.getInstance().crafting.get(list.get(1));
	}
	public void playShootSound(Player p){
		List<String> list = Arrays.asList(shootSound.replaceAll(" ", "").split(","));
		try {
			p.playSound(p.getLocation(), Sound.valueOf(list.get(0)), Integer.parseInt(list.get(1)), Integer.parseInt(list.get(2)));
		} catch (Exception e) {
			Main.getInstance().clogger.sendMessage("§cTHE §bSHOOT SOUND  PARAMITER§c FOR THE §a" + name + " §cDOES NOT RECONIZE THE SOUND: §9" + list.get(1));
		}
		
	}
	public void playReloadSound(Player p){
		List<String> list = Arrays.asList(reloadSound.replaceAll(" ", "").split(","));
		try {
			p.playSound(p.getLocation(), Sound.valueOf(list.get(0)), Integer.parseInt(list.get(1)), Integer.parseInt(list.get(2)));
		} catch (Exception e) {
			Main.getInstance().clogger.sendMessage("§cTHE §bRELOAD SOUND  PARAMITER§c FOR THE §a" + name + " §cDOES NOT RECONIZE THE SOUND: §9" + list.get(1));
		}
	}
	public void playHammerSound(Player p){
		List<String> list = Arrays.asList(clickSound.replaceAll(" ", "").split(","));
		try {
			p.playSound(p.getLocation(), Sound.valueOf(list.get(0)), Integer.parseInt(list.get(1)), Integer.parseInt(list.get(2)));
		} catch (Exception e) {
			Main.getInstance().clogger.sendMessage("§cTHE §bHAMMER SOUND  PARAMITER§c FOR THE §a" + name + " §cDOES NOT RECONIZE THE SOUND: §9" + list.get(1));
		}
	}

}
