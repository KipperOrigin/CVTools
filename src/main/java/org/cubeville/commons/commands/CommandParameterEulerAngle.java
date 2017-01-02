package org.cubeville.commons.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.util.EulerAngle;

public class CommandParameterEulerAngle implements CommandParameterType
{
	
    public boolean isValid(String value) {
        return getAngle	(value) != null;
    }

    public String getInvalidMessage(String value) {
        return value + " is no valid angle!";
    }

    public Object getValue(String value) {
        return getAngle(value);
    }
    
    public EulerAngle getAngle(String value) {
    	String[] string = value.split(",");
    	List<Double> angles = new ArrayList<>();
    	
    	if (string.length == 3) {
    		try {
    			angles.add(Math.toRadians(Double.parseDouble(string[0])));
    			angles.add(Math.toRadians(Double.parseDouble(string[1])));
    			angles.add(Math.toRadians(Double.parseDouble(string[2])));
    		} catch (NumberFormatException e) {
    			return null;
    		}
    	} else {
    		return null;
    	}
    	
    	return new EulerAngle(angles.get(0), angles.get(1), angles.get(2));
    }
}

