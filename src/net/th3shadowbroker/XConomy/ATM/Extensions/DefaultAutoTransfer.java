package net.th3shadowbroker.XConomy.ATM.Extensions;

import net.th3shadowbroker.XConomy.Cache.CacheState;
import net.th3shadowbroker.XConomy.Defaults.Transfer.QueuedPlayer;
import net.th3shadowbroker.XConomy.Exceptions.NotInCacheException;
import net.th3shadowbroker.XConomy.GUI.GUIItemExtension;
import net.th3shadowbroker.XConomy.Objects.XConomyPlayer;
import net.th3shadowbroker.XConomy.main;

public class DefaultAutoTransfer extends GUIItemExtension
{

    private main XConomy;
    
    @Override
    public void OnConstruct() 
    {
        
        this.XConomy = main.getInstance();
        
    }

    @Override
    public void Run() 
    {
        if ( !XConomy.TransferQueue.PlayerIsWaiting( Player ) )
        {
            
            String UnfilteredName =  XConomy.lang.getText("ATMItemTransfer") + ": ";
            String FilteredName = CurrentItem.getName().replaceAll( UnfilteredName , "");

            try{

                XConomy.TransferQueue.AddWaiting( new QueuedPlayer( Player, Double.parseDouble( FilteredName ) ) );
                XConomy.getCache().updateCacheEntry( new XConomyPlayer( Player ) , CacheState.WAIT_TRANSFER_NAME );

                Player.sendMessage( XConomy.ChatPrefix() + XConomy.lang.getText( "Transfer.TypeTarget" ) );

            } catch ( NotInCacheException ex ) {

                Player.sendMessage( XConomy.ChatPrefix() + XConomy.lang.getText( "SystemUniErrMsg" ) );
                ex.printStackTrace();

            }
            
        } else {
            
            Player.sendMessage( XConomy.ChatPrefix() + XConomy.lang.getText( "Transfer.CancelMessage" ) );
            
        }
        
        Player.closeInventory();
        
    }
    
}
