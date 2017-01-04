package org.cubeville.commons.commands;

import java.lang.NumberFormatException;

public class CommandParameterInteger implements CommandParameterType
{
    boolean positive = false;
    Integer min;
    Integer max;
    
    public CommandParameterInteger() {
    }
	
    public CommandParameterInteger(boolean b) { // For backwards compatibility
        if(b) min = 0;
    }

    public CommandParameterInteger(int min, int max) {
        this.min = min;
        this.max = max;
    }
    
    public boolean isValid(String value) {
        int v;
        try {
            v = Integer.valueOf(value);
        }
        catch(NumberFormatException e) {
            return false;
        }
        if(min != null && v < min) return false;
        if(max != null && v > max) return false;
        return true;
    }

    public String getInvalidMessage(String value) {
        return value + " is no valid integer!";
    }

    public Object getValue(String value) {
        return Integer.valueOf(value);
    }
    
}
