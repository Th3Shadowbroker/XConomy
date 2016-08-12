package net.th3shadowbroker.XConomy.Defaults.CalcInterface;

import net.th3shadowbroker.XConomy.Cache.CacheState;
import net.th3shadowbroker.XConomy.Defaults.DefaultCalcInterface;
import net.th3shadowbroker.XConomy.Defaults.Transfer.QueuedPlayer;
import net.th3shadowbroker.XConomy.Exceptions.NotEnoughMoneyException;
import net.th3shadowbroker.XConomy.GUI.GUIItemExtension;
import net.th3shadowbroker.XConomy.Objects.DoubleFormatter;
import net.th3shadowbroker.XConomy.Objects.XConomyPlayer;
import net.th3shadowbroker.XConomy.main;

public class OKItemAction extends GUIItemExtension
{

    private final main XConomy = main.getInstance();
    
    @Override
    public void OnConstruct() 
    {
        //Nothing to do here!
    }

    @Override
    public void Run() 
    {
       try{

           DefaultCalcInterface CurrentCalcInterface = (DefaultCalcInterface) CurrentInterface;
           
           //Check type and do the right things
           switch ( CurrentCalcInterface.GetCalcType() )
           {
               
               case DEPOSIT:
                   
                   XConomy.BankManager.Deposit( Player , DoubleFormatter.Format( CurrentCalcInterface.GetAmount() ) );
                   
                break;
                   
               case WITHDRAW:
                   
                   XConomy.BankManager.Withdraw( Player , DoubleFormatter.Format( CurrentCalcInterface.GetAmount() ) );
                   
                break;
                   
               case TRANSFER:
               
                   XConomy.TransferQueue.AddWaiting( new QueuedPlayer( Player, DoubleFormatter.Format( CurrentCalcInterface.GetAmount() ) ) );
                   XConomy.getCache().updateCacheEntry( new XConomyPlayer( Player ) , CacheState.WAIT_TRANSFER_NAME );

                   Player.sendMessage( XConomy.ChatPrefix() + XConomy.lang.getText( "Transfer.TypeTarget" ) );
                   
                break;
           }
           
           Player.closeInventory();
        
       } catch ( NotEnoughMoneyException ex ) {
           
           Player.sendMessage( XConomy.ChatPrefix() + XConomy.lang.getText( "AccountNotEnoughMoneyToOwner" ) );
           
       } catch ( Exception ex ) {
           
           /*
                THIS ONLY HAPPENS IF THERE IS NO VALID INPUT. IN SHORT NOTHING TO DO HERE!
           */
       }
       
    }
    
}
