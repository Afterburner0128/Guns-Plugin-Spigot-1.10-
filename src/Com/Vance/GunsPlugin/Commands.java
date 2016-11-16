package Com.Vance.GunsPlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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
		if(cmd.getName().equalsIgnoreCase("Guns")){
			if(sender.hasPermission("gunsplugin.command.guns.use")){
				if(args.length == 0){
					p.sendMessage("§cUsage: /Guns <Give, List> [Weapon Name]");
					return true;
				}
				if(args.length == 1){
					if(args[0].equalsIgnoreCase("List")){
						p.sendMessage("§1§l=========§6§l Guns List §1§l=========");
						for(Guns gun: Main.getInstance().guns.values()){
							p.sendMessage("§r -" + gun.getID());
						}
						p.sendMessage("§1§l=========§6§l Grenades List §1§l=========");
						for(Grenades grenades: Main.getInstance().grenades.values()){
							p.sendMessage("§r -" + grenades.getID());
						}
						p.sendMessage("§1§l=========§6§l Attachments List §1§l=========");
						for(Attachments attachments: Main.getInstance().attachments.values()){
							p.sendMessage("§r -" + attachments.getID());
						}
					}
				}
				if(args.length == 2){
					for(Guns gun: Main.getInstance().guns.values()){
						if(args[1].equalsIgnoreCase(gun.getID())){
			
							p.getInventory().addItem(CreateItems.createGun(gun.getID()));
							p.sendMessage(Config.getConfiguration().getString("Messages.Received Weapon").replaceAll("%GunID%", gun.getID()).replaceAll("%GunName%", gun.getName()));
							if(gun.isHeavy() > 0){
								p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100000, gun.isHeavy()));
							}
							if(gun.isHeavy() < 0){
								p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000, gun.isHeavy() * -1));
							}
						
							return false;
						}
					}
					for(Grenades gun: Main.getInstance().grenades.values()){
						if(args[1].equalsIgnoreCase(gun.getID())){
							
							p.getInventory().addItem(CreateItems.createGun(gun.getID()));
							p.sendMessage(Config.getConfiguration().getString("Messages.Received Weapon").replaceAll("%GunID%", gun.getID()).replaceAll("%GunName%", gun.getName()));
						
							return false;
						}
					}
				}
			}	
		}
		
		return false;
	}

}
