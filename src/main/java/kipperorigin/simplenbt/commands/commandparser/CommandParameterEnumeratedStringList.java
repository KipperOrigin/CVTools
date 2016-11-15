package kipperorigin.armamentseffects.commandparser;

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class CommandParameterEnumeratedStringList implements CommandParameterType
{
    Set<String> values;

    public CommandParameterEnumeratedStringList(Set<String> values) {
        this.values = values;
    }

    public boolean isValid(String value) {
        String[] elements = value.split(",");
        for(int i = 0; i < elements.length; i++) {
            if(!values.contains(elements[i])) {
                return false;
            }
        }
        return true;
    }

    public String getInvalidMessage(String value) {
        return "";
    }

    public Object getValue(String value) {
        return new HashSet<>(Arrays.asList(value.split(",")));
    }
}
