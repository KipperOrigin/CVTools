package org.cubeville.commons.commands;

import java.lang.IllegalArgumentException;
import java.util.UUID;

public class CommandParameterUUID implements CommandParameterType
{
    public boolean isValid(String value) {
        try {
            UUID.fromString(value);
            return true;
        }
        catch(IllegalArgumentException e) {
            return false;
        }
    }

    public String getInvalidMessage(String value) {
        return value + " is not valid UUID.";
    }

    public Object getValue(String Value) {
        return UUID.fromString(Value);
    }
}
