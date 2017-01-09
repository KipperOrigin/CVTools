package org.cubeville.commons.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandParameterListEnum implements CommandParameterType
{
    Class<? extends Enum> enumType;
    
    public CommandParameterListEnum(Class<? extends Enum> enumType) {
        this.enumType = enumType;
    }

    public boolean isValid(String value) {
        String[] parts = value.split(";");
        try {
            for(int i = 0; i < parts.length; i++) {
                Enum.valueOf(enumType, parts[i].toUpperCase());
            }
        }
        catch(IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public String getInvalidMessage(String value) {
        return value + " is no valid enum list!";
    }

    public Object getValue(String value) {
        String[] parts = value.split(";");
        List<Enum<?>> ret = new ArrayList<>();
        for(int i = 0; i < parts.length; i++) {
            ret.add(Enum.valueOf(enumType, parts[i].toUpperCase()));
        }
        return ret;
    }
}
