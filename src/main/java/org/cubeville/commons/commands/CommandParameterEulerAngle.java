package org.cubeville.commons.commands;

import org.bukkit.util.EulerAngle;

public class CommandParameterEulerAngle implements CommandParameterType
{
	
    public boolean isValid(String value) {
        return getAngle	(value) != null;
    }

    public String getInvalidMessage(String value) {
        return value + " is no valid enchantment!";
    }

    public Object getValue(String value) {
        return getAngle(value);
    }
    
    public EulerAngle getAngle(String value) {
    	EulerAngle angle = null;
    	String[] string = value.split(",");
    	if (string.length == 3) {
    		angle = new EulerAngle(0,0,0);
    		try {
    			angle.setX(Math.toRadians(Double.parseDouble(string[0])));
    			angle.setY(Math.toRadians(Double.parseDouble(string[1])));
    			angle.setZ(Math.toRadians(Double.parseDouble(string[2])));
    		} catch (NumberFormatException e) {
    			angle = null;
    		}
    	}
    	return angle;
    }
}

