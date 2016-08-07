package net.th3shadowbroker.XConomy.Defaults.Transfer;

import net.th3shadowbroker.XConomy.Cache.CacheState;
import net.th3shadowbroker.XConomy.Exceptions.NotEnoughMoneyException;
import net.th3shadowbroker.XConomy.Loaders.Events;
import net.th3shadowbroker.XConomy.Objects.Transaction;
import net.th3shadowbroker.XConomy.Objects.XConomyPlayer;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ArgumentCatcher implements Listener
{
     
    private final Events Loader;
    private final main XConomy;
    
    //Construction
    public ArgumentCatcher( Events loaderClass )
    {
        
        this.Loader = loaderClass;
        
        this.XConomy = loaderClass.loader;

    }
    
    @EventHandler
    public void OnPlayerChat( AsyncPlayerChatEvent ev )
    {
        
        Player Player = ev.getPlayer();
        String Message = ev.getMessage();
        
        try {
            
            //Is player in queue ?
            if ( XConomy.TransferQueue.PlayerIsWaiting( Player ) )
            {
                
                ev.setCancelled( true );
                
                //If player want to cancel the transaction
                if ( Message.equals( "#" ) )
                {
                    XConomy.getCache().updateCacheEntry( new XConomyPlayer( Player ) , CacheState.NORMAL);
                    
                    Player.sendMessage( XConomy.ChatPrefix() + XConomy.lang.getText( "Transfer.CancelByUser" ) );
                    XConomy.TransferQueue.RemoveWaiting( Player );
                    
                    return;
                }
                
                try {
                    
                    OfflinePlayer Target = Bukkit.getOfflinePlayer( Message );
                    
                    QueuedPlayer PlayerInQueue = XConomy.TransferQueue.GetPlayer( Player );
                    
                    Transaction Transaction = new Transaction( Player, Target.getPlayer(),  PlayerInQueue.GetAmount() );
                    
                    Transaction.Transfer();
                    
                    XConomy.getCache().updateCacheEntry( new XConomyPlayer( Player ) , CacheState.NORMAL);
                    XConomy.TransferQueue.RemoveWaiting( Player );
                    
                    Player.sendMessage( XConomy.ChatPrefix() + XConomy.lang.getText( "Transfer.Success" ) );
                    
                } catch ( NotEnoughMoneyException ex ) {    
                
                    Player.sendMessage( XConomy.ChatPrefix() + XConomy.lang.getText( "AccountNotEnoughMoneyToOwner" ) );
                    
                    XConomy.getCache().updateCacheEntry( new XConomyPlayer( Player ) , CacheState.NORMAL);
                    XConomy.TransferQueue.RemoveWaiting( Player );

                } catch ( Exception ex ) {
                    
                    Player.sendMessage( XConomy.ChatPrefix() + XConomy.lang.getText( "Transfer.TargetNotFound" ) );
                    XConomy.Console.write( ex.getMessage() );
  
                }
                
            }
            
        } catch ( Exception ex ) {
            
            ex.printStackTrace();
            
        }
    }
    
}
