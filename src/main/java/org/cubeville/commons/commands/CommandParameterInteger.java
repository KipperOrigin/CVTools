package org.cubeville.commons.commands;

import java.lang.NumberFormatException;

public class CommandParameterInteger implements CommandParameterType
{
    boolean positive = false;
	
    public CommandParameterInteger() {
		
    }
	
    public CommandParameterInteger(boolean b) {
        positive = b;
    }
	
    public boolean isValid(String value) {
        try {
            if (positive && Integer.valueOf(value) < 0) {
            	return false;
            } else {
            	return true;
            }
        }
        catch(NumberFormatException e) {
            return false;
        }
    }

    public String getInvalidMessage(String value) {
        return value + " is no valid integer!";
    }

    public Object getValue(String value) {
        return Integer.valueOf(value);
    }
    
}
