package org.cubeville.simplenbt.commands.banner;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.nbt.BannerItem;

public class BannerPatternClear extends Command {

    public BannerPatternClear() {                                                                     
        super("banner clear");
        setPermission("snbt.banner");
    }

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		BannerItem banner = null;
		
		try {
			banner = new BannerItem(player.getInventory().getItemInMainHand());
		} catch (IllegalArgumentException e) {
			throw new CommandExecutionException("&cMust be holding a &6banner&c!");
		}
		
		banner.clearPatterns();
		player.getInventory().setItemInMainHand(banner.asItemStack());
		return new CommandResponse("&aBanner patterns cleared!");
	}
}
