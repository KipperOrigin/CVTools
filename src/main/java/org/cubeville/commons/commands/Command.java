package org.cubeville.commons.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.command.CommandSender;

// TODO: Make textparameters use all remaining arguments to permit spaces
// TODO: Use quotes to include spaces -> Done, but: Make colons inside quotation marks not be considered parameter name separators
// TODO: Aliases for prefixed parameters and command parts?
// TODO: Default values for optional parameters
// TODO: Permissions for commands and for parameters/flags -> Part 1 done, part 2 worth it?

public abstract class Command extends BaseCommand
{
    public Command(String fullCommand) {
        super(fullCommand);
    }
    
    public CommandResponse execute(CommandSender commandSender, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) throws CommandExecutionException {
        if(!(commandSender instanceof Player)) throw new CommandExecutionException("Command can only be used by player!");
        Player player = (Player) commandSender;
        return execute(player, flags, parameters, baseParameters);
    }
    
    public abstract CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) throws CommandExecutionException;
}
