package XZot1K.plugins.zb.packets.actionbars.versions;

import XZot1K.plugins.zb.packets.actionbars.ActionBars;
import net.minecraft.server.v1_8_R2.IChatBaseComponent;
import net.minecraft.server.v1_8_R2.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R2.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ActionBars1_8R2 implements ActionBars
{

	@Override
	public void sendActionbar(Player p, String message)
	{
		IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \"" + message + "\"}");
		PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte) 2);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(bar);
	}

}
