package Com.Vance.GunsPlugin.Database.Crafting;

import java.util.List;

public class Crafting {
	
	private String name;
	private String id;
	private List<String> abbrivations;
	private int itemAmount;
	private String row1;
	private String row2;
	private String row3;
	private boolean enabled;
	
	public Crafting(String name, String id, List<String> abbreviations, int itemAmount, String row1, String row2, String row3, boolean enable){
		this.name = name;
		this.id = id;
		this.abbrivations = abbreviations;
		this.itemAmount = itemAmount;
		this.row1 = row1;
		this.row2 = row2;
		this.row3 = row3;
		this.enabled = enable;
	}
	public String getName(){
		return name;
	}
	public String getID(){
		return id;
	}
	public List<String> getItemAbbreviations(){
		return abbrivations;
	}
	public int getItemAmount(){
		return itemAmount;
	}
	public String getRow1(){
		return row1;
	}
	public String getRow2(){
		return row2;
	}
	public String getRow3(){
		return row3;
	}
	public boolean isEnabled(){
		return enabled;
	}

}
