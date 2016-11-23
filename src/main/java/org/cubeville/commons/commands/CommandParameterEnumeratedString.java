package org.cubeville.commons.commands;

import java.util.HashSet;
import java.util.Set;

public class CommandParameterEnumeratedString implements CommandParameterType
{
    Set<String> values;

    public CommandParameterEnumeratedString(Set<String> values) {
        this.values = values;
    }

    public CommandParameterEnumeratedString(String value1, String value2) {
        values = new HashSet<>();
        values.add(value1);
        values.add(value2);
    }
    
    public boolean isValid(String value) {
        return values.contains(value);
    }

    public String getInvalidMessage(String value) {
        return value + " is no valid parameter.";
    }

    public Object getValue(String value) {
        return value;
    }
}
