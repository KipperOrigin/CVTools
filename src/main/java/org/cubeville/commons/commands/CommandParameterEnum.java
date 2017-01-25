package org.cubeville.commons.commands;

import java.util.EnumSet;
import java.util.List;
import java.util.ArrayList;
import java.lang.IllegalArgumentException;

public class CommandParameterEnum implements CommandParameterType
{
    Class<? extends Enum> enumType;

    public CommandParameterEnum(Class<? extends Enum> enumType) {
        this.enumType = enumType;
    }
    
    public boolean isValid(String value) {
        try {
            Enum<?> theOneAndOnly = Enum.valueOf(enumType, value.toUpperCase());
        }
        catch(IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public String getInvalidMessage(String value) {
        return value + " is no valid value!";
    }

    public Object getValue(String value) {
        return Enum.valueOf(enumType, value.toUpperCase());
    }

    
}
