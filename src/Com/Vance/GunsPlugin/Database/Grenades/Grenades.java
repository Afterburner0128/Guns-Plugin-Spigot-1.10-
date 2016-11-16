package Com.Vance.GunsPlugin.Database.Grenades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Com.Vance.GunsPlugin.Main;
import Com.Vance.GunsPlugin.Database.Damage.Damage;
import Com.Vance.GunsPlugin.Database.Particles.Particles;

public class Grenades {
	
	private String name;
	private String id;
	private String item;
	private String description;
	private int velocity;
	private int timer;
	private String damage;
	private String particles;
	private boolean removeOnDetonate;
	private boolean pickup;
	
	public Grenades(String name, String id, String item, String description, int velocity, int timer, String damage, String particles, boolean removeOnDetonate, boolean pickup){
		
		this.name = name;
		this.id = id;
		this.item = item;
		this.description = description;
		this.velocity = velocity;
		this.timer = timer;
		this.damage = damage;
		this.particles = particles;
		this.removeOnDetonate = removeOnDetonate;
		this.pickup = pickup;
	}
	public String getName(){
		return name;
	}
	public String getID(){
		return id;
	}
	public int getItem(){
		List<String> list = Arrays.asList(item.replaceAll(" ", "").split(","));
		if(list.size() != 4){
			Main.getInstance().clogger.sendMessage("§cTHE §bWEAPON ITEM  PARAMITER§c FOR THE §a" + name + " §cIS MISSING A VARIABLE.§9 [Item ID, Durability, Amount, Unbreakable(true/false)]");
			return 0;
		}
		return Integer.parseInt(list.get(0));
	}
	public int getItemData(){
		List<String> list = Arrays.asList(item.replaceAll(" ", "").split(","));
		if(list.size() != 4){
			Main.getInstance().clogger.sendMessage("§cTHE §bWEAPON ITEM  PARAMITER§c FOR THE §a" + name + " §cIS MISSING A VARIABLE.§9 [Item ID, Durability, Amount, Unbreakable(true/false)]");
			return 0;
		}
		return Integer.parseInt(list.get(1));
	}
	public int getItemAmount(){
		List<String> list = Arrays.asList(item.replaceAll(" ", "").split(","));
		if(list.size() != 4){
			Main.getInstance().clogger.sendMessage("§cTHE §bWEAPON ITEM  PARAMITER§c FOR THE §a" + name + " §cIS MISSING A VARIABLE.§9 [Item ID, Durability, Amount, Unbreakable(true/false)]");
			return 0;
		}
		return Integer.parseInt(list.get(2));
	}
	public boolean getUnbreakable(){
		List<String> list = Arrays.asList(item.replaceAll(" ", "").split(","));
		if(list.size() != 4){
			Main.getInstance().clogger.sendMessage("§cTHE §bWEAPON ITEM  PARAMITER§c FOR THE §a" + name + " §cIS MISSING A VARIABLE.§9 [Item ID, Durability, Amount, Unbreakable(true/false)]");
			return false;
		}
		return Boolean.parseBoolean(list.get(3));
	}
	public List<String> getItemDescription(){
		ArrayList<String> lore = new ArrayList<String>();
		String[] list = description.split("%");

		for(String s : list){
			lore.add(s);
		}
		return lore;
	}
	public int getVelocity(){
		return velocity;
	}
	public int getTimer(){
		return timer;
	}
	public Damage getDamageSet(){
		return Main.getInstance().damage.get(damage);
	}
	public Particles getParticles(){
		return Main.getInstance().particles.get(particles);
	}
	public boolean removeItemOnDetonate(){
		return removeOnDetonate;
	}
	public boolean canPickUp(){
		return pickup;
	}

}
