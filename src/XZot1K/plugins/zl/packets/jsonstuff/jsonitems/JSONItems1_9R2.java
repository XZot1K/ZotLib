package XZot1K.plugins.zl.packets.jsonstuff.jsonitems;

import net.minecraft.server.v1_9_R2.NBTTagCompound;
import org.bukkit.craftbukkit.v1_9_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class JSONItems1_9R2 implements JSONItems
{
    @Override
    public String getJSONItem(ItemStack itemStack)
    {
        net.minecraft.server.v1_9_R2.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound compound = new NBTTagCompound();
        compound = nmsItemStack.save(compound);
        return compound.toString();
    }
}
