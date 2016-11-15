package kipperorigin.armamentseffects.commandparser;

import java.lang.NumberFormatException;

public class CommandParameterDouble implements CommandParameterType
{
    public boolean isValid(String value) {
        try {
            Double.valueOf(value);
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
        return Double.valueOf(value);
    }
}
