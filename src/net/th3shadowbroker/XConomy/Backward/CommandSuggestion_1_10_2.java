package net.th3shadowbroker.XConomy.Backward;

import net.minecraft.server.v1_10_R1.IChatBaseComponent;
import net.minecraft.server.v1_10_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_10_R1.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class CommandSuggestion_1_10_2 {
    
    protected String message = "";
    protected String action = "";
    protected String m = "\"";
    
    //Construction
    public CommandSuggestion_1_10_2(String message, String command)
    {
        
        this.message = "{\"text\":\"%MSG% \",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"%CMD%\"}}";
        this.message = this.message.replaceAll("%MSG%", message.replaceAll("&", "§"));
        this.message = this.message.replaceAll("%CMD%", command);
        
    }
    
    //Send as a package
    public void sendToPlayer(Player p)
    {
        
        try {
            
                IChatBaseComponent comp = ChatSerializer.a(message);
                
                PacketPlayOutChat packet = new PacketPlayOutChat(comp);
                
                ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
                
            
        } catch ( Exception ex ) {
            
            ex.printStackTrace();
            
        }
        
    }
}
