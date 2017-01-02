package org.cubeville.cvtools.commands.simplenbt.entity;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.commands.CommandMap;
import org.cubeville.cvtools.commands.CommandMapManager;

import net.minecraft.server.v1_9_R2.PacketPlayOutMount;

public class EntityRide extends Command {

    public EntityRide() {
        super("entity ride");
        addFlag("reverse");
        addFlag("stack");
        addFlag("unstack");
    }

    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
        throws CommandExecutionException {
        if (flags.contains("reverse") && !flags.contains("stack") && flags.contains("unstack")) {
            dismountAll(player);
            return null;
        }
		
        CommandMap commandMap = CommandMapManager.primaryMap;
        if (!commandMap.contains(player)) {
            throw new CommandExecutionException("&cPlease select an &6entity&c!");
        } else if (!(commandMap.get(player) instanceof Entity)) {
            throw new CommandExecutionException("&cPlease select an &6entity&c!");
        }
		
        Entity entity = (Entity) commandMap.get(player);
			
        if (flags.contains("reverse") && !flags.contains("stack") && !flags.contains("unstack")) {
            if (getHighestEntity(player) != entity) {
                stackHighestEntity(player, entity);
            }
        } else if (!flags.contains("reverse") && flags.contains("stack") && !flags.contains("unstack")) {
        } else if (!flags.contains("reverse") && !flags.contains("stack") && flags.contains("unstack")) {
            dismountAll(entity);
        } else {
            if (getHighestEntity(entity) != player)
                getHighestEntity(entity).setPassenger(player);
        }
		
        return new CommandResponse("&aEntity ride successfully executed!");
    }
	
    public static Entity getHighestEntity(Entity e) {
        boolean higher = true;
		
        while (higher)
            if (!e.isEmpty())
                e = e.getPassenger();
            else
                higher = false;
		
        return e;
    }
	
    public static void stackHighestEntity(Entity vehicle, Entity passenger) {
        vehicle = getHighestEntity(vehicle);
        if (vehicle == passenger)
            return;
        vehicle.setPassenger(passenger);
        if (vehicle instanceof Player) {
            Player player = (Player) vehicle;
            PacketPlayOutMount packet = new PacketPlayOutMount(((CraftPlayer)player).getHandle());
            ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
        }
    }
	
    public static void dismountAll(Entity e) {
        boolean more = true;
	
        while (more)
            if (!e.isEmpty()) {
                Entity ex = e.getPassenger();
                e.eject();
                if (e instanceof Player) {
                    PacketPlayOutMount packet = new PacketPlayOutMount(((CraftPlayer)e).getHandle());
                    ((CraftPlayer)e).getHandle().playerConnection.sendPacket(packet);
                }
                e = ex;
            } else
                more = false;
    }
		
}
