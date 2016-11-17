package kipperorigin.simplenbt.resources;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Color;

public class Colorize {

    public static String addColor(String message) {
    	message = "&f" + message;
        return ChatColor.translateAlternateColorCodes('&', message);
    }
    
    public static List<String> addColor(List<String> strings) {
    	List<String> finalStrings = new ArrayList<String>();
    	
    	for (String string: strings) {
    		finalStrings.add(ChatColor.translateAlternateColorCodes('&', string));
    	}
    	
		return finalStrings;
    }
    
    public static Color getColorFromString(String string) {
    	if (string.equalsIgnoreCase("aqua"))
    		return Color.AQUA;
    	else if (string.equalsIgnoreCase("black"))
    		return Color.BLACK;
    	else if (string.equalsIgnoreCase("blue"))
    		return Color.BLUE;
    	else if (string.equalsIgnoreCase("fuschia"))
    		return Color.FUCHSIA;
    	else if (string.equalsIgnoreCase("gray") || string.equalsIgnoreCase("grey"))
    		return Color.GRAY;
    	else if (string.equalsIgnoreCase("green"))
    		return Color.GREEN;
    	else if (string.equalsIgnoreCase("lime"))
    		return Color.LIME;
    	else if (string.equalsIgnoreCase("maroon"))
    		return Color.MAROON;
    	else if (string.equalsIgnoreCase("navy"))
    		return Color.NAVY;
    	else if (string.equalsIgnoreCase("olive"))
    		return Color.OLIVE;
    	else if (string.equalsIgnoreCase("orange"))
    		return Color.ORANGE;
    	else if (string.equalsIgnoreCase("purple"))
    		return Color.PURPLE;
    	else if (string.equalsIgnoreCase("red"))
    		return Color.RED;
    	else if (string.equalsIgnoreCase("silver"))
    		return Color.SILVER;
    	else if (string.equalsIgnoreCase("teal"))
    		return Color.TEAL;
    	else if (string.equalsIgnoreCase("white"))
    		return Color.WHITE;
    	else if (string.equalsIgnoreCase("yellow"))
    		return Color.YELLOW;   	
    	else {
    		return null;
    	}
    }
    
    public static Color getColorFromHex(String string) {
    	//TODO
    	return null;
    }
}
