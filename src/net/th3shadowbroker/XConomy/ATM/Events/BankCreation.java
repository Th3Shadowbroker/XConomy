package net.th3shadowbroker.XConomy.ATM.Events;

import net.th3shadowbroker.XConomy.ATM.ATM;
import net.th3shadowbroker.XConomy.Cache.CacheState;
import net.th3shadowbroker.XConomy.Exceptions.ATMExistsException;
import net.th3shadowbroker.XConomy.Exceptions.NotInCacheException;
import net.th3shadowbroker.XConomy.Loaders.Events;
import net.th3shadowbroker.XConomy.Objects.XConomyPlayer;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class BankCreation implements Listener {
    
    private final Events loader;
    private final main plugin;
    
    //Construction
    public BankCreation( Events loaderClass )
    {
        
        this.loader = loaderClass;
        
        this.plugin = loaderClass.loader;
        
    }
    
    //Event to create banks
    @EventHandler
    public void createBank( PlayerInteractEvent ev )
    {
        if ( plugin.getCache().getCacheEntry( new XConomyPlayer( ev.getPlayer() ) ).getState() == CacheState.WAIT_CREATE_BANK ){
            
            if ( ev.getAction() == Action.RIGHT_CLICK_BLOCK )
            {
                
                if( !plugin.ATMConfig.ATMExists( ev.getClickedBlock().getLocation() ) )
                {
                    
                    try {
                        
                        plugin.ATMConfig.addATM( new ATM( ev.getClickedBlock().getLocation() ) );
                        
                            try{

                                plugin.getCache().updateCacheEntry( new XConomyPlayer( ev.getPlayer() ) , CacheState.NORMAL );
                                
                                ev.getPlayer().sendMessage( plugin.ChatPrefix() + plugin.lang.getText( "SystemATMCreated" ) );
                                
                                return;

                            } catch ( NotInCacheException cx ) {

                                plugin.Console.write( "Error while caching!" );

                                cx.printStackTrace();

                            }
                        
                        
                        
                    } catch (ATMExistsException ex) {
                        
                            try{

                                plugin.getCache().updateCacheEntry( new XConomyPlayer( ev.getPlayer() ) , CacheState.NORMAL );

                            } catch ( NotInCacheException cx ) {

                                plugin.Console.write( "Error while caching!" );

                                cx.printStackTrace();

                            }
                        
                        ev.getPlayer().sendMessage( plugin.ChatPrefix() + plugin.lang.getText( "SystemATMExists" ) );
                        
                    }
                    
                }
                else
                {
                    
                        try{

                            plugin.getCache().updateCacheEntry( new XConomyPlayer( ev.getPlayer() ) , CacheState.NORMAL );

                        } catch ( NotInCacheException cx ) {

                            plugin.Console.write( "Error while caching!" );

                            cx.printStackTrace();

                        }
                    
                    ev.getPlayer().sendMessage( plugin.ChatPrefix() + plugin.lang.getText( "SystemATMExists" ) );
                    
                }
                
            }
            
        }
    }
    
}
