package net.th3shadowbroker.XConomy.GUI;

import net.th3shadowbroker.XConomy.Blueprints.ATMInterface;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GUIItemAction implements Listener {
 
    private final GUIItemStack Item;
    private final GUIItemExtension Extension;
    private final main XConomy;
    private final ATMInterface ATMInterface;
    private boolean RemoveAfterTriggered = true;

    //Construction
    public GUIItemAction( GUIItemStack TriggerItem, ATMInterface ATMInterface, GUIItemExtension Extension )
    {
        
        this.Item = TriggerItem;
        
        this.Extension = Extension;
        
        this.XConomy = main.getInstance();
        
        this.ATMInterface = ATMInterface;

        Bukkit.getPluginManager().registerEvents( this , XConomy );

    }
    
    //An construction option to prevent Item removal after triggered
    public GUIItemAction( GUIItemStack TriggerItem, ATMInterface ATMInterface, GUIItemExtension Extension, boolean RemoveAfterTriggered )
    {
        
        this.Item = TriggerItem;
        
        this.Extension = Extension;
        
        this.XConomy = main.getInstance();
        
        this.ATMInterface = ATMInterface;

        this.RemoveAfterTriggered = RemoveAfterTriggered;
        
        Bukkit.getPluginManager().registerEvents( this , XConomy );

    }
 
    //The Event
    @EventHandler( priority = EventPriority.NORMAL )
    public void GUIActionEvent( InventoryClickEvent ev )
    {
        if ( !HandlerList.getRegisteredListeners( XConomy ).contains( this ) )
        {
            if ( ev.getCurrentItem() instanceof ItemStack && ev.getCursor() != null )
            {
                if ( ev.getInventory().getName().equals( ATMInterface.getInventory().getName() ) )
                {

                    if ( ev.getCurrentItem().getItemMeta().getDisplayName().equals( Item.getName() ) )
                    {

                            ev.setCancelled( true );

                            if ( Extension != null )
                            {
                                Extension.UpdateInformations( (Player) ev.getWhoClicked() , ATMInterface, Item );
                                Extension.Run();
                            }
                            
                            if ( RemoveAfterTriggered == true )
                            {
                                HandlerList.unregisterAll( this );
                            }

                    }

               }
            }
        }
    }
    
}
