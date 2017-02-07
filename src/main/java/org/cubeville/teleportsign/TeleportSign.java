package org.cubeville.teleportsign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.cubeville.commons.utils.ColorUtils;

public class TeleportSign {
    
    private String name;
    private HashMap<Team, List<SignValue>> signValues;
    private HashMap<Team, SignProperties> signProperties;
    
    public TeleportSign(String name) {
        signValues = new HashMap<>();
        for (Team team: Team.values()) {
            signValues.put(team, new ArrayList<SignValue>());
            signProperties.put(team, new SignProperties());
        }
        this.name = name;
    }
    
    @SuppressWarnings("unchecked")
    public TeleportSign(Map<String, Object> ret) {
        name = (String) ret.get("name");
        for (Team team: Team.values()) {
            if (ret.containsKey(team.name())) {
                List<SignValue> signs = (List<SignValue>) ret.get(team.name());
                if (signs == null) signValues.put(team, null);
                else for (SignValue sign: signs) {
                    addSignValue(team, sign);
                }
            }
        }
    }
    
    public void addSignValue(Team team, SignValue sign) {
        if (signValues.get(team).contains(sign)) {
            for (int i = 0; i < signValues.get(team).size(); i++) {
                if (sign == signValues.get(team).get(i)) {
                    signValues.get(team).remove(i);
                    break;
                }
            }
        }
        signValues.get(team).add(sign);
    }
    
    public void removeSignValue(Team team, String name) {
        for (int i = 0; i < signValues.get(team).size(); i++) {
            if (signValues.get(team).get(i).getName().equalsIgnoreCase(name)) signValues.get(team).remove(i);
        }
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public boolean hasSignValues(Team team) {
        return signValues.size() > 0;
    }
    
    public boolean containsSignValue(Team team, String name) {
        for (SignValue sign:signValues.get(team)) {
            if (sign.getName().equalsIgnoreCase(name)) return true;
        }
        return false;
    }
    
    public SignValue getSignValue(Team team, String name) {
        for (SignValue sign:signValues.get(team)) {
            if (sign.getName().equalsIgnoreCase(name)) return sign;
        }
        return null;
    }
    
    public SignValue getRandomSignValue(Team team) {
        if (!signValues.containsKey(team) ||signValues.get(team).size() == 0) return null;
        int randomNum = ThreadLocalRandom.current().nextInt(0, signValues.get(team).size() + 1);
        return signValues.get(team).get(randomNum);
    }
    
    public SignProperties getSignProperties(Team team) {
        return signProperties.get(team);
    }
    
    public String getName() {
        return name;
    }
    
    public Map<String, Object> serialize() {
        Map<String, Object> ret = new HashMap<>();
        ret.put("name", name);
        for (Map.Entry<Team, List<SignValue>> entry : signValues.entrySet()) {
            ret.put(entry.getKey().name(), entry.getValue());
        }
        return ret;
    }
    
    public void teleportPlayer(Player player, Team team) {
        SignValue sign = getRandomSignValue(team);
        if (sign == null) return;
        player.teleport(getRandomSignValue(team).getLocation());
        runEffectsOnPlayer(player, team, sign);
    }
    
    public void teleportPlayer(Player player, Team team, String name) {
        SignValue sign = getSignValue(team, name);
        if (sign == null) return;
        runEffectsOnPlayer(player, team, sign);
    }
    
    private void runEffectsOnPlayer(Player player, Team team, SignValue sign) {
        player.sendMessage(ColorUtils.addColor("&aYou have been teleported!"));
        if (signProperties.get(team).clearsInv()) player.getInventory().clear();
        if (signProperties.get(team).clearsPots()) {
            for (PotionEffectType type: PotionEffectType.values()) {
                if (player.hasPotionEffect(type)) player.removePotionEffect(type);
            }
        }
        if (signProperties.get(team).heals()) player.setHealth(player.getMaxHealth());
    }
    
    public static enum Team {
        BLACK, BLUE, BROWN, CYAN, GRAY, GREEN, LIGHT_BLUE, LIGHT_GRAY, LIME, LOBBY, MAGENTA, NEUTRAL, NONE, ORANGE, PINK, PURPLE, RED, WHITE, YELLOW;
    }

    
}
