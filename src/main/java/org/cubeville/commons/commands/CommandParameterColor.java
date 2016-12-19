package org.cubeville.commons.commands;

import org.cubeville.commons.utils.Colorize;

public class CommandParameterColor implements CommandParameterType
{
	public boolean isValid(String value) {
		if (Colorize.getColorFromString(value.toUpperCase()) != null) {
			return true;
		} else {
			return Colorize.getColorFromRGB(value) != null;
		}
    }

    public String getInvalidMessage(String value) {
        return value + " is no valid enchantment!";
    }

    public Object getValue(String value) {
		if (Colorize.getColorFromString(value.toUpperCase()) != null) {
			return Colorize.getColorFromString(value.toUpperCase());
		} else {
			return Colorize.getColorFromRGB(value);
		}
    }
}
