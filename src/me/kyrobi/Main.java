package me.kyrobi;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Main extends JavaPlugin {

    public Set<PacketType> ignorePackets = new HashSet<>();

    @Override
    public void onEnable() {

        ignorePackets.add(PacketType.Play.Server.ENTITY_METADATA);
        ignorePackets.add(PacketType.Play.Server.ENTITY_HEAD_ROTATION);
        ignorePackets.add(PacketType.Play.Server.REL_ENTITY_MOVE_LOOK);
        ignorePackets.add(PacketType.Play.Server.REL_ENTITY_MOVE);
        ignorePackets.add(PacketType.Play.Server.ENTITY_VELOCITY);
        ignorePackets.add(PacketType.Play.Server.MAP_CHUNK);
        ignorePackets.add(PacketType.Play.Server.UPDATE_ATTRIBUTES);
        ignorePackets.add(PacketType.Play.Server.UPDATE_TIME);
        ignorePackets.add(PacketType.Play.Server.ENTITY_EFFECT);
        ignorePackets.add(PacketType.Play.Server.LIGHT_UPDATE);
        ignorePackets.add(PacketType.Play.Server.ENTITY_STATUS);
        ignorePackets.add(PacketType.Play.Server.ENTITY_LOOK);
        ignorePackets.add(PacketType.Play.Server.ENTITY_DESTROY);
        ignorePackets.add(PacketType.Play.Server.KEEP_ALIVE);
        ignorePackets.add(PacketType.Play.Server.SPAWN_ENTITY);
        ignorePackets.add(PacketType.Play.Server.NAMED_SOUND_EFFECT);
        ignorePackets.add(PacketType.Play.Server.ENTITY_TELEPORT);
        ignorePackets.add(PacketType.Play.Server.PLAYER_INFO);

        ignorePackets.add(PacketType.Play.Client.LOOK);
        ignorePackets.add(PacketType.Play.Client.POSITION);
        ignorePackets.add(PacketType.Play.Client.POSITION_LOOK);
        ignorePackets.add(PacketType.Play.Client.KEEP_ALIVE);
        ignorePackets.add(PacketType.Play.Client.TELEPORT_ACCEPT);
        ignorePackets.add(PacketType.Play.Client.TAB_COMPLETE);

        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(this, ListenerPriority.NORMAL, PacketType.values()) {
            @Override
            public void onPacketSending(PacketEvent event) {

                PacketType packet = event.getPacketType();

                if(ignorePackets.contains(packet)){
                    return;
                }
                getLogger().info(event.getPlayer().getName()+ " - Server -> Client: " + event.getPacket());
            }

            @Override
            public void onPacketReceiving(PacketEvent event) {

                PacketType packet = event.getPacketType();

                if(ignorePackets.contains(packet)){
                    return;
                }
                getLogger().info(event.getPlayer().getName()+ " - Client -> Server: " + event.getPacket());
            }
        });
    }
}
