package kipperorigin.simplenbt.commands.commandparser;

import org.bukkit.potion.PotionEffectType;

public class CommandParameterPotionEffectType implements CommandParameterType
{
    public boolean isValid(String value) {
	return (getPotionEffectType(value) != null);
    }

    public String getInvalidMessage(String value) {
	return value + " is no valid potion effect type!";
    }

    public Object getValue(String value) {
	return getPotionEffectType(value);
    }

    PotionEffectType getPotionEffectType(String name) {
 	PotionEffectType[] effectList = PotionEffectType.values();
	for(int i = 0; i < effectList.length; i++) {
	    if(effectList[i] != null) {
		if(effectList[i].getName().equals(name)) {
		    return effectList[i];
		}
	    }
	}
	return null;
    }
}
