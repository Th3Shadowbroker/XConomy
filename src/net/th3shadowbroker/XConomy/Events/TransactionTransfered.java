package net.th3shadowbroker.XConomy.Events;

import net.th3shadowbroker.XConomy.API.MoneyTransferEvent;
import net.th3shadowbroker.XConomy.Loaders.Events;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class TransactionTransfered implements Listener 
{
    
    private final Events loader;
    private final main plugin;
        
    public TransactionTransfered( Events loaderClass )
    {
        this.loader = loaderClass;
        this.plugin = loaderClass.loader;
    }
    
    @EventHandler
    public void OnTransactionTransfered( MoneyTransferEvent ev )
    {
        plugin.Console.write( "FUCK YEAH!" );
    }
    
}
