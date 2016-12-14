package org.cubeville.cvtools.commands.simplenbt.firework;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;

public class FireworkEffectAdd extends Command {

    public FireworkEffectAdd() {                                                                     
        super("firework add");
        addFlag("flicker");
        addFlag("trail");
    }

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		FireworkEffect.Builder builder = FireworkEffect.builder();
		
		if (flags.contains("flicker")) {
			builder.withFlicker();
			builder.flicker(true);
		}
		
		if (flags.contains("trail")) {
			builder.withTrail();
			builder.trail(true);
		}
		
		builder.with((FireworkEffect.Type) baseParameters.get(0));
		builder.withColor((Color[]) parameters.get("colors"));
		builder.withFade((Color[]) parameters.get("fades"));
	}

}
