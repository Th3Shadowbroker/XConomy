package net.th3shadowbroker.Thirdparty.Citizens.Events;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import net.th3shadowbroker.Thirdparty.Citizens.Objects.Thirdparty_Config_Citizens;
import net.th3shadowbroker.Thirdparty.Citizens.Thirdparty_Citizens;
import net.th3shadowbroker.XConomy.Cache.CacheState;
import net.th3shadowbroker.XConomy.Exceptions.NotInCacheException;
import net.th3shadowbroker.XConomy.Objects.XConomyPlayer;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CreateRemoveBankNPC implements Listener
{
   
    private final main XConomy;
    private final Thirdparty_Config_Citizens Config;
    
    //Construction
    public CreateRemoveBankNPC( Thirdparty_Citizens loaderClass )
    {
        
        this.XConomy = loaderClass.XConomy;
        this.Config = loaderClass.Config;
        
    }
    
    //Create bank npc event
    @EventHandler
    public void CreateBankNPCOnRight( NPCRightClickEvent ev )
    {
        
        NPC ClickedNPC = ev.getNPC();
        Player Player = ev.getClicker();

            //Check if player is waiting
            if ( XConomy.getCache().getCacheEntry( new XConomyPlayer( Player ) ).getState() == CacheState.WAIT_CREATE_BANK )
            {
                
                ev.setCancelled( true );
                
                //NPC already registered
                if ( Config.NPCRegistered( ClickedNPC ) )
                {
                    
                    Player.sendMessage( XConomy.ChatPrefix() + XConomy.lang.getText( "SystemNPCExists" ) );
                    Player.sendMessage( XConomy.ChatPrefix() + "§cBank creation cancelled" );
                    
                    try {
                        XConomy.getCache().updateCacheEntry( new XConomyPlayer( Player ) , CacheState.NORMAL );
                    } catch (NotInCacheException ex) {
                        Logger.getLogger(CreateRemoveBankNPC.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    
                    Player.sendMessage( XConomy.ChatPrefix() + XConomy.lang.getText( "SystemNPCCreate" ) );
                    
                    Config.SaveNPC( ClickedNPC );
                    
                    try {
                        XConomy.getCache().updateCacheEntry( new XConomyPlayer( Player ) , CacheState.NORMAL );
                    } catch (NotInCacheException ex) {
                        Logger.getLogger(CreateRemoveBankNPC.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                
            }
            
            //Delete NPC ?
            if ( XConomy.getCache().getCacheEntry( new XConomyPlayer( Player ) ).getState() == CacheState.WAIT_DELETION )
            {
                if ( Config.NPCRegistered( ClickedNPC ) )
                {
                    
                    Player.sendMessage( XConomy.ChatPrefix() + XConomy.lang.getText( "SystemNPCRemove" ) );
                    
                    Config.RemoveNPC( ClickedNPC );
                    
                    try {
                        XConomy.getCache().updateCacheEntry( new XConomyPlayer( Player ) , CacheState.NORMAL );
                    } catch (NotInCacheException ex) {
                        Logger.getLogger(CreateRemoveBankNPC.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            }
   
    }
    
}


