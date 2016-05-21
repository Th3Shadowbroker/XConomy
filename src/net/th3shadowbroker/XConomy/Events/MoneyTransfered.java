package net.th3shadowbroker.XConomy.Events;

import net.th3shadowbroker.XConomy.API.MoneyTransferEvent;
import net.th3shadowbroker.XConomy.Loaders.Events;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MoneyTransfered implements Listener {
    
    private final main loader;
    
    //Construction
    public MoneyTransfered( Events loaderClass )
    {
        
        this.loader = loaderClass.loader;
        
    }
    
    @EventHandler
    public void onMoneyTransfer( MoneyTransferEvent e )
    {
        if ( loader.Config.getBoolean( "Messages.MessageOnTransfer" )  == true )
        {
            
            e.getSender().sendMessage( loader.ChatPrefix() + loader.lang.getText( "MoneyTransferToSender" ).replaceAll( "%AMOUNT%" , String.valueOf( e.getAmount() )
                                                                                                           .replaceAll( "%SHORTNAME%" , loader.Config.getString( "Currency.Shortname" ) )) );
            
            e.getTarget().sendMessage( loader.ChatPrefix() + loader.lang.getText( "MoneyTransferToTarget" ).replaceAll( "%AMOUNT%" , String.valueOf( e.getAmount() )
                                                                                                           .replaceAll( "%SHORTNAME%" , loader.Config.getString( "Currency.Shortname" ) )) );
            
        }
    }
    
}
