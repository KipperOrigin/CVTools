package org.cubeville.teleportsign;

import org.cubeville.commons.commands.CommandParameterType;
import org.cubeville.cvtools.CVTools;

public class CommandParameterTeleportSign implements CommandParameterType {

    @Override
    public boolean isValid(String value) {
        return CVTools.getInstance().getTeleportSignManager().containsSign(value);
    }

    @Override
    public String getInvalidMessage(String value) {
        return "&6" + value + " &cis no valid teleport sign!";
    }

    @Override
    public Object getValue(String value) {
        return CVTools.getInstance().getTeleportSignManager().getSign(value);
    }

}
