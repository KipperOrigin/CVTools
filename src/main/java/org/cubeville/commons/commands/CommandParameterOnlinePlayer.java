package org.cubeville.commons.commands;

import org.bukkit.Bukkit;

public class CommandParameterOnlinePlayer implements CommandParameterType
{
	public boolean isValid(String value) {
        return Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(value));
    }

    public String getInvalidMessage(String value) {
        return value + " is no valid online player!";
    }

    public Object getValue(String value) {
        return Bukkit.getPlayer(value);
    }
}
