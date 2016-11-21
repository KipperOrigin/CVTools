package kipperorigin.simplenbt.commands.block.sign;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.BlockPosition;

import kipperorigin.simplenbt.commands.CommandMapManager;
import kipperorigin.simplenbt.resources.Colorize;

public class BlockSignEdit extends Command {

	public BlockSignEdit() {
		super("block sign edit");
	}

    private ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
	
	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) {
		Map<String, Block> commandMap = CommandMapManager.getBlockCommandMap();
		
		if (!commandMap.containsKey(player.getName())) {
			player.sendMessage(Colorize.addColor("&cPlease select a &6sign&c!"));
			return;
		} else if (commandMap.get(player.getName()) == null || !(commandMap.get(player.getName()).getState() instanceof Sign)) {
 			player.sendMessage(Colorize.addColor("&cPlease select a &6sign&c!"));
			return;
		}
		
		Block sign = commandMap.get(player.getName());
		
		//Sign blockState = (Sign) sign.getState();
		
		PacketContainer editSignPacket = protocolManager.createPacket(PacketType.Play.Server.OPEN_SIGN_EDITOR);
		
		BlockPosition position = new BlockPosition(sign.getX(), sign.getY(), sign.getZ());
		
		editSignPacket.getBlockPositionModifier().write(0, position);
		
		try {
			ProtocolLibrary.getProtocolManager().sendServerPacket(player, editSignPacket);
		} catch (InvocationTargetException e) {
			throw new RuntimeException("Cannot send packet.", e);
		}
		
		//Figure out what is called for the sign update event 'Player KipperOrigin just tried to change non-editable sign'
	}

}

