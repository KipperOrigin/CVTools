package org.cubeville.simplenbt.commands.firework;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterEnum;
import org.cubeville.commons.commands.CommandParameterListColor;
import org.cubeville.commons.commands.CommandResponse;

public class FireworkEffectAdd extends Command {

    public FireworkEffectAdd() {                                                                     
        super("firework add");
        setPermission("snbt.firework");
        addBaseParameter(new CommandParameterEnum(FireworkEffect.Type.class));
        addBaseParameter(new CommandParameterListColor());
        addParameter("fades", true, new CommandParameterListColor());
        addFlag("flicker");
        addFlag("trail");
    }

    @SuppressWarnings("unchecked")
    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
        throws CommandExecutionException {
        ItemStack item = player.getInventory().getItemInMainHand();
		
        if (item.getType() != Material.FIREWORK) {
            throw new CommandExecutionException("&cMust be holding a &6firework&c!");
        }
		
        FireworkMeta fireworkMeta = (FireworkMeta) item.getItemMeta();
        FireworkEffect.Builder builder = FireworkEffect.builder();		
        List<Color> colors = (List<Color>) baseParameters.get(1);

				
        if (flags.contains("flicker")) {
            builder.withFlicker();
            builder.flicker(true);
        }
		
        if (flags.contains("trail")) {
            builder.withTrail();
            builder.trail(true);
        }
		
        if (parameters.containsKey("fades")) {
            List<Color> fades = (List<Color>) parameters.get("fades");
            builder.withFade(fades);
        }
		
        builder.with((FireworkEffect.Type) baseParameters.get(0));
        builder.withColor(colors);
        fireworkMeta.addEffect(builder.build());
        item.setItemMeta(fireworkMeta);
		
        return new CommandResponse("&aEffect successfully added to &6firework&6!");
    }

}
