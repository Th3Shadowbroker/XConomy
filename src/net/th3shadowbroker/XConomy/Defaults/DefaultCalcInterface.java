package net.th3shadowbroker.XConomy.Defaults;

import java.util.ArrayList;
import net.th3shadowbroker.XConomy.Blueprints.ATMInterface;
import net.th3shadowbroker.XConomy.Defaults.CalcInterface.NumberItemAction;
import net.th3shadowbroker.XConomy.GUI.GUIItemAction;
import net.th3shadowbroker.XConomy.GUI.GUIItemStack;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DefaultCalcInterface extends ATMInterface
{

    private final Player player;
    private final main XConomy = main.getInstance();
    private final CalcType CurrentType;
    
    public DefaultCalcInterface( Player player, CalcType type )
    {
    
        switch( type )
        {
            
            case DEPOSIT:
                
            this.OverrideDefaults( XConomy.lang.getText( "ATMItemCustomDeposit" ) , 54 );
            
            /////
            
            case WITHDRAW:
                
            this.OverrideDefaults( XConomy.lang.getText( "ATMItemCustomWithdraw" ) , 54 );
            
            ////
            
            case TRANSFER:
                
            this.OverrideDefaults( XConomy.lang.getText( "ATMItemCustomTransfer" ) , 54 );
            
        }
        
        this.SetupDefaults();
        
        this.player = player;
        
        this.CurrentType = type;
        
    }
    
    public enum CalcType
    {
        DEPOSIT,
        WITHDRAW,
        TRANSFER
    }
    
    @Override
    public void registerActionEvents() 
    {
        //Nothing to do here!
    }
    
    private void SetupDefaults()
    {
        
        ArrayList<GUIItemStack> Elements = new ArrayList<GUIItemStack>();
        
        //Display Line
        GUIItemStack Display = new GUIItemStack( Material.BOOK_AND_QUILL, "---" , null, 1, 4); Elements.add(Display);
        
        //Line one
        GUIItemStack L1I1 = new GUIItemStack( new ItemStack( Material.WOOL, 1,(short) 11 ), "§e" + String.valueOf( 1 ) , null, 12); Elements.add(L1I1);
        GUIItemStack L1I2 = new GUIItemStack( new ItemStack( Material.WOOL, 2,(short) 11 ), "§e" + String.valueOf( 2 ) , null, 13); Elements.add(L1I2);
        GUIItemStack L1I3 = new GUIItemStack( new ItemStack( Material.WOOL, 3,(short) 11 ), "§e" + String.valueOf( 3 ) , null, 14); Elements.add(L1I3);
        
        //Line one actions
        GUIItemAction L1A1 = new GUIItemAction( L1I1, this, new NumberItemAction() );
        GUIItemAction L1A2 = new GUIItemAction( L1I2, this, new NumberItemAction() );
        GUIItemAction L1A3 = new GUIItemAction( L1I3, this, new NumberItemAction() );
        
        //Line two
        GUIItemStack L2I1 = new GUIItemStack( new ItemStack( Material.WOOL, 4,(short) 11 ), "§e" + String.valueOf( 4 ) , null, 21); Elements.add(L2I1);
        GUIItemStack L2I2 = new GUIItemStack( new ItemStack( Material.WOOL, 5,(short) 11 ), "§e" + String.valueOf( 5 ) , null, 22); Elements.add(L2I2);
        GUIItemStack L2I3 = new GUIItemStack( new ItemStack( Material.WOOL, 6,(short) 11 ), "§e" + String.valueOf( 6 ) , null, 23); Elements.add(L2I3);
        
        //Line two actions
        GUIItemAction L2A1 = new GUIItemAction( L2I1, this, new NumberItemAction() );
        GUIItemAction L2A2 = new GUIItemAction( L2I2, this, new NumberItemAction() );
        GUIItemAction L2A3 = new GUIItemAction( L2I3, this, new NumberItemAction() );
        
        //Line three
        GUIItemStack L3I1 = new GUIItemStack( new ItemStack( Material.WOOL, 7,(short) 11 ), "§e" + String.valueOf( 7 ) , null, 30); Elements.add(L3I1);
        GUIItemStack L3I2 = new GUIItemStack( new ItemStack( Material.WOOL, 8,(short) 11 ), "§e" + String.valueOf( 8 ) , null, 31); Elements.add(L3I2);
        GUIItemStack L3I3 = new GUIItemStack( new ItemStack( Material.WOOL, 9,(short) 11 ), "§e" + String.valueOf( 9 ) , null, 32); Elements.add(L3I3);
   
        //Line three actions
        GUIItemAction L3A1 = new GUIItemAction( L3I1, this, new NumberItemAction() );
        GUIItemAction L3A2 = new GUIItemAction( L3I2, this, new NumberItemAction() );
        GUIItemAction L3A3 = new GUIItemAction( L3I3, this, new NumberItemAction() );
        
        
        //Line four / Options line
        /* Add a dot */    GUIItemStack L4I1 = new GUIItemStack( new ItemStack( Material.WOOL, 1,(short) 4 ), "§e.", null, 39); Elements.add(L4I1);
        /* Add a null*/    GUIItemStack L4I2 = new GUIItemStack( new ItemStack( Material.WOOL, 1,(short) 11 ), "§e0", null, 40); Elements.add(L4I2);
        /* Confirm   */    GUIItemStack L4I3 = new GUIItemStack( new ItemStack( Material.WOOL, 1,(short) 13 ), "§2OK", null, 41); Elements.add(L4I3);
        
        //Line four / Options line actions
        /* Add number extension to 0 */     GUIItemAction L4A2 = new GUIItemAction( L4I2, this, new NumberItemAction() );
                
        //Add items to inerface
        this.AddElements( Elements );
        
    }
    
    //Get current calc-type
    public CalcType GetCalcType()
    {
        
        return CurrentType;
        
    }
    
    //Get the current amount
    public double GetAmount()
    {
        
        return Double.parseDouble( this.getInventory().getItem( 4 ).getItemMeta().getDisplayName() );
        
    }
    
    //Update current amount
    public void UpdateAmount( int amount )
    {
        
        ItemStack CurrentAmount = this.getInventory().getItem( 4 );
        String CurrentName = CurrentAmount.getItemMeta().getDisplayName();
        
        if ( CurrentName.equals( "---" ) )
        {
            
            //Creating a new amount-display item
            ItemStack NewAmount = CurrentAmount;
            ItemMeta NewAmountMeta = CurrentAmount.getItemMeta();
            
            NewAmountMeta.setDisplayName( String.valueOf( amount ) );
            NewAmount.setItemMeta( NewAmountMeta );
            
            this.AddElement( new GUIItemStack( NewAmount, NewAmount.getItemMeta().getDisplayName(), null, 4 ) );
            
        } else {
            
            //Creating an updated amount-display item
            ItemStack NewAmount = CurrentAmount;
            ItemMeta NewAmountMeta = CurrentAmount.getItemMeta();
            
            NewAmountMeta.setDisplayName( String.valueOf( NewAmountMeta.getDisplayName() ) + String.valueOf( amount ) );
            NewAmount.setItemMeta( NewAmountMeta );
            
            this.AddElement( new GUIItemStack( NewAmount, NewAmount.getItemMeta().getDisplayName(), null, 4 ) );
            
        }
        
    }
    
}
