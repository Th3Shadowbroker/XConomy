package net.th3shadowbroker.Thirdparty.Citizens.Events;

import net.citizensnpcs.api.event.NPCRemoveEvent;
import net.citizensnpcs.api.npc.NPC;
import net.th3shadowbroker.Thirdparty.Citizens.Objects.Thirdparty_Config_Citizens;
import net.th3shadowbroker.Thirdparty.Citizens.Thirdparty_Citizens;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NPCAutoRemove implements Listener
{
    
    private final main XConomy;
    private final Thirdparty_Config_Citizens Config;

    //Construction
    public NPCAutoRemove( Thirdparty_Citizens loaderClass )
    {
        
        this.XConomy = loaderClass.XConomy;
        
        this.Config = loaderClass.Config;
        
    }
    
    @EventHandler
    public void RemoveBankOnNPCRemove( NPCRemoveEvent ev )
    {
        
        NPC RemovedNPC = ev.getNPC();
        
        if ( Config.NPCRegistered( RemovedNPC ) )
        {
            Config.RemoveNPC( RemovedNPC );
            XConomy.Console.writeAPI( "NPC-Bank removed (" + RemovedNPC.getName() + String.valueOf( RemovedNPC.getId() )  + ")");
        }
        
    }
    
}
