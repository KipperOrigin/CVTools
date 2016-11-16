package kipperorigin.simplenbt.commands.commandparser;

import java.util.List;
import java.util.ArrayList;

import org.bukkit.entity.Player;

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
        String parameterError = null;
        for(Command command: commands) {
            if(command.checkCommand(args)) {
                parameterError = command.checkParameters(args);
                if(parameterError == null) {
                    command.execute(player, args);
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
}
