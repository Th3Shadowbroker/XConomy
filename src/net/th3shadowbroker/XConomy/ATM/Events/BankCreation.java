package net.th3shadowbroker.XConomy.ATM.Events;

import net.th3shadowbroker.XConomy.ATM.ATM;
import net.th3shadowbroker.XConomy.Cache.CacheState;
import net.th3shadowbroker.XConomy.Exceptions.ATMExistsException;
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
        //Citizens preventions
        try {
            
            CacheState tmp = plugin.getCache().getCacheEntry( new XConomyPlayer( ev.getPlayer() ) ).getState();
            
        } catch ( Exception ex ) {
            
            return;
            
        }
        //End of prevention
        
        if ( plugin.getCache().getCacheEntry( new XConomyPlayer( ev.getPlayer() ) ).getState() == CacheState.WAIT_CREATE_BANK ){
            
            if ( ev.getAction() == Action.RIGHT_CLICK_BLOCK )
            {
                
                Block cBlockRaw = ev.getClickedBlock();
                Player p = ev.getPlayer();
                Location cBlock = new Location( cBlockRaw.getWorld() , cBlockRaw.getX(), cBlockRaw.getY(), cBlockRaw.getZ() );

                
                try {
                
                    
                    if ( !plugin.ATMConfig.ATMExists( cBlock ) )
                    {

                        ATM atm = new ATM( cBlock );
                        plugin.ATMConfig.addATM( atm );
                        p.sendMessage( plugin.ChatPrefix() + plugin.lang.getText( "SystemATMCreated" ) );
                        plugin.getCache().updateCacheEntry( new XConomyPlayer( p ) , CacheState.NORMAL );

                    } else {

                        p.sendMessage( plugin.ChatPrefix() + plugin.lang.getText( "SystemATMExists" ) );
                        plugin.getCache().updateCacheEntry( new XConomyPlayer( p ) , CacheState.NORMAL );

                    }
                    
                } catch ( ATMExistsException | NotInCacheException ex ) {
                    
                    p.sendMessage( plugin.ChatPrefix() + plugin.lang.getText( "SystemUniErrMsg" ) );
                    ex.printStackTrace();
                    
                }
                
            }
            
        }
    }
    
}
