package kipperorigin.simplenbt.commands.commandparser;

import java.lang.NumberFormatException;

public class CommandParameterInteger implements CommandParameterType
{
    public boolean isValid(String value) {
	try {
	    Integer.valueOf(value);
	    return true;
	}
	catch(NumberFormatException e) {
	    return false;
	}
    }

    public String getInvalidMessage(String value) {
	return value + " is no valid integer!";
    }

    public Object getValue(String value) {
	return Integer.valueOf(value);
    }
    
}
