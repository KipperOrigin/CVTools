package org.cubeville.mail.containers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MailBox {

    private UUID owner;
    private int boxSize;
    private int archiveSize;
    private List<MailItem> mail;
    private List<MailItem> archive;
    
    public MailBox(Player player) {
        owner = player.getUniqueId();
        boxSize = 50;
        archiveSize = 10;
        mail = new ArrayList<>();
        archive = new ArrayList<>();
    }
    
    public void setBoxSize(int i) {
        boxSize = i;
    }
    
    public void setArchiveSize(int i) {
        archiveSize = i;
    }
    
    public void addMail(MailItem m) {
        mail.add(m);
    }
    
    public void removeMailItem(MailItem m) {
        mail.remove(m);
    }
    
    public void archive(MailItem m) {
        if (mail.contains(m) && !archiveIsFull()) {
            mail.remove(m);
            archive.add(m);
        }
    }
    
    public void unarchive(MailItem m) {
        if (archive.contains(m) && !mailIsFull()) {
            archive.remove(m);
            mail.add(m);
        }
    }
    
    public boolean checkMailSize(int i) {
        if (mail.size() >= i && i > 0) {
            return true;
        }
        return false;
    }
    
    public boolean checkArchiveSize(int i) {
        if (archive.size() >= i && i > 0) {
            return true;
        }
        return false;
    }
    
    public boolean mailIsFull() {
        return mail.size() < boxSize;
    }
    
    public boolean archiveIsFull() {
        return archive.size() < archiveSize;
    }
    
    public String getOwnerName() {
        return Bukkit.getPlayer(owner).getName();
    }
    
    public UUID getOwnerUUID() {
        return owner;
    }
    
    public int getGiftSize() {
        int i = 0;
        for (MailItem m: mail) {
            if (m.containsGift()) i ++;
        }
        return i;
    }
    
    public int getReadAmount() {
        int i = 0;
        for (MailItem m: mail) {
            if (m.isRead()) i ++;
        }
        return i;
    }
    
    public List<MailItem> getMailContents() {
        return mail;
    }
    
    public List<MailItem> getArchiveContents() {
        return archive;
    }
    
    public String getMail() {
        String color = "&a";
        if (mailIsFull()) color = "&c";
        return color + mail.size() + "&f/" + color + boxSize;
    }
    
    public String getArchive() {
        String color = "&a";
        if (archiveIsFull()) color = "&c";
        return color + archive.size() + "&f/" + color + archiveSize;
    }
    
    public MailItem getMailItem(int i) {
        return mail.get(i);
    }
    
    public MailItem getArchiveItem(int i) {
        return archive.get(i);
    }
    
    public List<String> getBoxInfo() {
        List<String> info = new ArrayList<>();
        String giftColor = "&a";
        if (getGiftSize() == 0) giftColor = "&c";
        info.add("&aOwner&f: " + getOwnerName());
        info.add("&aBox&f: " + getMail());
        info.add("&aArchive&f: " + getArchive());
        info.add("&aRead&f: &e" + getReadAmount());
        info.add("&aGifts&f: " + giftColor + getGiftSize());
        return info;
    }
}
