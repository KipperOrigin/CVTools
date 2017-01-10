package org.cubeville.mail.containers;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MailBoxManager {
    
    public Set<MailBox> boxes;
    
    public MailBoxManager() {
        boxes = new HashSet<>();
    }
    
    public void addBox(String playerName) {
        Player p = Bukkit.getPlayer(playerName);
        boxes.add(new MailBox(p));
    }
    
    public void removeBox(String playerName) {
        Player p = Bukkit.getPlayer(playerName);
        for(MailBox box: boxes) {
            if (box.getOwnerUUID() == p.getUniqueId()) boxes.remove(box);
        }
    }
    
    public boolean containsBox(String playerName) {
        Player p = Bukkit.getPlayer(playerName);
        for(MailBox box: boxes) {
            if (box.getOwnerUUID() == p.getUniqueId()) return true;
        }
        return false;
    }
    
    public MailBox getBox(String playerName) {
        Player p = Bukkit.getPlayer(playerName);
        for(MailBox box: boxes) {
            if (box.getOwnerUUID() == p.getUniqueId()) return box;
        }
        throw new IllegalArgumentException("MailBox does not exist for player!");
    }
}
