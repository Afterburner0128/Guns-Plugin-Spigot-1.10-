package Com.Vance.GunsPlugin.Database.Particles;

import java.util.Arrays;
import java.util.List;

public class Particles {
	
	private String name;
	private int blockID;
	private List<String> particles;
	private int blockData;
	
	public Particles(String name, List<String> particles, int blockID, int blockData){
		this.name = name;
		this.particles = particles;
		this.blockID = blockID;
		this.blockData = blockData;
	}
	public String getName(){
		return name;
	}
	public int getBlockID(){
		return blockID;
	}
	public List<String> getParticles(){
		return particles;
	}
	public int getBlockData(){
		return blockData;
	}
	public int getOffsetX(int value){
		List<String> s = Arrays.asList(particles.get(value).replaceAll(" ", "").split(","));
		return Integer.parseInt(s.get(1));
	}
	public int getOffsetY(int value){
		List<String> s = Arrays.asList(particles.get(value).replaceAll(" ", "").split(","));
		return Integer.parseInt(s.get(2));
	}
	public int getOffsetZ(int value){
		List<String> s = Arrays.asList(particles.get(value).replaceAll(" ", "").split(","));
		return Integer.parseInt(s.get(3));
	}
	public int getParticlesSpeed(int value){
		List<String> s = Arrays.asList(particles.get(value).replaceAll(" ", "").split(","));
		return Integer.parseInt(s.get(4));
	}
	public int getParticlesCount(int value){
		List<String> s = Arrays.asList(particles.get(value).replaceAll(" ", "").split(","));
		return Integer.parseInt(s.get(5));
	}

}
