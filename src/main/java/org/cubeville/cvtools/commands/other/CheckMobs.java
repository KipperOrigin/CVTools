package org.cubeville.cvtools.commands.other;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Rabbit;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Wolf;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.util.Vector;

import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterInteger;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.utils.BlockUtils;
import org.cubeville.commons.utils.Colorize;

public class CheckMobs extends Command {

    public CheckMobs() {
        super("checkmobs");
    }

    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) {
        CommandResponse ret = new CommandResponse();
        World world = player.getWorld();
        List<Player> players = world.getPlayers();
        for(Player cplayer: players) {
            Collection<Entity> entities = world.getNearbyEntities(cplayer.getLocation(), 32, 256, 32);
            int cnt = 0;
            for(Entity entity: entities) {
                if(entity instanceof Sheep || entity instanceof Chicken ||
                   entity instanceof Cow || entity instanceof Rabbit ||
                   entity instanceof Pig ||
                   entity instanceof Horse || entity instanceof Wolf ||
                   entity instanceof Ocelot) {
                    cnt++;
                }
            }
            if(cnt >= 100) {
                ret.addMessage("&aPlayer &9" + player.getName() + "&a has &9" + cnt + "&a animals nearby.");
            }
        }
        if(ret.getMessages() == null) ret.setBaseMessage("&cNo players near too many animals found.");
        return ret;
    }

}
