package org.cubeville.commons.commands;

public class CommandParameterString implements CommandParameterType
{
    public boolean isValid(String value) {
        return true;
    }

    public String getInvalidMessage(String value) {
        return "";
    }

    public Object getValue(String value) {
        return value;
    }
}
