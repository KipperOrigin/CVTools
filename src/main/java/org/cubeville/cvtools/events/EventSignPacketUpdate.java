package org.cubeville.cvtools.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.plugin.Plugin;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;

import org.cubeville.cvtools.CVTools;
import org.cubeville.commons.utils.Colorize;

public class EventSignPacketUpdate extends PacketAdapter {

    public EventSignPacketUpdate(Plugin plugin) {
        super(plugin, ListenerPriority.NORMAL, PacketType.Play.Client.UPDATE_SIGN, PacketType.Play.Server.OPEN_SIGN_EDITOR);
    }
	
    @Override
    public void onPacketSending(PacketEvent event) {
        if (event.getPacketType() == PacketType.Play.Server.OPEN_SIGN_EDITOR) {
        }
    }
	
    @Override
    public void onPacketReceiving(PacketEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.UPDATE_SIGN) {
            PacketContainer editSignPacket = event.getPacket();
            String[] lines = editSignPacket.getStringArrays().getValues().get(0);

            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(CVTools.getInstance(),
                new Runnable() {
                    @Override
                    public void run() {
                        Location loc = new Location(event.getPlayer().getWorld(), editSignPacket.getBlockPositionModifier().getValues().get(0).getX(), 
                                                    editSignPacket.getBlockPositionModifier().getValues().get(0).getY(),
                                                    editSignPacket.getBlockPositionModifier().getValues().get(0).getZ());
                        
                        if (!(loc.getBlock().getState() instanceof Sign))
                            return;
                        
                        Sign sign = (Sign) loc.getBlock().getState();
                        
                        for (int i = 0; i < 4; i++)
                            sign.setLine(i, Colorize.addColor("&0" + lines[i]));
                        
                        sign.update();
                    }
                });
        }
    }
}
