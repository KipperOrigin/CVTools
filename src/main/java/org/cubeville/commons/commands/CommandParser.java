package org.cubeville.commons.commands;

import java.util.List;
import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.cubeville.commons.utils.Colorize;

public class CommandParser
{
    List<Command> commands;

    public CommandParser() {
        commands = new ArrayList<>();
    }

    public void addCommand(Command command) {
        commands.add(command);
    }

    public boolean execute(Player player, String[] args) {
        String fullCommand = "";
    	
        for (String arg: args)
        	fullCommand += arg + " ";
        
        args = fullCommand.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        
        for (int x = 0; x < args.length; x++)
        	args[x] = args[x].replace("\"", "");
    	
        try {
            String parameterError = null;
            for(Command command: commands) {
                if(command.checkCommand(args)) {
                    parameterError = command.checkParameters(args);
                    if(parameterError == null) {
                        CommandResponse response = command.execute(player, args);
                        if(response == null) {
                            player.sendMessage(Colorize.addColor("&aCommand executed successfully."));
                        }
                        else {
                            for (String message: response.getMessages())
                                player.sendMessage(Colorize.addColor(message));
                        }
                        return true;
                    }
                }
            }
            
            if(parameterError != null) {
                player.sendMessage(parameterError);
            }
            
            else {
                player.sendMessage("Unknown command!");
            }
            return false;
        }
        catch(CommandExecutionException e) {
            player.sendMessage(Colorize.addColor(e.getMessage()));
            return true;
        }
        catch(IllegalArgumentException e) {
            String msg = Colorize.addColorWithoutHeader(e.getMessage());
            if(Colorize.removeColor(msg).equals(msg)) {
                    msg = Colorize.addColor("&c" + msg);
            }
            else {
                msg = Colorize.addColor(e.getMessage());
            }
            player.sendMessage(msg);
            return true;
        }
    }
 
}
