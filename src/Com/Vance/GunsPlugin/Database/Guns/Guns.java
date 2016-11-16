package Com.Vance.GunsPlugin.Database.Guns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import Com.Vance.GunsPlugin.Main;
import Com.Vance.GunsPlugin.Database.Attachments.Attachments;
import Com.Vance.GunsPlugin.Database.Crafting.Crafting;
import Com.Vance.GunsPlugin.Database.Damage.Damage;
import Com.Vance.GunsPlugin.Database.Projectiles.Projectiles;

public class Guns {
	
	private String name;
	private String ID;
	private String weaponItem;
	private String itemLore;
	private String ammoItem;
	private int weaponCapacity;
	private boolean removeAmmoIndividualy;
	private String projectileSet;
	private boolean automaticReload;
	private boolean rightClicktoShoot;
	private int shootDelay;
	private int reloadDelay;
	private String firearmType;
	private String damageSet;
	private String jamming;
	private boolean scope;
	private int zoomLevel;
	private int heavyWeapon;
	private String shootSound;
	private String reloadSound;
	private String hammerSound;
	private String crafting;
	private String attachment;
	private boolean drop;
	
	public Guns(String name, String ID, String weaponItem, String itemLore, String ammoItem, int ammoCapacity, boolean removeAmmoIndividualy, String projectileSet,
			int shootDelay, int reloadDelay, String firearmType, String damageSet, String jamming, boolean scope, int zoomLevel, int heavyWeapon, String shootSound,
			String reloadSound, String hammerSound, boolean rightClicktoShoot, boolean automaticReload, String crafting, String attachment, boolean drop){
		
		this.name = name;
		this.attachment = attachment;
		this.ID = ID;
		this.weaponItem = weaponItem;
		this.itemLore = itemLore;
		this.ammoItem = ammoItem;
		this.weaponCapacity = ammoCapacity;
		this.removeAmmoIndividualy = removeAmmoIndividualy;
		this.projectileSet = projectileSet;
		this.automaticReload = automaticReload;
		this.rightClicktoShoot = rightClicktoShoot;
		this.shootDelay = shootDelay;
		this.reloadDelay = reloadDelay;
		this.firearmType = firearmType;
		this.damageSet = damageSet;
		this.jamming = jamming;
		this.scope = scope;
		this.zoomLevel = zoomLevel;
		this.heavyWeapon = heavyWeapon;
		this.shootSound = shootSound;
		this.reloadSound = reloadSound;
		this.hammerSound = hammerSound;
		this.crafting = crafting;
		this.drop = drop;
	}
	public String getName(){
		return name;
	}
	public String getID(){
		return ID;	
	}
	public boolean canDrop(){
		return drop;
	}
	@SuppressWarnings("deprecation")
	public ItemStack getWeaponItem(){
		List<String> list = Arrays.asList(weaponItem.replaceAll(" ", "").split(","));
		
		if(list.size() != 4){
			Main.getInstance().clogger.sendMessage("§cTHE §bWEAPON ITEM  PARAMITER§c FOR THE §a" + name + " §cIS MISSING A VARIABLE.§9 [Item ID, Durability, Amount, Unbreakable(true/false)]");
			return null;
		}
		
		ItemStack is = new ItemStack(Material.getMaterial(Integer.parseInt(list.get(0))), Integer.parseInt(list.get(2)), Short.parseShort(list.get(1)));
		ItemMeta ismeta = is.getItemMeta();
		ismeta.setDisplayName(name);
		ismeta.spigot().setUnbreakable(Boolean.parseBoolean(list.get(3)));
		
		ArrayList<String> lore = new ArrayList<String>();
		String[] lorelist = itemLore.split("%");

		for(String s : lorelist){
			lore.add(s);
		}
		ismeta.setLore(lore);
		is.setItemMeta(ismeta);
		
		return is;
		
	}
	@SuppressWarnings("deprecation")
	public ItemStack getAmmoItem(){
		List<String> list = Arrays.asList(ammoItem.replaceAll(" ", "").split(","));
		
		if(list.size() != 2){
			Main.getInstance().clogger.sendMessage("§cTHE §bAMMO ITEM  PARAMITER§c FOR THE §a" + name + " §cIS MISSING A VARIABLE.§9 [Item ID, Durability]");
			return null;
		}
		
		ItemStack is = new ItemStack(Material.getMaterial(Integer.parseInt(list.get(0))), 1, Short.parseShort(list.get(1)));
		return is;
		
	}
	public int getCapacity(){
		return weaponCapacity;
	}
	public boolean removeAmmoIndividualy(){
		return removeAmmoIndividualy;
	}
	public Projectiles getProjectileSet(){
		return Main.getInstance().projectiles.get(projectileSet);
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
	public boolean automaticReload(){
		return automaticReload;
	}
	public boolean rightClickToShoot(){
		return rightClicktoShoot;
	}
	public Damage getDamageSet(){
		return Main.getInstance().damage.get(damageSet);
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
		return Integer.parseInt(list.get(1));
	}
	public boolean scoped(){
		return scope;
	}
	public int getZoomLevel(){
		return zoomLevel;
	}
	public int isHeavy(){
		return heavyWeapon;
	}
	public Attachments getAttachment(){
		List<String> list = Arrays.asList(attachment.replaceAll(" ", "").split(","));
		return Main.getInstance().attachments.get(list.get(1));
	}
	public boolean usesAttachment(){
		List<String> list = Arrays.asList(attachment.replaceAll(" ", "").split(","));
		if(list.get(0).equalsIgnoreCase("TRUE")){
			return true;
		}
		if(list.get(0).equalsIgnoreCase("FALSE")){
			return false;
		}
		Main.getInstance().clogger.sendMessage("§cTHE §bATTACHMENT PARAMITER§c FOR THE §a" + name + " §cIS UNKNOWN. VARIABLE MUST BE §9true§c OR §9false");
		return false;
	}
	public boolean isCrafting(){
		List<String> list = Arrays.asList(crafting.replaceAll(" ", "").split(","));
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
			Main.getInstance().clogger.sendMessage("§cTHE §bSHOOT SOUND  PARAMITER§c FOR THE §a" + name + " §cDOES NOT RECONIZE THE SOUND: §9" + list.get(0));
		}
		
	}
	public void playReloadSound(Player p){
		List<String> list = Arrays.asList(reloadSound.replaceAll(" ", "").split(","));
		try {
			p.playSound(p.getLocation(), Sound.valueOf(list.get(0)), Integer.parseInt(list.get(1)), Integer.parseInt(list.get(2)));
		} catch (Exception e) {
			Main.getInstance().clogger.sendMessage("§cTHE §bRELOAD SOUND  PARAMITER§c FOR THE §a" + name + " §cDOES NOT RECONIZE THE SOUND: §9" + list.get(0));
		}
	}
	public void playHammerSound(Player p){
		List<String> list = Arrays.asList(hammerSound.replaceAll(" ", "").split(","));
		try {
			p.playSound(p.getLocation(), Sound.valueOf(list.get(0)), Integer.parseInt(list.get(1)), Integer.parseInt(list.get(2)));
		} catch (Exception e) {
			Main.getInstance().clogger.sendMessage("§cTHE §bHAMMER SOUND  PARAMITER§c FOR THE §a" + name + " §cDOES NOT RECONIZE THE SOUND: §9" + list.get(0));
		}
	}
	public void getGun(){
		
	}
}
