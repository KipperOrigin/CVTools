package kipperorigin.simplenbt.commands.commandparser;

import java.lang.NumberFormatException;

public class CommandParameterShort implements CommandParameterType
{
    public boolean isValid(String value) {
        try {
            Short.valueOf(value);
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
        return Short.valueOf(value);
    }
}
