package XZot1K.plugins.zb.packets.ping.versions;

import XZot1K.plugins.zb.packets.ping.Ping;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Ping1_8R3 implements Ping
{

    public int getPing(Player player)
    {
        return ((CraftPlayer) player).getHandle().ping;
    }

}
