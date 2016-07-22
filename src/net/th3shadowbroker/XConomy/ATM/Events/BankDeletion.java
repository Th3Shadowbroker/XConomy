package net.th3shadowbroker.XConomy.ATM.Events;

import net.th3shadowbroker.XConomy.ATM.ATM;
import net.th3shadowbroker.XConomy.Cache.CacheState;
import net.th3shadowbroker.XConomy.Exceptions.ATMExistsException;
import net.th3shadowbroker.XConomy.Exceptions.ATMNotExistsException;
import net.th3shadowbroker.XConomy.Exceptions.NotInCacheException;
import net.th3shadowbroker.XConomy.Loaders.Events;
import net.th3shadowbroker.XConomy.Objects.XConomyPlayer;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class BankDeletion implements Listener {
    
    private final Events loader;
    private final main plugin;
    
    //Construction
    public BankDeletion( Events loaderClass )
    {
        
        this.loader = loaderClass;
        
        this.plugin = loaderClass.loader;
        
    }
    
    //Event to create banks
    @EventHandler
    public void removeBank( PlayerInteractEvent ev )
    {
        if ( plugin.getCache().getCacheEntry( new XConomyPlayer( ev.getPlayer() ) ).getState() == CacheState.WAIT_DELETION ){
            
            if ( ev.getAction() == Action.RIGHT_CLICK_BLOCK )
            {
                
                Block cBlockRaw = ev.getClickedBlock();
                Player p = ev.getPlayer();
                Location cBlock = new Location( cBlockRaw.getWorld() , cBlockRaw.getX(), cBlockRaw.getY(), cBlockRaw.getZ() );

                
                try {
                
                    
                    if ( plugin.ATMConfig.ATMExists( cBlock ) )
                    {

                        ATM atm = new ATM( cBlock );
                        plugin.ATMConfig.removeATM( atm );
                        p.sendMessage( plugin.ChatPrefix() + plugin.lang.getText( "SystemATMRemoved" ) );
                        plugin.getCache().updateCacheEntry( new XConomyPlayer( p ) , CacheState.NORMAL );

                    } else {

                        p.sendMessage( plugin.ChatPrefix() + plugin.lang.getText( "SystemATMNotFound" ) );
                        plugin.getCache().updateCacheEntry( new XConomyPlayer( p ) , CacheState.NORMAL );

                    }
                    
                } catch ( NotInCacheException | ATMNotExistsException ex ) {
                    
                    p.sendMessage( plugin.ChatPrefix() + plugin.lang.getText( "SystemUniErrMsg" ) );
                    ex.printStackTrace();
                    
                }
                
            }
            
        }
    }
    
}
