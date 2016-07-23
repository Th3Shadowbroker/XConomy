
package net.th3shadowbroker.XConomy.GUI;

import net.th3shadowbroker.XConomy.Blueprints.ATMInterface;
import net.th3shadowbroker.XConomy.Loaders.Events;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GUIItemBlocker implements Listener {
    
    protected Events loader;
    protected ATMInterface menu;
    
    public GUIItemBlocker( Events loaderClass , ATMInterface menu )
    {
        
        this.loader = loaderClass;
        this.menu = menu;
        
        Bukkit.getPluginManager().registerEvents( this , loader.loader );
        
    }
    
    @EventHandler( priority = EventPriority.LOW )
    public void blockItemInput( InventoryClickEvent ev )
    {
        if ( ev.getCurrentItem() instanceof ItemStack )
        {
            if ( ev.getInventory().getName().equals( menu.getInventory().getName() ) )
            {
                if ( !ev.isCancelled() )
                {
                    ev.setCancelled( true );
                }
            }
        }

    }
    
}
