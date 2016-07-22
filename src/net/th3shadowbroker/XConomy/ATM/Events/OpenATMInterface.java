package net.th3shadowbroker.XConomy.ATM.Events;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.th3shadowbroker.XConomy.Addons.OverrideManager.OverrideType;
import net.th3shadowbroker.XConomy.Blueprints.ATMInterface;
import net.th3shadowbroker.XConomy.Defaults.DefaultATMInterface;
import net.th3shadowbroker.XConomy.Loaders.Events;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class OpenATMInterface implements Listener
{
    
    private final main plugin;
    private final Events loader;
    
    //Construction
    public OpenATMInterface( Events loaderClass )
    {
        
        this.plugin = loaderClass.loader;
        
        this.loader = loaderClass;
        
    }
    
    //The Events
    @EventHandler
    public void openInterfaceIfBankBlock( PlayerInteractEvent ev )
    {
        
        Player p = ev.getPlayer();
        
        if ( p.hasPermission( "XConomy.useatm" ) )
        {
            if ( ev.getAction() == Action.RIGHT_CLICK_BLOCK )
            {
                
                Block clickedBlock = ev.getClickedBlock();
                Location cBlock = new Location( clickedBlock.getWorld(), clickedBlock.getX(), clickedBlock.getY(), clickedBlock.getZ() );
                
                if ( plugin.ATMConfig.ATMExists( cBlock ) )
                {
//                    if ( plugin.Overrides.GetOverride( OverrideType.ATM_INTERFACE ) != null )                    
//                    {
//                      
//                        try {
//                            
//                            ATMInterface InterfaceOverride = (ATMInterface) plugin.Overrides.GetOverride( OverrideType.ATM_INTERFACE ).GetOverride().newInstance();
//                       
//                        } catch ( Exception  ex ) {
//                            
//                            Logger.getLogger(OpenATMInterface.class.getName()).log(Level.SEVERE, null, ex);
//                            
//                        }
//                        
//                    } else {
                        
                        ATMInterface test = new DefaultATMInterface( p );
                    
                        test.OpenTo( p , true );
                        
//                    }
                }
                            
            }
        }
        
    }
    
}
