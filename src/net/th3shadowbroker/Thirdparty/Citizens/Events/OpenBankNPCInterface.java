package net.th3shadowbroker.Thirdparty.Citizens.Events;

import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import net.th3shadowbroker.Thirdparty.Citizens.Objects.Thirdparty_Config_Citizens;
import net.th3shadowbroker.Thirdparty.Citizens.Thirdparty_Citizens;
import net.th3shadowbroker.XConomy.Blueprints.ATMInterface;
import net.th3shadowbroker.XConomy.Cache.CacheState;
import net.th3shadowbroker.XConomy.Defaults.DefaultATMInterface;
import net.th3shadowbroker.XConomy.GUI.GUIItemBlocker;
import net.th3shadowbroker.XConomy.Loaders.Events;
import net.th3shadowbroker.XConomy.Objects.XConomyPlayer;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class OpenBankNPCInterface implements Listener
{
    
    private final main XConomy;
    private final Thirdparty_Config_Citizens Config;
    
    //Construction
    public OpenBankNPCInterface( Thirdparty_Citizens loaderClass )
    {
        
        this.XConomy = loaderClass.XConomy;
        
        this.Config = loaderClass.Config;
        
    }
    
    @EventHandler
    public void OpenBankInterface( NPCRightClickEvent ev )
    {
        
        NPC ClickedNPC = ev.getNPC();
        Player Player = ev.getClicker();

            if ( Config.NPCRegistered( ClickedNPC ) )
            {
                
                ev.setCancelled( true );
                
                if ( XConomy.getCache().getCacheEntry( new XConomyPlayer( Player ) ).getState() == CacheState.NORMAL )
                {
                    if ( !XConomy.CustomInterfaces.UseCustomIntefaces() )
                    {
                        DefaultATMInterface DefaultInterface = new DefaultATMInterface( Player );
                        GUIItemBlocker DefaultInterfaceBlocker = new GUIItemBlocker( Events.getLoaderInstance(), DefaultInterface );
                        DefaultInterface.OpenTo( Player, true );
                    
                    } else {
                        
                        ATMInterface CustomInterface = XConomy.CustomInterfaces.GetInterface();
                        GUIItemBlocker CustomInterfaceBlocker = new GUIItemBlocker( Events.getLoaderInstance(), CustomInterface );
                        CustomInterface.OpenTo( Player, true );
                    }
                
                }
                
            }
            
    }
    
}
