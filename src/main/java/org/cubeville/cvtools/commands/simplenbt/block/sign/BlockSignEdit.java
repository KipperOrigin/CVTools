package org.cubeville.cvtools.commands.simplenbt.block.sign;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.utils.Colorize;
import org.cubeville.commons.utils.ObjectUtils;
import org.cubeville.cvtools.commands.CommandMap;
import org.cubeville.cvtools.commands.CommandMapManager;
import org.cubeville.cvtools.commands.CommandMapValueException;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.BlockPosition;

public class BlockSignEdit extends Command {

	public BlockSignEdit() {
		super("block sign edit");
	}

    private ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
	
	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		CommandMap commandMap = CommandMapManager.primaryMap;
		Block block;
		
		try {
			block = ObjectUtils.getObjectAsBlock(commandMap.get(player));
		} catch (RuntimeException e) {
			throw new CommandExecutionException("&cPlease select a sign!");
		}
		
		if (!(block.getState() instanceof Sign)) {
			throw new CommandExecutionException("&cPlease select a sign!");
		}
		
		PacketContainer editSignPacket = protocolManager.createPacket(PacketType.Play.Server.OPEN_SIGN_EDITOR);
		
		BlockPosition position = new BlockPosition(block.getX(), block.getY(), block.getZ());
		
		editSignPacket.getBlockPositionModifier().write(0, position);
		
		try {
			ProtocolLibrary.getProtocolManager().sendServerPacket(player, editSignPacket);
		} catch (InvocationTargetException e) {
			throw new RuntimeException("Cannot send packet.", e);
		}
		
		//Figure out what is called for the sign update event 'Player KipperOrigin just tried to change non-editable sign'
                return null;
	}

}

