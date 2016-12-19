package org.cubeville.commons.commands;

import java.lang.NumberFormatException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.util.Vector;

public class CommandParameterList implements CommandParameterType
{
    private List<CommandParameterType> parameters;

    public CommandParameterList(List<CommandParameterType> parameters) {
        this.parameters = parameters;
    }

    public boolean isValid(String value) {
        String[] parts = value.split(";");
        if(parts.length != parameters.size()) return false;;
        for(int i = 0; i < parts.length; i++) {
            if(!parameters.get(i).isValid(parts[i])) return false;
        }
        return true;
    }

    public String getInvalidMessage(String value) {
        return "No valid parameter list.";
    }

    public Object getValue(String value) {
        List<Object> ret = new ArrayList<>();
        String[] parts = value.split(";");
        for(int i = 0; i < parts.length; i++) {
            ret.add(parameters.get(i).getValue(parts[i]));
        }
        return ret;
    }
}
