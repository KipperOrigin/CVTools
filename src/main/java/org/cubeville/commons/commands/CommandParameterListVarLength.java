package org.cubeville.commons.commands;

import java.util.ArrayList;
import java.util.List;

public class CommandParameterListVarLength implements CommandParameterType
{
    private CommandParameterType type;

    public CommandParameterListVarLength(CommandParameterType type) {
        this.type = type;
    }

    public boolean isValid(String value) {
        String[] parts = value.split(";");
        for(int i = 0; i < parts.length; i++) {
            if(!type.isValid(parts[i])) return false;
        }
        return true;
    }

    public String getInvalidMessage(String value) {
        return "No valid parameter list!";
    }

    public Object getValue(String value) {
        List<Object> ret = new ArrayList<>();
        String[] parts = value.split(";");
        for(int i = 0; i < parts.length; i++) {
            ret.add(type.getValue(parts[i]));
        }
        return ret;
    }
}
