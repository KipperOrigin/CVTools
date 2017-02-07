package org.cubeville.teleportsign;

import java.util.ArrayList;
import java.util.List;

public class TeleportSignManager {
    
    private List<TeleportSign> signs;
    
    public TeleportSignManager() {
        signs = new ArrayList<>();
    }
    
    public void addSign(String name) {
        if (!containsSign(name)) signs.add(new TeleportSign(name));
    }
    
    public void removeSign(String name) {
        signs.remove(new TeleportSign(name));
    }
    
    public boolean containsSign(String name) {
        for (TeleportSign sign: signs) {
            if (sign.getName().equalsIgnoreCase(name)) return true;
        }
        return false;
    }
    
    public TeleportSign getSign(String name) {
        for (TeleportSign sign: signs) {
            if (sign.getName().equalsIgnoreCase(name)) return sign;
        }
        return null;
    }

}
