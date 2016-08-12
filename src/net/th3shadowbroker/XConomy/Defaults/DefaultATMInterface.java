package net.th3shadowbroker.XConomy.Defaults;

import java.util.ArrayList;
import net.th3shadowbroker.XConomy.ATM.Extensions.DefaultAutoDeposit;
import net.th3shadowbroker.XConomy.ATM.Extensions.DefaultAutoTransfer;
import net.th3shadowbroker.XConomy.ATM.Extensions.DefaultAutoWithdraw;
import net.th3shadowbroker.XConomy.ATM.Extensions.DefaultCustomDeposit;
import net.th3shadowbroker.XConomy.ATM.Extensions.DefaultCustomTransfer;
import net.th3shadowbroker.XConomy.ATM.Extensions.DefaultCustomWithdraw;
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
            //GUIItemAction DepositAction = new GUIItemAction( DepositItem, this, null );

            GUIItemStack CustomDepositItem = new GUIItemStack( Material.FEATHER, XConomy.lang.getText( "ATMItemCustomDeposit" ), null , 1, 8 ); Elements.add( CustomDepositItem );
            GUIItemAction CustomDepositAction = new GUIItemAction( CustomDepositItem, this, new DefaultCustomDeposit() );

            int DSCounter = XConomy.Config.getInt( "ATM.StartAt" );

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

                DSCounter += XConomy.Config.getInt( "ATM.StepSize" );

            }
            
            
            
            /*
             *           WITHDRAW-ITEM
             */
            
            //Decleration of the Withdraw Items
            GUIItemStack WithdrawItem = new GUIItemStack( Material.PAPER, XConomy.lang.getText( "ATMItemWithdraw" ), null , 1, 18 ); Elements.add( WithdrawItem );
            //GUIItemAction WithdrawAction = new GUIItemAction( WithdrawItem, this, null );

            GUIItemStack CustomWithdrawItem = new GUIItemStack( Material.FEATHER, XConomy.lang.getText( "ATMItemCustomWithdraw" ), null , 1, 26 ); Elements.add( CustomWithdrawItem );
            GUIItemAction CustomWithdrawAction = new GUIItemAction( CustomWithdrawItem, this, new DefaultCustomWithdraw() );

            int WDCounter = XConomy.Config.getInt( "ATM.StartAt" );;

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

                WDCounter += XConomy.Config.getInt( "ATM.StepSize" );
  
            }
            
            /*
             *           Transfer-ITEM
             */
            
            //Decleration of the Transfer Items
            GUIItemStack TransferItem = new GUIItemStack( Material.PAPER, XConomy.lang.getText( "ATMItemTransfer" ), null , 1, 36 ); Elements.add( TransferItem );
            //GUIItemAction TransferAction = new GUIItemAction( TransferItem, this, null );

            GUIItemStack CustomTransferItem = new GUIItemStack( Material.FEATHER, XConomy.lang.getText( "ATMItemCustomTransfer" ), null , 1, 44 ); Elements.add( CustomTransferItem );
            GUIItemAction CustomTransferAction = new GUIItemAction( CustomTransferItem, this, new DefaultCustomTransfer() );

            int TDCounter = XConomy.Config.getInt( "ATM.StartAt" );

            for ( int i = 38; i != 44; i++ )
            {

                //Check if slot is already occupied
                for ( GUIItemStack CurrentTransferItems : Elements )
                {
                    if ( CurrentTransferItems.getPos() == i )
                    {

                        throw new NullPointerException( "This error is currently not implemented." );

                    }
                }

                //Everything beyond this line will be launched if everything is ok
                GUIItemStack AutoTransferItem = new GUIItemStack( Material.BOOK, XConomy.lang.getText("ATMItemTransfer") + ": " + String.valueOf( TDCounter ), null, 1, i ); Elements.add( AutoTransferItem );
                GUIItemAction AutoTransferAction = new GUIItemAction( AutoTransferItem, this, new DefaultAutoTransfer() );

                TDCounter += XConomy.Config.getInt( "ATM.StepSize" );
  
            }

        
        //At the end we add all items to our brand new interface
        this.AddElements( Elements );
        
    }
    
}
