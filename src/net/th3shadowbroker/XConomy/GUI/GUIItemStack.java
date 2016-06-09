package net.th3shadowbroker.XConomy.GUI;

import java.util.List;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUIItemStack {
    
    private final Material material;
    private final String displayName;
    private final List<String> lore;
    private final int amount;
    private final int position;
    
    private final ItemStack item; 
    private final ItemMeta meta;
    
    //Construction with new information
    public GUIItemStack( Material material, String displayName, List<String> lore, int amount, int position )
    {
        
        //Set properties
        this.material = material;
        this.displayName = displayName;
        this.lore = lore;
        this.amount = amount;
        this.position = position;
        
        //Create item and item's meta
        this.item = new ItemStack( material, amount );
        this.meta = item.getItemMeta();
        
        //Set display-name and lore if available
        this.meta.setDisplayName( this.displayName );
        
        if ( lore != null )
        {
            this.meta.setLore( lore );
        }
        
        //Fianlly set the meta
        this.item.setItemMeta( this.meta );
        
    }
    
    //Get items name
    public String getName()
    {
        return this.displayName;
    }
    
    //Get item amount
    public int getAmount()
    {
        return this.amount;
    }
    
    //Get items material
    public Material getMaterial()
    {
        return this.material;
    }
    
    //Get items position
    public int getPos()
    {
        return this.position;
    }
    
    //Get items lore
    public List<String> getLore()
    {
        return this.lore;
    }
    
    //Get the whole item as ItemStack
    public ItemStack getItemStack()
    {
        return this.item;
    }
    
    //Get items meta
    public ItemMeta getMeta()
    {
        return this.meta;
    }
    
}
