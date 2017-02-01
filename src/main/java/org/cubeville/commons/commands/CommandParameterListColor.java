package org.cubeville.commons.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Color;
import org.cubeville.commons.utils.ColorUtils;

public class CommandParameterListColor implements CommandParameterType
{
    
    public boolean isValid(String value) {
        String[] parts = value.split(";");
        try {
            for(int i = 0; i < parts.length; i++) {
            	ColorUtils.getColorFromString(parts[i].toUpperCase());
            }
        }
        catch(IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public String getInvalidMessage(String value) {
        return "No valid color.";
    }

    public Object getValue(String value) {
        List<Color> ret = new ArrayList<>();
        String[] parts = value.split(";");
        for(int i = 0; i < parts.length; i++) {
            ret.add(ColorUtils.getColorFromString(parts[i].toUpperCase()));
        }
        return ret;
    }
}
