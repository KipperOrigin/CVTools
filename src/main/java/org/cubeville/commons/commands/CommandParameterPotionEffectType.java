package org.cubeville.commons.commands;

import org.bukkit.potion.PotionEffectType;

public class CommandParameterPotionEffectType implements CommandParameterType
{
    public boolean isValid(String value) {
        return PotionEffectType.getByName(value) != null;
    }

    public String getInvalidMessage(String value) {
        return value + " is no valid potion effect type!";
    }

    public Object getValue(String value) {
        return PotionEffectType.getByName(value);
    }
}
