package Com.Vance.GunsPlugin.Utilliites;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Effect;
import org.bukkit.Location;

import Com.Vance.GunsPlugin.Database.Particles.Particles;

public class ParticleMethods {
	
	public static void spawnParticles(Particles p, Location loc){
		for (int i = 0; i < p.getParticles().size(); i++) {
			List<String> s = Arrays.asList(p.getParticles().get(i).replaceAll(" ", "").split(","));
				loc.getWorld().spigot().playEffect(loc, Effect.valueOf(s.get(0)), p.getBlockID(), p.getBlockData(), p.getOffsetX(i), p.getOffsetY(i), p.getOffsetZ(i), p.getParticlesSpeed(i), p.getParticlesCount(i), 50);
			
		}
	}

}
