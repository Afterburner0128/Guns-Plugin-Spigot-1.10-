package Com.Vance.GunsPlugin.Utilliites;

import org.bukkit.Color;

public class Colors {
	
	public static Color getColor(String color){
		if(color.equalsIgnoreCase("BLACK")){
			return Color.BLACK;
		}
		if(color.equalsIgnoreCase("AQUA")){
			return Color.AQUA;
		}
		if(color.equalsIgnoreCase("BLUE")){
			return Color.BLUE;
		}
		if(color.equalsIgnoreCase("FUCHSIA")){
			return Color.FUCHSIA;
		}
		if(color.equalsIgnoreCase("GRAY")){
			return Color.GRAY;
		}
		if(color.equalsIgnoreCase("GREEN")){
			return Color.GREEN;
		}
		if(color.equalsIgnoreCase("LIME")){
			return Color.LIME;
		}
		if(color.equalsIgnoreCase("MAROON")){
			return Color.MAROON;
		}
		if(color.equalsIgnoreCase("NAVY")){
			return Color.NAVY;
		}
		if(color.equalsIgnoreCase("OLIVE")){
			return Color.OLIVE;
		}
		if(color.equalsIgnoreCase("ORANGE")){
			return Color.ORANGE;
		}
		if(color.equalsIgnoreCase("PURPLE")){
			return Color.PURPLE;
		}
		if(color.equalsIgnoreCase("RED")){
			return Color.RED;
		}
		if(color.equalsIgnoreCase("SILVER")){
			return Color.SILVER;
		}
		if(color.equalsIgnoreCase("TEAL")){
			return Color.TEAL;
		}
		if(color.equalsIgnoreCase("WHITE")){
			return Color.WHITE;
		}
		if(color.equalsIgnoreCase("YELLOW")){
			return Color.YELLOW;
		}
		return null;
	}

}
