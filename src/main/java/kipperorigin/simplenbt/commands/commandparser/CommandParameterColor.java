package kipperorigin.simplenbt.commands.commandparser;

import kipperorigin.simplenbt.resources.Colorize;

public class CommandParameterColor implements CommandParameterType
{
	private Colorize colorize = new Colorize();
	
    public boolean isValid(String value) {
        return colorize.getColorFromString(value.toUpperCase()) != null;
    }

    public String getInvalidMessage(String value) {
        return value + " is no valid enchantment!";
    }

    public Object getValue(String value) {
        return colorize.getColorFromString(value.toUpperCase());
    }
}
