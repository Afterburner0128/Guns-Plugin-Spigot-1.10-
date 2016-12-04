package Com.Vance.GunsPlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import Com.Vance.GunsPlugin.Database.Config;
import Com.Vance.GunsPlugin.Database.Attachments.Attachments;
import Com.Vance.GunsPlugin.Database.Grenades.Grenades;
import Com.Vance.GunsPlugin.Database.Guns.Guns;
import Com.Vance.GunsPlugin.Utilliites.CreateItems;

public class Commands implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)){
			Main.getInstance().clogger.sendMessage("§cCONSOLES CANNOT USE THE COMMANDS");
			return false;
		}
		Player p = (Player) sender;
		if(Config.getConfiguration().getBoolean("Permissions.Require Command Permissions") == true){
			if(!p.hasPermission(Config.getConfiguration().getString("Permissions.Command Permission"))){
				return false;
			}
		}
		if(cmd.getName().equalsIgnoreCase("Guns")){
			if(sender.hasPermission("gunsplugin.command.guns.use")){
				if(args.length ==0){
					p.sendMessage("§1§m========§r§6 Guns Plugin §eBy: Afterburner0128 §1§m========");
					p.sendMessage("§6 /Guns Give [Weapon Name]: §aGives the player a specified weapon.");
					p.sendMessage("§6 /Guns List: §aProvides a list of all the weapons that can be recieved.");
					p.sendMessage("§6 /Guns Ammo [Weapon Name] [Amount]: §aGives the player ammunition for the specified weapon.");
					p.sendMessage("§1§m=============================================");
					return true;
				}
				if(args.length == 1){
					if(args[0].equalsIgnoreCase("Ammo")){
						p.sendMessage("§cUsage: /Guns Ammo [Weapon Name] [Amount]");
						return false;
					}
					if(args[0].equalsIgnoreCase("Give")){
						p.sendMessage("§cUsage: /Guns Give [Weapon Name]");
						return false;
					}
					if(args[0].equalsIgnoreCase("List")){
						p.sendMessage("§1§m=========§6§l Guns List §1§m=========");
						for(Guns gun: Main.getInstance().guns.values()){
							p.sendMessage("§r -" + gun.getID());
							p.sendMessage("");
						}
						p.sendMessage("§1§m=========§6§l Grenades List §1§m=========");
						for(Grenades grenades: Main.getInstance().grenades.values()){
							p.sendMessage("§r -" + grenades.getID());
							p.sendMessage("");
						}
						p.sendMessage("§1§m=========§6§l Attachments List §1§m=========");
						for(Attachments attachments: Main.getInstance().attachments.values()){
							p.sendMessage("§r -" + attachments.getID());
							p.sendMessage("");
						}
					}
				}
				if(args.length == 2){
					if(args[0].equalsIgnoreCase("Ammo")){
						p.sendMessage("§cUsage: /Guns Ammo [Weapon Name] [Amount]");
						return false;
					}
					if(args[0].equalsIgnoreCase("Give")){
						for(Guns gun: Main.getInstance().guns.values()){
							if(args[1].equalsIgnoreCase(gun.getID())){
								p.sendMessage("§aRequested weapon found: §6" + gun.getID());
								p.getInventory().addItem(CreateItems.createGun(gun.getID()));
								p.sendMessage(Config.getConfiguration().getString("Messages.Received Weapon").replaceAll("%WeaponID%", gun.getID()).replaceAll("%WeaponName%", gun.getName()));
								if(gun.isHeavy() > 0){
									p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100000, gun.isHeavy()));
								}
								if(gun.isHeavy() < 0){
									p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000, gun.isHeavy() * -1));
								}
							
								return false;
							}
							else{
								p.sendMessage("§aSearching for weapon, Found: §6" + gun.getID());
							}
				
						}
						p.sendMessage("§cWeapon not found: §6" + args[1] + ". §cSearching grenades...");
						for(Grenades grenade: Main.getInstance().grenades.values()){
							if(args[1].equalsIgnoreCase(grenade.getID())){
								p.sendMessage("§aRequested weapon found: §6" + grenade.getID());
								p.getInventory().addItem(CreateItems.createGrenade(grenade.getID()));
								p.sendMessage(Config.getConfiguration().getString("Messages.Received Weapon").replaceAll("%WeaponID%", grenade.getID()).replaceAll("%WeaponName%", grenade.getName()));
							
								return false;
							}
							else{
								p.sendMessage("§aSearching for weapon, Found: §6" + grenade.getID());
							}
							p.sendMessage("§cWeapon not found: §6" + args[1]);
						}	
					}
	
				}
				if(args.length == 3){
					if(args[0].equalsIgnoreCase("Ammo")){
						for(Guns gun: Main.getInstance().guns.values()){
							if(args[1].equalsIgnoreCase(gun.getID())){
								p.sendMessage("§aRequested weapon ammo found: §6" + gun.getID());
								if(gun.removeAmmoIndividualy() == true){
									ItemStack is = new ItemStack(gun.getAmmoItem().getType(), Integer.parseInt(args[2]), gun.getAmmoItem().getDurability());
									p.getInventory().addItem(is);
								}
								if(gun.removeAmmoIndividualy() == false){
									p.getInventory().addItem(CreateItems.createAmmo(gun, gun.getCapacity(), Integer.parseInt(args[2])));
								}
								
							
								return false;
							}
							else{
								p.sendMessage("§aSearching for weapon ammo, Found: §6" + gun.getID());
							}
							p.sendMessage("§cAmmunition not found: §6" + args[1]);
						}
					}
				}
			}	
		}
		
		return false;
	}

}
