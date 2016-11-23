package org.cubeville.cvtools.events;

import java.util.Collections;
import java.util.Map;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.cubeville.cvtools.commands.CommandMapManager;

public class EventBlockRemoval implements Listener {

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		removeBlock(event.getBlock());
	}
	
	@EventHandler
	public void onBlockBurn(BlockBurnEvent event) {
		removeBlock(event.getBlock());
	}
	
	@EventHandler
	public void onBlockExplode(BlockExplodeEvent event) {
		removeBlock(event.getBlock());
	}

	@EventHandler
	public void onBlockFade(BlockFadeEvent event) {
		removeBlock(event.getBlock());
	}
	
	@EventHandler
	public void onLeavesDecay(LeavesDecayEvent event) {
		removeBlock(event.getBlock());
	}
	
	public void removeBlock(Block block) {
		Map<String, Block> commandMap = CommandMapManager.getBlockCommandMap();
		if (commandMap.containsValue(block)) {
			commandMap.values().removeAll(Collections.singleton(block));
		}
	}
	
}
