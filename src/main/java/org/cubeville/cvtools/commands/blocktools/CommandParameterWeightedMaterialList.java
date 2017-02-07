package org.cubeville.cvtools.commands.blocktools;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.EnumSet;
import java.util.List;
import java.util.ArrayList;
import java.lang.IllegalArgumentException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;

import org.cubeville.commons.commands.BaseCommand;
import org.cubeville.commons.commands.CommandParameterEnum;
import org.cubeville.commons.commands.CommandParameterInteger;
import org.cubeville.commons.commands.CommandParameterListEnum;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandParameterType;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.utils.BlockUtils;

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
