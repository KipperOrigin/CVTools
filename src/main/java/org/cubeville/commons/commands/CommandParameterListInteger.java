package org.cubeville.commons.commands;

import java.lang.NumberFormatException;
import java.util.ArrayList;
import java.util.List;

public class CommandParameterListInteger implements CommandParameterType
{
    int length;

    public CommandParameterListInteger(int length) {
        this.length = length;
    }
    
    public boolean isValid(String value) {
        String[] parts = value.split(";");
        if(parts.length != length) return false;
        try {
            for(int i = 0; i < parts.length; i++) {
                Integer.valueOf(parts[i]);
            }
        }
        catch(NumberFormatException e) {
            return false;
        }
        return true;
    }

    public String getInvalidMessage(String value) {
        String[] parts = value.split(";");
        if(parts.length != length) return value + " has not the correct length of " + length;
        return "No valid number.";
    }

    public Object getValue(String value) {
        List<Integer> ret = new ArrayList<>();
        String[] parts = value.split(";");
        for(int i = 0; i < parts.length; i++) {
            ret.add(Integer.valueOf(parts[i]));
        }
        return ret;
    }
}
