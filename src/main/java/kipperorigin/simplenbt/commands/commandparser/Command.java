package kipperorigin.simplenbt.commands.commandparser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;

// TODO: Make textparameters use all remaining arguments to permit spaces
// TODO: Make optional textparameters
// TODO: Use quotes to include spaces
// TODO: Aliases for prefixed parameters and command parts?
// TODO: Default values for optional parametes

public abstract class Command
{
    private List<String> commands;
    private Set<String> flags;
    private Map<String, CommandParameterType> optional;
    private Map<String, CommandParameterType> mandatory;
    private List<CommandParameterType> text;

    public Command(String fullCommand) {
        commands = Arrays.asList(fullCommand.split(" "));
        flags = new HashSet<>();
        optional = new HashMap<>();
        mandatory = new HashMap<>();
        text = new ArrayList<>();
    }

    public String getFullCommand() {
        String ret = commands.get(0);
        for(int i = 1; i < commands.size(); i++) ret += " " + commands.get(i);
        return ret;
    }
    
    protected void addFlag(String flag) {
        flags.add(flag);
    }

    protected void addTextParameter(CommandParameterType type) {
        text.add(type);
    }
    
    protected void addParameter(String name, boolean optional, CommandParameterType type) {
        if(optional) {
            this.optional.put(name, type);
        }
        else {
            mandatory.put(name, type);
        }
    }

    public boolean checkCommand(String[] args) {
        if(args.length < commands.size()) return false;
        for(int i = 0; i < commands.size(); i++) {
            if(!args[i].equals(commands.get(i))) {
                return false;
            }
        }
        return true;
    }

    public String checkParameters(String[] args) {
        Set<String> flagsChecked = new HashSet<>();
        Set<String> mandatoryParametersChecked = new HashSet<>();
        Set<String> optionalParametersChecked = new HashSet<>();
        int textParametersSet = 0;

        for(int i = commands.size(); i < args.length; i++) {
            String[] parts = args[i].split(":", 2);
            String name = parts[0];
            if(parts.length == 1) {
                if(flags.contains(name)) {
                    if(!flagsChecked.add(name)) {
                        return "Flag '" + name + "' can't be used twice!";
                    }
                }
                else {
                    if(textParametersSet == text.size()) return "Too many parameters!";
                    if(!text.get(textParametersSet).isValid(name)) return text.get(textParametersSet).getInvalidMessage(name);
                    textParametersSet++;
                }
            }
            else {
                String par = parts[1];
                if(optional.containsKey(name)) {
                    if(!optionalParametersChecked.add(name)) return "Parameter " + name + " can't be used twice!";
                    if(!optional.get(name).isValid(par)) return optional.get(name).getInvalidMessage(name);
                }
                else if(mandatory.containsKey(name)) {
                    if(!mandatoryParametersChecked.add(name)) return "Parameter " + name + " can't be used twice!";
                    if(!mandatory.get(name).isValid(par)) return mandatory.get(name).getInvalidMessage(name);
                }
                else {
                    System.out.println("Mandatory pars: " + mandatory.keySet());
                    return "Unknown parameter " + name + "!";
                }
            }
        }
        if(textParametersSet != text.size()) {
            return "Wrong number of parameters!";
        }
        if(mandatoryParametersChecked.size() != mandatory.size()) {
            return "Mandatory parameter(s) missing!";
        }
        return null;
    }

    public void execute(Player player, String[] args) {
        Set<String> flags = new HashSet<>();
        Map<String, Object> parameters = new HashMap<>();
        List<Object> textParameters = new ArrayList<>();
        for(int i = commands.size(); i < args.length; i++) {
            String[] parts = args[i].split(":", 2);
            String name = parts[0];
            if(parts.length == 1) {
                if(this.flags.contains(name)) {
                    flags.add(name);
                }
                else {
                    textParameters.add(text.get(textParameters.size()).getValue(name));
                }
            }
            else {
                if(mandatory.containsKey(name)) {
                    parameters.put(name, mandatory.get(name).getValue(parts[1]));
                }
                else {
                    parameters.put(name, optional.get(name).getValue(parts[1]));
                }
            }
        }
        execute(player, flags, parameters, textParameters);
    }
    
    public abstract void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> textParameters);
}
