package org.cubeville.mail.containers;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MailItem {

    private UUID sender = null;
    private String subject = null;
    private String body = null;
    private ItemStack gift = null;
    private boolean read = false;
    
    public MailItem(Player player) {
        sender = player.getUniqueId();
    }
    
    public void setSubject(String s) {
        subject = s;
    }
    
    public void startBody(String b) {
        body = b;
    }
    
    public void continueBody(String b) {
        body = body + " " + b;
    }
    
    public void addGift(ItemStack i) {
        gift = i;
    }
    
    public void read() {
        read = true;
    }
    
    public void unread() {
        read = false;
    }
    
    public boolean containsSubject() {
        return subject != null;
    }
    
    public boolean containsBody() {
        return body != null;
    }
    
    public boolean containsGift() {
        return gift != null;
    }
    
    public boolean isRead() {
        return read;
    }
    
    public String getSenderName() {
        return Bukkit.getPlayer(sender).getName();
    }
    
    public UUID getSenderUUID() {
        return sender;
    }
    
    public String getSubject() {
        return subject;
    }
    
    public String getBody() {
        return body;
    }
    
    public ItemStack getGift() {
        return gift;
    }

}
