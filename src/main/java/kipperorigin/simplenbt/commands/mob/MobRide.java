package kipperorigin.simplenbt.commands.mob;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import kipperorigin.simplenbt.commands.commandparser.Command;
import kipperorigin.simplenbt.resources.Colorize;
import kipperorigin.simplenbt.resources.MobCommandMap;
import net.minecraft.server.v1_9_R2.PacketPlayOutMount;

public class MobRide extends Command {

	public MobRide() {
		super("mob ride");
		addFlag("reverse");
		addFlag("stack");
		addFlag("unstack");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> textParameters) {
		if (flags.contains("reverse") && !flags.contains("stack") && flags.contains("unstack")) {
			dismountAll(player);
			return;
		}
		
		if (!MobCommandMap.containsKey(player)) {
			player.sendMessage(Colorize.addColor("&cPlease select a &6mob&c!"));
			return;
		} else if (MobCommandMap.getValue(player) == null) {
			player.sendMessage(Colorize.addColor("&cPlease select a &6mob&c!"));
			return;
		}
			
		if (flags.contains("reverse") && !flags.contains("stack") && !flags.contains("unstack")) {
			if (getHighestEntity(player) != MobCommandMap.getValue(player)) {
				stackHighestEntity(player, MobCommandMap.getValue(player));
			}
		} else if (!flags.contains("reverse") && flags.contains("stack") && !flags.contains("unstack")) {
		} else if (!flags.contains("reverse") && !flags.contains("stack") && flags.contains("unstack")) {
			dismountAll(MobCommandMap.getValue(player));
		} else {
			if (getHighestEntity(MobCommandMap.getValue(player)) != player)
				getHighestEntity(MobCommandMap.getValue(player)).setPassenger(player);
		}
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
		vehicle.setPassenger(passenger);
		if (vehicle instanceof Player) {
			Player player = (Player) vehicle;
			PacketPlayOutMount packet = new PacketPlayOutMount(((CraftPlayer)player).getHandle());
			((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
			System.out.println("vehicle is player");

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
