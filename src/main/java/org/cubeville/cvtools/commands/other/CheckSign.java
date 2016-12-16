package org.cubeville.cvtools.commands.other;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterInteger;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.utils.BlockGetter;
import org.cubeville.commons.utils.Colorize;

public class CheckSign extends Command {

    public CheckSign() {
        super("checksign");
        addBaseParameter(new CommandParameterString());
        addOptionalBaseParameter(new CommandParameterInteger());
        addFlag("we");
    }

    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
        throws CommandExecutionException {

        CommandResponse ret = new CommandResponse();

        List<Block> signs;
        if(flags.size() > 0) { // WE
            signs = BlockGetter.getBlocksInWESelectionByType(player, Material.SIGN_POST, Material.WALL_SIGN);
        }
        else if(baseParameters.size() == 2) {
            signs = BlockGetter.getBlocksInRadiusByType(player.getLocation(), (int) baseParameters.get(1), Material.SIGN_POST, Material.WALL_SIGN);
        }
        else {
            throw new CommandExecutionException("No radius / we.");
        }
        
        CharSequence cs = (String) baseParameters.get(0);
        int amount = 0;
        for (Block sign: signs) {
            boolean contains = false;
            Sign signState = (Sign) sign.getState();
            String[] lines = signState.getLines();
            String lineCon = "";
            for (String line: lines) {
                if (line.contains(cs)) 
                    contains = true;
                if(lineCon.length() > 0) lineCon += " ";
                lineCon += line;
            }
            if (contains) {
                amount += 1;
                ret.addMessage(sign.getLocation().getBlockX() + "/" + sign.getLocation().getBlockY() + "/" + sign.getLocation().getBlockZ() + "&a: " + lineCon);
            }
        }
        
        ret.setBaseMessage((amount > 0 ? "&a" : "&c") + amount + " sign(s) contain the string &6" + (String) baseParameters.get(0) + (amount > 0 ? "&a:" : "&c."));
        
        return ret;
    }
}
