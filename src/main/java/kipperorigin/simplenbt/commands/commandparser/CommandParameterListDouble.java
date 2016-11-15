package kipperorigin.armamentseffects.commandparser;

import java.lang.NumberFormatException;
import java.util.ArrayList;
import java.util.List;

public class CommandParameterListDouble
{
    int length;

    public CommandParameterListDouble(int length) {
        this.length = length;
    }
    
    public boolean isValid(String value) {
        String[] parts = value.split(";");
        if(parts.length != length) return false;
        try {
            for(int i = 0; i < parts.length; i++) {
                Double.valueOf(parts[i]);
            }
        }
        catch(NumberFormatException e) {
            return false;
        }
        return true;
    }

    public String getInvalidMessage(String value) {
        String[] parts = value.split(";");
        if(parts.length != length) return value + " has not the correct length of " + length;
        return "No valid number.";
    }

    public Object getValue(String value) {
        List<Double> ret = new ArrayList<>();
        String[] parts = value.split(";");
        for(int i = 0; i < parts.length; i++) {
            ret.add(Double.valueOf(parts[i]));
        }
        return ret;
    }
}
