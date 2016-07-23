package net.th3shadowbroker.XConomy.Defaults;

import java.util.ArrayList;
import net.th3shadowbroker.XConomy.ATM.Extensions.DefaultAutoDeposit;
import net.th3shadowbroker.XConomy.ATM.Extensions.DefaultAutoWithdraw;
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
    
    public void SetupDefaults()
    {

        //An array-list to store our items
        ArrayList<GUIItemStack> Elements = new ArrayList<GUIItemStack>();
        
            /*
             *                  DEPOSIT-ITEM
             */
        
            //Decleration of the Withdraw Items
            GUIItemStack DepositItem = new GUIItemStack( Material.PAPER, XConomy.lang.getText( "ATMItemDeposit" ), null , 1, 0 ); Elements.add( DepositItem );
            GUIItemAction DepositAction = new GUIItemAction( DepositItem, this, null );

            GUIItemStack CustomDepositItem = new GUIItemStack( Material.FEATHER, XConomy.lang.getText( "ATMItemCustomDeposit" ), null , 1, 8 ); Elements.add( CustomDepositItem );
            GUIItemAction CustomDepositAction = new GUIItemAction( DepositItem, this, null );

            int DSCounter = 5;

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
                GUIItemStack AutoDepositItem = new GUIItemStack( Material.EMERALD, XConomy.lang.getText("ATMItemDeposit") + ": " + String.valueOf( DSCounter ), null, 1, i ); Elements.add( AutoDepositItem );
                GUIItemAction AutoDepositAction = new GUIItemAction( AutoDepositItem, this, new DefaultAutoDeposit() );

                if ( DSCounter == 5 )
                {

                    DSCounter = 25;

                } else {

                    DSCounter += 25;

                }

            }
            
            
            
            /*
             *           WITHDRAW-ITEM
             */
            
            //Decleration of the Withdraw Items
            GUIItemStack WithdrawItem = new GUIItemStack( Material.PAPER, XConomy.lang.getText( "ATMItemWithdraw" ), null , 1, 18 ); Elements.add( WithdrawItem );
            GUIItemAction WithdrawAction = new GUIItemAction( WithdrawItem, this, null );

            GUIItemStack CustomWithdrawItem = new GUIItemStack( Material.FEATHER, XConomy.lang.getText( "ATMItemCustomWithdraw" ), null , 1, 26 ); Elements.add( CustomWithdrawItem );
            GUIItemAction CustomWithdrawAction = new GUIItemAction( WithdrawItem, this, null );

            int WDCounter = 5;

            for ( int i = 20; i != 26; i++ )
            {

                //Check if slot is already occupied
                for ( GUIItemStack CurrentWithdrawItems : Elements )
                {
                    if ( CurrentWithdrawItems.getPos() == i )
                    {

                        throw new NullPointerException( "This error is currently not implemented." );

                    }
                }

                //Everything beyond this line will be launched if everything is ok
                GUIItemStack AutoWithdrawItem = new GUIItemStack( Material.REDSTONE, XConomy.lang.getText("ATMItemWithdraw") + ": " + String.valueOf( WDCounter ), null, 1, i ); Elements.add( AutoWithdrawItem );
                GUIItemAction AutoWithdrawAction = new GUIItemAction( AutoWithdrawItem, this, new DefaultAutoWithdraw() );

                if ( WDCounter == 5 )
                {

                    WDCounter = 25;

                } else {

                    WDCounter += 25;

                }

            }

        
        //At the end we add all items to our brand new interface
        this.AddElements( Elements );
        
    }
    
}
