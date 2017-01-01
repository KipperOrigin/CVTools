package org.cubeville.cvtools.commands.other;

// TODO: Add configuration options for search radius,
//       add search options for groups of entities

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Rabbit;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Snowman;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Wolf;

import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;

public class CheckEntities extends Command {

    private int radius;
    private int minCount;
    
    public CheckEntities() {
        super("checkentities");
        addOptionalBaseParameter(new CommandParameterString());
        radius = 32;
        minCount = 100;
    }

    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) throws CommandExecutionException {
        CommandResponse ret = new CommandResponse();
        World world = player.getWorld();

        if(baseParameters.size() == 0) {
            List<Player> players = world.getPlayers();
            for(Player cplayer: players) {
                Collection<Entity> entities = world.getNearbyEntities(cplayer.getLocation(), 32, 256, 32);
                int cnt = 0;
                for(Entity entity: entities) {
                    if(isOfEntityClass(entity)) {
                        cnt++;
                    }
                }
                if(cnt >= 100) {
                    ret.addMessage("&aPlayer &9" + player.getName() + "&a has &9" + cnt + "&a entities nearby.");
                }
            }
            if(ret.getMessages() == null) {
                ret.setBaseMessage("&cNo players near too many animals found.");
            }
            else {
                ret.setBaseMessage("&6--- Searching for players with more than " + minCount + " nearby entities.");
            }
        }

        else {
            String playerName = (String) baseParameters.get(0);
            Player cplayer = Bukkit.getPlayer(playerName);
            if(cplayer == null) throw new CommandExecutionException("Player " + playerName + " not found!");
            if(!cplayer.isOnline()) throw new CommandExecutionException("Player " + playerName + " is not online!");
            Collection<Entity> entities = cplayer.getWorld().getNearbyEntities(cplayer.getLocation(), 32, 256, 32);
            Map<String, Integer> count = new HashMap<>();
            for(Entity entity: entities) {
                if(isOfEntityClass(entity)) {
                    String entityName = entity.getClass().getSimpleName();
                    if(entityName.startsWith("Craft")) entityName = entityName.substring(5);
                    if(!count.containsKey(entityName)) count.put(entityName, 0);
                    count.put(entityName, count.get(entityName) + 1);
                }
            }
            if(count.size() > 0) {
                ret.addMessage("&6--- Counting entities near &9" + playerName + "&6. Search region size: " + radius + " ---");
                List<String> sortedEntities = new ArrayList<String>(count.keySet());
                Collections.sort(sortedEntities);
                for(String entity: sortedEntities) {
                    ret.addMessage("&aNumber of &9" + entity + "&a entities: &9" + count.get(entity));
                }
            }
            else {
                ret.setBaseMessage("&cNo entites found near specified player.");
            }
        }

        return ret;
    }

    private boolean isOfEntityClass(Entity entity) {
        if(entity instanceof Sheep || entity instanceof Chicken ||
           entity instanceof Cow || entity instanceof Rabbit ||
           entity instanceof Pig || entity instanceof Villager ||
           entity instanceof Horse || entity instanceof Wolf ||
           entity instanceof Ocelot || entity instanceof IronGolem ||
           entity instanceof Snowman) {
            return true;
        }
        return false;
    }
        
}
