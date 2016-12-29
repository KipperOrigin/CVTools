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

    public boolean execute(Player player, String[] argsIn) {
        try {
            String full = "";
            for (String arg: argsIn) {
                if(full.length() > 0) full = full + " ";
                full += arg;
            }

            String[] args = smartSplit(full).toArray(new String[0]);

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

    private List<String> smartSplit(String full) {
        // I don't like this piece of code....
        boolean inQuotes = false;
        List<String> ret = new ArrayList<>();
        ret.add(new String());
        for(int i = 0; i < full.length(); i++) {
            String current = ret.get(ret.size() - 1);
            int lsize = ret.size();
            int size = current.length();
            char c = full.charAt(i);
            char last = (size > 0 ? current.charAt(size - 1) : ' ');

            if(c == '"' && (size == 0 || last == ' ' || last == ':') && inQuotes == false) {
                inQuotes = true;
            }
            else if(c == '"' && inQuotes == true && (i == full.length() - 1 || full.charAt(i + 1) == ' ')) {
                inQuotes = false;
            }
            else if(c == ' ' && inQuotes == false) {
                ret.add(new String());
            }
            else {
                ret.set(lsize - 1, ret.get(lsize - 1) + full.charAt(i));
            }

        }

        if(inQuotes) throw new IllegalArgumentException("Unbalanced quotes!");
        return ret;
    }
}
