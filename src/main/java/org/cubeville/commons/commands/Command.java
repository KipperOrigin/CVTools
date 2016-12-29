package org.cubeville.commons.commands;

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
// TODO: Default values for optional parameters

public abstract class Command
{
    private List<String> commands;
    private Set<String> flags;
    private Map<String, CommandParameterType> optional;
    private Map<String, CommandParameterType> mandatory;
    private List<CommandParameterType> base;
    int mandatoryBase;

    public Command(String fullCommand) {
        commands = Arrays.asList(fullCommand.split(" "));
        flags = new HashSet<>();
        optional = new HashMap<>();
        mandatory = new HashMap<>();
        base = new ArrayList<>();
        mandatoryBase = 0;
    }

    public String getFullCommand() {
        String ret = commands.get(0);
        for(int i = 1; i < commands.size(); i++) ret += " " + commands.get(i);
        return ret;
    }
    
    public String[] getArgs() {
    	return (String[]) commands.toArray();
    }
    
    public void addFlag(String flag) {
        flags.add(flag);
    }

    public void addBaseParameter(CommandParameterType type) {
        if(base.size() != mandatoryBase) return;
        base.add(type);
        mandatoryBase++;
    }

    public void addOptionalBaseParameter(CommandParameterType type) {
        base.add(type);
    }
    
    public void addParameter(String name, boolean optional, CommandParameterType type) {
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
        int baseParametersSet = 0;

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
                    if(baseParametersSet == base.size()) return "Too many parameters!";
                    if(!base.get(baseParametersSet).isValid(name)) return base.get(baseParametersSet).getInvalidMessage(name);
                    baseParametersSet++;
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
        if(baseParametersSet > base.size() || baseParametersSet < mandatoryBase) {
            return "Wrong number of parameters!";
        }
        if(mandatoryParametersChecked.size() != mandatory.size()) {
            return "Mandatory parameter(s) missing!";
        }
        return null;
    }

    public CommandResponse execute(Player player, String[] args) throws CommandExecutionException {
        Set<String> flags = new HashSet<>();
        Map<String, Object> parameters = new HashMap<>();
        List<Object> baseParameters = new ArrayList<>();
        for(int i = commands.size(); i < args.length; i++) {
            String[] parts = args[i].split(":", 2);
            String name = parts[0];
            if(parts.length == 1) {
                if(this.flags.contains(name)) {
                    flags.add(name);
                }
                else {
                    baseParameters.add(base.get(baseParameters.size()).getValue(name));
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
        return execute(player, flags, parameters, baseParameters);
    }
    
    public abstract CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) throws CommandExecutionException;

    public boolean checkMoreThanOne(Boolean... conditions) {
        int cnt = 0;
        for(Boolean condition: conditions) {
            if(condition) {
                cnt++;
                if(cnt == 2) return true;
            }
        }
        return false;
    }
    
}
