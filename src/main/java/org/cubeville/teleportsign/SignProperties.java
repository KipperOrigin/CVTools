package org.cubeville.teleportsign;

import java.util.HashMap;
import java.util.Map;

public class SignProperties {
   
    private boolean clearInv;
    private boolean clearPots;
    private boolean heal;
    
    public SignProperties() {
        clearInv = false;
        clearPots = false;
        heal = false;
    }
    
    public SignProperties(Map<String, Object> ret) {
        clearInv = (boolean) ret.get("clearInv");
        clearPots = (boolean) ret.get("clearPots");
        heal = (boolean) ret.get("heal");
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
        return ret;
    }

}
