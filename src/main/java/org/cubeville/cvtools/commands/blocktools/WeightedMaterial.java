package org.cubeville.cvtools.commands.blocktools;

import org.bukkit.Material;

public class WeightedMaterial
{
    Integer percentage = null;
    Integer dataValue = null;
    Material material;
    
    public WeightedMaterial(String description) {
        try {
            int percentSignIndex = description.indexOf('%');
            if(percentSignIndex >= 0) {
                percentage = Integer.valueOf(description.substring(0, percentSignIndex));
                description = description.substring(percentSignIndex + 1);
                if (percentage < 0) throw new IllegalArgumentException("No valid percentage");
            }
            
            int colonIndex = description.indexOf("|");
            if(colonIndex >= 0) {
                dataValue = Integer.valueOf(description.substring(colonIndex + 1));
                material = Material.valueOf(description.substring(0, colonIndex).toUpperCase());
            }
            else {
                material = Material.valueOf(description.toUpperCase());
            }
        }
        catch(NumberFormatException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public Integer getPercentage() {
        return percentage;
    }

    public Integer getDataValue() {
        return dataValue;
    }

    public Material getMaterial() {
        return material;
    }
}
