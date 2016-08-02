package net.th3shadowbroker.XConomy.ATM.Events;

import net.th3shadowbroker.XConomy.Blueprints.ATMInterface;
import net.th3shadowbroker.XConomy.Defaults.DefaultATMInterface;
import net.th3shadowbroker.XConomy.GUI.GUIItemBlocker;
import net.th3shadowbroker.XConomy.Loaders.Events;
import net.th3shadowbroker.XConomy.Permissions.Permissions;
import net.th3shadowbroker.XConomy.Permissions.Permissions.XConomyPermission;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

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
        
        if ( p.hasPermission( Permissions.GetPermission( XConomyPermission.ATM ) ) )
        {

            if ( ev.getAction() == Action.RIGHT_CLICK_BLOCK && ev.getHand() == EquipmentSlot.HAND )
            {

                Block clickedBlock = ev.getClickedBlock();
                Location cBlock = new Location( clickedBlock.getWorld(), clickedBlock.getX(), clickedBlock.getY(), clickedBlock.getZ() );
                
                if ( plugin.ATMConfig.ATMExists( cBlock ) )
                {
  
                        DefaultATMInterface test = new DefaultATMInterface(p);
                        GUIItemBlocker testBlocker = new GUIItemBlocker( this.loader, test );
                        test.OpenTo( p , true );

                }
                            
            }
        }
        
    }
    
}
