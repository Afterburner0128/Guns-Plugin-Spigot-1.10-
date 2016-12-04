package Com.Vance.GunsPlugin.Utilliites;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;

import Com.Vance.GunsPlugin.Main;
import Com.Vance.GunsPlugin.Database.Guns.Guns;

public class CraftingUtilities {
	
	private static ArrayList<List<String>> list = new ArrayList<List<String>>();
	
	@SuppressWarnings("deprecation")
	public static void addRecpies(){
		for(Guns g : Main.getInstance().guns.values()){
			if(g.isCrafting() == true){
				for (int i = 0; i < g.craftingRecipe().getItemAbbreviations().size(); i++) {
					List<String> s = Arrays.asList(g.craftingRecipe().getItemAbbreviations().get(i).replaceAll(" ", "").split(","));
					list.add(s);
				}
				ShapedRecipe recpie = new ShapedRecipe(CreateItems.createGun(g.getID()));
				recpie.shape(g.craftingRecipe().getRow1(),g.craftingRecipe().getRow2(),g.craftingRecipe().getRow3());
				for (int i = 0; i < list.size(); i++) {
					System.out.println(list.get(i).get(2).charAt(0));
					recpie.setIngredient(list.get(i).get(2).charAt(0), Material.getMaterial(Integer.parseInt(list.get(i).get(0))));
				}
				
				Main.getInstance().getServer().addRecipe(recpie);
			}
		}
	}

}
