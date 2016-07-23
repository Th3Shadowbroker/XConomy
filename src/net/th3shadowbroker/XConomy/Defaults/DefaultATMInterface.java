package net.th3shadowbroker.XConomy.Defaults;

import java.util.ArrayList;
import net.th3shadowbroker.XConomy.ATM.Extensions.DefaultAutoDeposit;
import net.th3shadowbroker.XConomy.Blueprints.ATMInterface;
import net.th3shadowbroker.XConomy.GUI.GUIItemAction;
import net.th3shadowbroker.XConomy.GUI.GUIItemStack;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class DefaultATMInterface extends ATMInterface
{

    private final Player player;
    
    public DefaultATMInterface( Player player )
    {
        
        //Override default size
        this.SetSize( 45 );
        
        //Setup defaults
        this.SetupDefaults();
        
        this.player = player;
 
    }
    
    @Override
    public void registerActionEvents() {
        //Currently nothing to do here
    }
    
    private void SetupDefaults()
    {
        
        //An array-list to store our items
        ArrayList<GUIItemStack> Elements = new ArrayList<GUIItemStack>();
        
        //Decleration of the Withdraw Items
        GUIItemStack DepositItem = new GUIItemStack( Material.PAPER, XConomy.lang.getText( "ATMItemDeposit" ), null , 1, 0 ); Elements.add( DepositItem );
        GUIItemAction DepositAction = new GUIItemAction( DepositItem, this, new DefaultAutoDeposit() );
        
        GUIItemStack CustomDepositItem = new GUIItemStack( Material.FEATHER, XConomy.lang.getText( "ATMItemCustomDeposit" ), null , 1, 8 ); Elements.add( CustomDepositItem );
        GUIItemAction CustomDepositAction = new GUIItemAction( DepositItem, this, null );
        
        int Counter = 5;
        
        for ( int i = 2; i != 8; i++ )
        {

            //Check if slot is already occupied
            for ( GUIItemStack CurrentDepositItems : Elements )
            {
                if ( CurrentDepositItems.getPos() == i )
                {
                    
                    throw new NullPointerException( "This error is currently not implemented." );
                    
                }
            }
            
            //Everything beyond this line will be launched if everything is ok
            GUIItemStack AutoDepositItem = new GUIItemStack( Material.EMERALD, XConomy.lang.getText("ATMItemDeposit") + ": " + String.valueOf( Counter ), null, 1, i ); Elements.add( AutoDepositItem );
            GUIItemAction AutoDepositAction = new GUIItemAction( AutoDepositItem, this, null );
            
            if ( Counter == 5 )
            {
                
                Counter = 25;
                
            } else {
                
                Counter += 25;
                        
            }
  
        }

        
        //At the end we add all items to our brand new interface
        this.AddElements( Elements );
        
    }
    
}
