package org.cubeville.commons.commands;

import java.util.Arrays;

public class CommandParameterListString implements CommandParameterType
{
    public CommandParameterListString() {}

    public boolean isValid(String value) {
        return true;
    }

    public String getInvalidMessage(String value) {
        return "";
    }

    public Object getValue(String value) {
        return Arrays.asList(value.split(";"));
    }
}
