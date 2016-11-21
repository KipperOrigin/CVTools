package org.cubeville.commons;

import kipperorigin.simplenbt.resources.Colorize;

public class CommandParameterColor implements CommandParameterType
{
	public boolean isValid(String value) {
        return Colorize.getColorFromString(value.toUpperCase()) != null;
    }

    public String getInvalidMessage(String value) {
        return value + " is no valid enchantment!";
    }

    public Object getValue(String value) {
        return Colorize.getColorFromString(value.toUpperCase());
    }
}
