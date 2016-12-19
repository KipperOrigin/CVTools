package org.cubeville.cvtools.events;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.SignChangeEvent;
import org.cubeville.cvtools.commands.CommandMap;
import org.cubeville.cvtools.commands.CommandMapManager;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;

public class EventSignChange {

	@EventHandler
	public void onSignChange(SignChangeEvent event) {
		if (event.isCancelled())	
			System.out.println("Sends change event cancelled");
		System.out.println("Sends change event");
		CommandMap commandMap = CommandMapManager.primaryMap;
		Player player = event.getPlayer();
	    ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
		
		if (commandMap.contains(player)) {
			if (!(commandMap.get(player) instanceof Block)) {
				return;
			}
			Block block = (Block) (commandMap.get(player));
			if (block.getState() instanceof Sign) {
				PacketContainer signUpdate = protocolManager.createPacket(PacketType.Play.Server.OPEN_SIGN_EDITOR);
				Player[] players = (Player[]) Bukkit.getOnlinePlayers().toArray();
				for (Player playerP: players) {
					try {
						protocolManager.sendServerPacket(playerP, signUpdate);
					} catch (InvocationTargetException e) {
						throw new RuntimeException("Could not sent packet " + e);
					}
				}
			}
				
		}
	}
}
