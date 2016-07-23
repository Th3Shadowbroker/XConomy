package net.th3shadowbroker.XConomy.Blueprints;

import java.util.ArrayList;
import net.th3shadowbroker.XConomy.GUI.GUIItemBlocker;
import net.th3shadowbroker.XConomy.GUI.GUIItemStack;
import net.th3shadowbroker.XConomy.Loaders.Events;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class ATMInterface 
{
    
    public Inventory Interface = Bukkit.createInventory( null , 9, "§2ATM" );
    
    private final ATMInterface InterfaceAsATM;
    
    private ItemStack CustomSpacer = null;
    
    public final main XConomy;
    
    //Classic constructor
    public ATMInterface()
    {
        
        this.XConomy = main.getInstance();
        
        this.InterfaceAsATM = this;
        
    }
    
    //Constructor with custom menu-size
    public ATMInterface( int CustomSize )
    {
        
        this.Interface = Bukkit.createInventory( null , CustomSize , "§2ATM" );
        
        this.XConomy = main.getInstance();
        
        this.InterfaceAsATM = this;
        
    }

    //Add a single element
    public void AddElement( GUIItemStack Element )
    {
        
        Interface.setItem( Element.getPos() , Element.getItemStack() );
        
    }
    
    //Add a whole list of Items
    public void AddElements( ArrayList<GUIItemStack> Elements )
    {
        for ( GUIItemStack Element : Elements )
        {
            
            Interface.setItem( Element.getPos() , Element.getItemStack() );
            
        }
    }
    
    //Open Interface to player
    public void OpenTo( Player target, boolean fillEmpty )
    {
        
        target.openInventory( Interface );
        
        if ( fillEmpty == true )
        {
            this.FillEmptySlots();
        }
        
    }
    
    //Switch to another menu
    public void SwitchTo( Player target, ATMInterface newInterface, boolean fillEmpty )
    {
        
        target.closeInventory();
        
        newInterface.OpenTo( target , fillEmpty );
        
    }
    
    //Get current inventory
    public Inventory getInventory()
    {
        
        return Interface;
        
    }
    
    //Get this class
    public ATMInterface GetATMInterface()
    {
        
        return InterfaceAsATM;
        
    }
    
    //Override default-size
    public void SetSize( int size )
    {
        
        this.Interface = Bukkit.createInventory( null , size, "§2ATM" );
        
    }
    
    //Set custom spacer
    public void SetCustomSpacer( ItemStack Spacer )
    {
        
        this.CustomSpacer = Spacer;
        
    }
    
    //Fill all empty slots with spacer-items
    private void FillEmptySlots()
    {
        for ( int i = 0; i != Interface.getSize(); i++ )
        {
            if ( Interface.getItem( i ) == null )
            {
                
                ItemStack spacer;
                
                if ( this.CustomSpacer == null )
                {
                    
                    spacer = new ItemStack( Material.STAINED_GLASS_PANE , 1 , (short) 3 );
                    
                } else {
                    
                    spacer = this.CustomSpacer;
                    
                }
                 
                ItemMeta spacerMeta = spacer.getItemMeta();
                 
                spacerMeta.setDisplayName( main.getInstance().ChatPrefix() );
                spacer.setItemMeta( spacerMeta );

                Interface.setItem( i , spacer );
                
                GUIItemStack spacerForEvent = new GUIItemStack( spacer.getType(), spacer.getItemMeta().getDisplayName(), null, 1, 0);
                
                GUIItemBlocker spacerBlocker = new GUIItemBlocker( Events.getLoaderInstance(), this );
                
            }
        }
    }
    
    //Register all the required events
    public abstract void registerActionEvents();
    
}
