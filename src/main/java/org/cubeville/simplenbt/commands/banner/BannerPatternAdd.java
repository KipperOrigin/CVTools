package org.cubeville.simplenbt.commands.banner;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.DyeColor;
import org.bukkit.block.banner.PatternType;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterEnum;
import org.cubeville.commons.commands.CommandParameterInteger;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.nbt.BannerItem;

public class BannerPatternAdd extends Command {

    public BannerPatternAdd() {
        super("banner add");
        setPermission("snbt.banner");
        addBaseParameter(new CommandParameterEnum(PatternType.class));
        addBaseParameter(new CommandParameterEnum(DyeColor.class));
        addParameter("set", true, new CommandParameterInteger());
        addParameter("insert", true, new CommandParameterInteger());
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
		
        CommandResponse cr = new CommandResponse();
		
        if (parameters.containsKey("set") && !parameters.containsKey("insert")) {
            banner.setPattern((int) parameters.get("set") - 1, (DyeColor) baseParameters.get(1), (PatternType) baseParameters.get(0));
            cr.addMessage("&aPattern set to layer &6" + parameters.get("set") + " &aon banner!");
        } else if (!parameters.containsKey("set") && parameters.containsKey("insert")) {
            banner.insertPattern((int) parameters.get("insert") - 1, (DyeColor) baseParameters.get(1), (PatternType) baseParameters.get(0));
            cr.addMessage("&aPattern inserted at layer &6" + parameters.get("insert") + " &aon banner!");
        } else if (!parameters.containsKey("set") && !parameters.containsKey("insert")) {
            banner.addPattern((DyeColor) baseParameters.get(1), (PatternType) baseParameters.get(0));
            cr.addMessage("&aPattern added to banner!");
        } else {
            throw new CommandExecutionException("&aCannot insert and set at the same time!");
        }
		
        player.getInventory().setItemInMainHand(banner.asItemStack());
        return cr;
    }
}
