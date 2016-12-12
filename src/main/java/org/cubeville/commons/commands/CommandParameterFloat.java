package org.cubeville.commons.commands;

import java.lang.NumberFormatException;

public class CommandParameterFloat implements CommandParameterType
{
    public boolean isValid(String value) {
        try {
            Float.valueOf(value);
            return true;
        }
        catch(NumberFormatException e) {
            return false;
        }
    }

    public String getInvalidMessage(String value) {
        return value + " is no valid number!";
    }

    public Object getValue(String value) {
        return Float.valueOf(value);
    }
}
