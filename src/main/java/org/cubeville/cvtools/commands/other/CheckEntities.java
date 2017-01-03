package org.cubeville.cvtools.commands.other;

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
import org.cubeville.commons.commands.CommandParameterInteger;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;

public class CheckEntities extends Command {

    private int defaultSize;
    private int defaultMinCount;
    
    public CheckEntities() {
        super("checkentities");
        addOptionalBaseParameter(new CommandParameterString());
        addParameter("r", true, new CommandParameterInteger());
        addParameter("min", true, new CommandParameterInteger());
        defaultSize = 50;
        defaultMinCount = 100;
    }

    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) throws CommandExecutionException {
        CommandResponse ret = new CommandResponse();
        World world = player.getWorld();

        int size = defaultSize;
        if(parameters.get("r") != null) {
            size = (int) parameters.get("r") * 2;
            if(size < 4 || size > 60) throw new CommandExecutionException("Size must be in [2;30].");
        }

        int minCount = defaultMinCount;
        if(parameters.get("min") != null) {
            if(baseParameters.size() == 1) throw new CommandExecutionException("Minimum does work for search with a specific player.");
            minCount = (int) parameters.get("min");
            if(minCount < 50) throw new CommandExecutionException("Minimum must be bigger than 50.");
        }

        if(baseParameters.size() == 0) {
            int pcnt = 0;
            List<Player> players = world.getPlayers();
            for(Player cplayer: players) {
                Collection<Entity> entities = world.getNearbyEntities(cplayer.getLocation(), size, 256, size);
                int cnt = 0;
                for(Entity entity: entities) {
                    if(isOfEntityClass(entity)) cnt++;
                }
                if(cnt >= minCount) {
                    ret.addMessage("&aPlayer &9" + cplayer.getName() + "&a: &9" + cnt + "&a entities at &9" + cplayer.getLocation().getBlockX() + "/" + cplayer.getLocation().getBlockZ() + "&a.");
                    pcnt++;
                    if(pcnt++ >= 10) {
                        ret.addMessage("&c... and more.");
                    }
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
            Collection<Entity> entities = cplayer.getWorld().getNearbyEntities(cplayer.getLocation(), size, 256, size);
            Map<String, Integer> count = new HashMap<>();
            int cnt = 0;
            for(Entity entity: entities) {
                if(isOfEntityClass(entity)) {
                    String entityName = entity.getClass().getSimpleName();
                    if(entityName.startsWith("Craft")) entityName = entityName.substring(5);
                    if(!count.containsKey(entityName)) count.put(entityName, 0);
                    count.put(entityName, count.get(entityName) + 1);
                    cnt++;
                }
            }
            if(count.size() > 0) {
                ret.addMessage("&6--- Counting entities near &9" + playerName + "&6. Search region size: " + size + " ---");
                List<String> sortedEntities = new ArrayList<String>(count.keySet());
                Collections.sort(sortedEntities);
                for(String entity: sortedEntities) {
                    ret.addMessage("&aNumber of &9" + entity + "&a entities: &9" + count.get(entity));
                }
                ret.addMessage("&aTotal: &9" + cnt);
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
