package org.cubeville.commons.commands;

import java.lang.NumberFormatException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.util.Vector;

public class CommandParameterListVector implements CommandParameterType
{
    public boolean isValid(String value) {
        String[] parts = value.split(";");
        for(int i = 0; i < parts.length; i++) {
            if(CommandParameterVector.getVector(parts[i]) == null) {
                return false;
            }
        }
        return true;
    }

    public String getInvalidMessage(String value) {
        return "No valid vector list.";
    }

    public Object getValue(String value) {
        List<Vector> ret = new ArrayList<Vector>();
        String[] parts = value.split(";");
        for(int i = 0; i < parts.length; i++) {
            ret.add(CommandParameterVector.getVector(parts[i]));
        }
        return ret;
    }
}
