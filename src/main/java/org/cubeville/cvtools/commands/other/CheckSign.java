package org.cubeville.cvtools.commands.other;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.util.Vector;

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
        addFlag("sel");
        addFlag("tp");
        addParameter("wg", true, new CommandParameterString());
    }

    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
        throws CommandExecutionException {

        if(checkMoreThanOne(flags.contains("we"), parameters.containsKey("wg"), baseParameters.size() == 2)) {
            throw new CommandExecutionException("Only one of radius / we / wg parameters can be applied.");
        }

        CommandResponse ret = new CommandResponse();

        List<Block> signs;
        if(flags.contains("we")) {
            signs = BlockGetter.getBlocksInWESelectionByType(player, Material.SIGN_POST, Material.WALL_SIGN);
        }
        else if(parameters.containsKey("wg")) {
            signs = BlockGetter.getBlocksInWGRegionByType(player, (String) parameters.get("wg"), Material.SIGN_POST, Material.WALL_SIGN);
        }
        else if(baseParameters.size() == 2) {
            if((int) baseParameters.get(1) > 25) throw new CommandExecutionException("Maximum radius is 25.");
            signs = BlockGetter.getBlocksInRadiusByType(player.getLocation(), (int) baseParameters.get(1), Material.SIGN_POST, Material.WALL_SIGN);
        }
        else {
            throw new CommandExecutionException("No radius / we / wg.");
        }

        boolean foundfirst = false;
        
        String cs = ((String) baseParameters.get(0)).toUpperCase();
        int amount = 0;
        for (Block sign: signs) {
            Sign signState = (Sign) sign.getState();
            String[] lines = signState.getLines();
            String lineCon = "";
            for (String line: lines) {
                if(lineCon.length() > 0) lineCon += " ";
                lineCon += Colorize.removeColor(line);
            }
            if (lineCon.toUpperCase().contains(cs)) {
                amount += 1;
                ret.addMessage(sign.getLocation().getBlockX() + "/" + sign.getLocation().getBlockY() + "/" + sign.getLocation().getBlockZ() + "&a: " + lineCon);
                if(!foundfirst) {
                    foundfirst = true;
                    if(flags.contains("sel")) {
                        Vector sl = new Vector(sign.getLocation().getBlockX(), sign.getLocation().getBlockY(), sign.getLocation().getBlockZ());
                        BlockGetter.setWESelection(player, player.getLocation().getWorld(), sl, sl);
                    }
                    if(flags.contains("tp")) { // Go one down if space is available, also one block "back" (on sign's front)
                        Location loc = sign.getLocation();
                        loc.setX(loc.getX() + .5);
                        loc.setZ(loc.getZ() + .5);
                        player.teleport(loc, PlayerTeleportEvent.TeleportCause.PLUGIN);
                    }
                }
            }
        }

        ret.setBaseMessage((amount > 0 ? "&a" : "&c") + amount + " sign(s) contain the string &6" + (String) baseParameters.get(0) + (amount > 0 ? "&a:" : "&c."));

        return ret;
    }

}
