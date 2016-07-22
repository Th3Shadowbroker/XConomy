package net.th3shadowbroker.XConomy.GUI;

import net.th3shadowbroker.XConomy.Blueprints.ATMInterface;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GUIItemAction implements Listener {
 
    private final GUIItemStack Item;
    private final GUIItemExtension Extension;
    private final main XConomy;
    private final ATMInterface ATMInterface;
    
    //Construction
    public GUIItemAction( GUIItemStack TriggerItem, ATMInterface ATMInterface, GUIItemExtension Extension )
    {
        
        this.Item = TriggerItem;
        
        this.Extension = Extension;
        
        this.XConomy = main.getInstance();
        
        this.ATMInterface = ATMInterface;
        
        Bukkit.getPluginManager().registerEvents( this , XConomy );
        
    }
    
    //The Event
    @EventHandler( priority = EventPriority.NORMAL )
    public void GUIActionEvent( InventoryClickEvent ev )
    {
        if ( ev.getInventory().getName().equals( ATMInterface.getInventory().getName() ) )
        {
            
            if ( ev.getCurrentItem().getItemMeta().getDisplayName().equals( Item.getName() ) )
            {
                
                if ( Extension != null )
                {
                    Extension.UpdateInformations( (Player) ev.getWhoClicked() , ATMInterface );
                    Extension.Run();
                }

                ev.setCancelled( true );

            } else {

                ev.setCancelled( false );

            }
            
        }
    }
    
}
