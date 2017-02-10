package org.cubeville.teleportsign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

@SerializableAs("SignProperties")
public class SignProperties implements ConfigurationSerializable {
   
    private boolean clearInv;
    private boolean clearPots;
    private boolean heal;
    private List<String> messages;
    
    public SignProperties() {
        clearInv = false;
        clearPots = false;
        heal = false;
        messages = new ArrayList<>();
    }
    
    @SuppressWarnings("unchecked")
	public SignProperties(Map<String, Object> ret) {
        clearInv = (boolean) ret.get("clearInv");
        clearPots = (boolean) ret.get("clearPots");
        heal = (boolean) ret.get("heal");
        messages = (List<String>) ret.get("messages");
    }
    
    public void setClearsInv(boolean clear) {
        clearInv = clear;
    }
    
    public void setClearsPots(boolean clear) {
        clearPots = clear;
    }
    
    public void setHeal(boolean heal) {
        this.heal = heal;
    }
    
    public void addMessage(String message) {
    	messages.add(message);
    }
    
    public void removeMessage(int i) {
    	messages.remove(i);
    }
    
    public List<String> getMessages() {
    	return messages;
    }
    
    public String getMessage(int i) {
    	if (messages.size() < i) return null;
    	return messages.get(i);
    }
    
    public boolean clearsInv() {
        return clearInv;
    }
    
    public boolean clearsPots() {
        return clearPots;
    }
    
    public boolean heals() {
        return heal;
    }
    
    public Map<String, Object> serialize() {
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("clearInv", clearInv);
        ret.put("clearPots", clearPots);
        ret.put("heal", heal);
        ret.put("messages", messages);
        return ret;
    }

}
