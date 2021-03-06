package XZot1K.plugins.zb.utils.jsonmsgs;

import XZot1K.plugins.zb.ZotBox;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONMessage
{

    private ZotBox plugin = ZotBox.getInstance();
    private JSONObject chatObject;

    @SuppressWarnings("unchecked")
    public JSONMessage(String text)
    {
        chatObject = new JSONObject();

        if (text != null)
        {
            getChatObject().put("text", plugin.getGeneralLibrary().color(text));
        }
    }

    public JSONObject getChatObject()
    {
        return chatObject;
    }

    @SuppressWarnings("unchecked")
    public void addExtra(JSONExtra extraObject)
    {
        if (!chatObject.containsKey("extra"))
        {
            chatObject.put("extra", new JSONArray());
        }
        JSONArray extra = (JSONArray) chatObject.get("extra");
        extra.add(extraObject.getExtraObject());
        getChatObject().put("extra", extra);
    }

    public void sendJSONToPlayer(Player player)
    {
        plugin.getPacketLibrary().getJSONMessageSender().sendJSONMessage(player, getChatObject().toJSONString());
    }

    @SuppressWarnings("unchecked")
    public void setClickEvent(JSONClickAction action, String value)
    {
        JSONObject clickEvent = new JSONObject();
        clickEvent.put("action", action.name().toLowerCase());
        clickEvent.put("value", plugin.getGeneralLibrary().color(value));
        getChatObject().put("clickEvent", clickEvent);
    }

    @SuppressWarnings("unchecked")
    public void setHoverEvent(JSONHoverAction action, String value)
    {
        JSONObject hoverEvent = new JSONObject();
        hoverEvent.put("action", action.name().toLowerCase());
        hoverEvent.put("value", plugin.getGeneralLibrary().color(value));
        getChatObject().put("hoverEvent", hoverEvent);
    }

    public String itemToJSON(ItemStack itemStack)
    {
        return plugin.getPacketLibrary().getJSONItemGetter().getJSONItem(itemStack);
    }

}
