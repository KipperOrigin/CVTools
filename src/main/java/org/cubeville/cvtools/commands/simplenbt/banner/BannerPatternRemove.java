package org.cubeville.cvtools.commands.simplenbt.banner;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterInteger;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.nbt.BannerItem;

public class BannerPatternRemove extends Command {

    public BannerPatternRemove() {                                                                     
        super("banner remove");
        addBaseParameter(new CommandParameterInteger());
    }

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		BannerItem banner = null;
		
		try {
			banner = new BannerItem(player.getInventory().getItemInMainHand());
		} catch (IllegalArgumentException e) {
			return null;
		}
		
		banner.removePattern((int) baseParameters.get(0));
		player.getInventory().setItemInMainHand(banner.asItemStack());
                return null;
	}
}

