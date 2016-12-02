package Com.Vance.GunsPlugin;

import java.util.HashMap;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import Com.Vance.GunsPlugin.Database.Config;
import Com.Vance.GunsPlugin.Database.Attachments.Attachments;
import Com.Vance.GunsPlugin.Database.Attachments.AttachmentsConfiguration;
import Com.Vance.GunsPlugin.Database.Crafting.Crafting;
import Com.Vance.GunsPlugin.Database.Crafting.CraftingConfiguration;
import Com.Vance.GunsPlugin.Database.Damage.Damage;
import Com.Vance.GunsPlugin.Database.Damage.DamageConfiguration;
import Com.Vance.GunsPlugin.Database.Grenades.Grenades;
import Com.Vance.GunsPlugin.Database.Grenades.GrenadesConfiguration;
import Com.Vance.GunsPlugin.Database.Guns.Guns;
import Com.Vance.GunsPlugin.Database.Guns.GunsConfiguration;
import Com.Vance.GunsPlugin.Database.Particles.Particles;
import Com.Vance.GunsPlugin.Database.Particles.ParticlesConfiguration;
import Com.Vance.GunsPlugin.Database.Projectiles.Projectiles;
import Com.Vance.GunsPlugin.Database.Projectiles.ProjectilesConfiguration;
import Com.Vance.GunsPlugin.Listeners.PlayerInteractListener;
import Com.Vance.GunsPlugin.Listeners.PlayerReloadListener;
import Com.Vance.GunsPlugin.Listeners.ProjectileHitListeners;
import Com.Vance.GunsPlugin.Utilliites.CraftingUtilities;

public class Main extends JavaPlugin implements Listener{
	
	static Main plugin;
	public HashMap<String, Guns> guns = new HashMap<String, Guns>();
	public HashMap<String, Projectiles> projectiles = new HashMap<String, Projectiles>();
	public HashMap<String, Particles> particles = new HashMap<String, Particles>();
	public HashMap<String, Damage> damage = new HashMap<String, Damage>();
	public HashMap<String, Crafting> crafting = new HashMap<String, Crafting>();
	public HashMap<String, Grenades> grenades = new HashMap<String, Grenades>();
	public HashMap<String, Attachments> attachments = new HashMap<String, Attachments>();
	
	public ConsoleCommandSender clogger = this.getServer().getConsoleSender();
	
	public void onEnable(){
		plugin = this;
		GunsConfiguration.setupConfiguration();
		GrenadesConfiguration.setupConfiguration();
		
		DamageConfiguration.setupConfiguration();
		ProjectilesConfiguration.setupConfiguration();
		ParticlesConfiguration.setupConfiguration();
		AttachmentsConfiguration.setupConfiguration();
		CraftingConfiguration.setupConfiguration();
		Config.setupConfig();
		CraftingUtilities.addRecpies();
		
		getServer().getPluginManager().registerEvents(new PlayerReloadListener(), this);
		getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
		getServer().getPluginManager().registerEvents(new ProjectileHitListeners(), this);
		
		getCommand("guns").setExecutor(new Commands());
	}
	public static Main getInstance(){
		return plugin;
	}

}
