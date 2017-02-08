package org.cubeville.cvtools.commands.blocktools;

import java.util.List;
import java.util.ArrayList;
import java.lang.IllegalArgumentException;

import org.cubeville.commons.commands.CommandParameterType;

public class CommandParameterWeightedMaterialList implements CommandParameterType
{
    public boolean isValid(String value) {
        String[] parts = value.split(";");
        for(int i = 0; i < parts.length; i++) {
            try {
                new WeightedMaterial(parts[i]);
            }
            catch(IllegalArgumentException e) {
                return false;
            }
        }
        return true;
    }

    public String getInvalidMessage(String value) {
        return "No valid material description!";
    }

    public Object getValue(String value) {
        List<WeightedMaterial> ret = new ArrayList<WeightedMaterial>();
        String[] parts = value.split(";");
        for(int i = 0; i < parts.length; i++) {
            ret.add(new WeightedMaterial(parts[i]));
        }
        return ret;
    }
}
