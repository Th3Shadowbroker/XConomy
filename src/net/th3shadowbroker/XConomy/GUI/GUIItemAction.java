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
import org.bukkit.plugin.RegisteredListener;

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

        if ( this.IsUnique() )
        {
            Bukkit.getPluginManager().registerEvents( this , XConomy );
        }

    }
    
    //An construction option to prevent Item removal after triggered
    public GUIItemAction( GUIItemStack TriggerItem, ATMInterface ATMInterface, GUIItemExtension Extension, boolean RemoveAfterTriggered )
    {
        
        this.Item = TriggerItem;
        
        this.Extension = Extension;
        
        this.XConomy = main.getInstance();
        
        this.ATMInterface = ATMInterface;

        this.RemoveAfterTriggered = RemoveAfterTriggered;
        
        if ( this.IsUnique() )
        {
            Bukkit.getPluginManager().registerEvents( this , XConomy );
        }

    }
    
    //Check for duplicate (self-destruct if true)
    private boolean IsUnique()
    {
         
         for ( RegisteredListener listener : HandlerList.getRegisteredListeners( XConomy ) )
         {
            if ( listener.getListener().equals( this ) )
            {
                
                    //Check for developer-setting
                    if ( XConomy.Config.getString( "DebugMode" ) != null )
                    {
                        if ( XConomy.Config.getBoolean( "DebugMode" ) )
                        {
                            XConomy.Console.write( "DEBUG//EVENT//HANDLED_EXCEPTION> GUI-Action " + Item.getName() + " already exist. Registration cancelled." );
                        }
                    }
                
                return  false;
                
            }
         }

                    //Check for developer-setting
                    if ( XConomy.Config.getString( "DebugMode" ) != null )
                    {
                        if ( XConomy.Config.getBoolean( "DebugMode" ) )
                        {
                            XConomy.Console.write( "DEBUG//EVENT//HANDLED_EXCEPTION> GUI-Action " + Item.getName() + " does not exist. Registration successfull." );
                        }
                    }
        
        return true;
                             
    }
 
    //The Event
    @EventHandler( priority = EventPriority.NORMAL )
    public void GUIActionEvent( InventoryClickEvent ev )
    {
        if ( ATMInterface.getInventory().equals( ev.getInventory() ) )
        {
            if ( ev.getCurrentItem() != null && ev.getCurrentItem().equals( Item.getItemStack() ) )
            {
                //Check for valid extension (valid is equal to != null)
                if ( Extension != null )
                {
                    Extension.UpdateInformations( (Player) ev.getWhoClicked() , ATMInterface, Item );
                    Extension.OnConstruct();
                    Extension.Run();
                }
                
                //Dispose listener after triggered once
                if ( RemoveAfterTriggered )
                {
                    for ( RegisteredListener listener : ev.getHandlers().getRegisteredListeners() )
                    {
                        if ( listener.getListener().equals( this ) )
                        {
                            ev.getHandlers().unregister( listener );
                        }
                    }
                }
                
                //Check for developer-setting
                if ( XConomy.Config.getString( "DebugMode" ) != null )
                {
                    if ( XConomy.Config.getBoolean( "DebugMode" ) )
                    {
                        XConomy.Console.write( "DEBUG//EVENT> " + Item.getName() );
                    }
                }
                    
            }
        }
    }
    
}
