package Com.Vance.GunsPlugin.Utilliites;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import Com.Vance.GunsPlugin.Database.Guns.Guns;

public class PotionMethods {

	public static void addPotionEffects(Guns gun, Entity e) {

		for (Entity en : e.getNearbyEntities(gun.getDamageSet().getEffectsRadius("X"),
				gun.getDamageSet().getEffectsRadius("Y"), gun.getDamageSet().getEffectsRadius("Z"))) {
			LivingEntity living = (LivingEntity) en;
			for (String potion : gun.getDamageSet().getPotionEffects()) {
				ArrayList<String> data = (ArrayList<String>) Arrays.asList(potion.replaceAll(" ", "").split(","));
				living.addPotionEffect(new PotionEffect(PotionEffectType.getByName(data.get(0)),
						Integer.parseInt(data.get(1)), Integer.parseInt(data.get(2))));
			}

		}
	}

}
