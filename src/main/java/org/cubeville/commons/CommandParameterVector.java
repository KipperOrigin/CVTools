package org.cubeville.commons;

import org.bukkit.util.Vector;

public class CommandParameterVector implements CommandParameterType
{
	
    public boolean isValid(String value) {
        return getVector(value) != null;
    }

    public String getInvalidMessage(String value) {
        return value + " is no valid enchantment!";
    }

    public Object getValue(String value) {
        return getVector(value);
    }
    
    public Vector getVector(String value) {
    	Vector vec = null;
    	String[] string = value.split(",");
    	if (string.length == 3) {
    		vec = new Vector(0,0,0);
    		vec.setX(Double.parseDouble(string[0]));
    		vec.setY(Double.parseDouble(string[1]));
    		vec.setZ(Double.parseDouble(string[2]));
    	}
    	return vec;
    }
}

