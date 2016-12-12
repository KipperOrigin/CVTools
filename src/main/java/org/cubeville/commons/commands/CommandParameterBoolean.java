package org.cubeville.commons.commands;

public class CommandParameterBoolean implements CommandParameterType
{
	
	String trueString = "true";
	String falseString = "false";
	Boolean stringBoolean = null;
	
	public CommandParameterBoolean(String trueString, String falseString) {
		this.trueString = trueString;
		this.falseString = falseString;
	}
	
	public CommandParameterBoolean() {
	}

	public boolean isValid(String value) {
        if (value.equalsIgnoreCase(trueString) || value.equalsIgnoreCase(falseString))
        	return true;
        else 
        	return false;
    }

    public String getInvalidMessage(String value) {
        return value + " is no valid boolean!";
    }

    public Object getValue(String value) {
        if (value.equalsIgnoreCase(trueString))
        	stringBoolean = true;
        else if (value.equalsIgnoreCase(falseString))
        	stringBoolean = false;
        
        return stringBoolean;
    }
}
