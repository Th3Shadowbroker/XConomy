package net.th3shadowbroker.XConomy.Defaults.Transfer;

import java.util.ArrayList;

import net.th3shadowbroker.XConomy.main;
import org.bukkit.entity.Player;

public class TransferQueue 
{
    
    private ArrayList<QueuedPlayer> WaitingPlayers = new ArrayList<QueuedPlayer>();
    private main XConomy = main.getInstance();
    
    //Add a new player who's waiting for money transfer
    public void AddWaiting( QueuedPlayer player )
    {
        if ( !WaitingPlayers.contains( player ) )
        {
            WaitingPlayers.add( player );
        }
    }
    
    //Remove a player if everything is done
    public void RemoveWaiting( Player player )
    {
        for ( QueuedPlayer qplayer : WaitingPlayers )
        {
            if ( qplayer.GetPlayer().equals( player ) )
            {
                WaitingPlayers.remove( qplayer );
                return;
            }
        }
    }
    
    //Get a waiting player from queue
    public QueuedPlayer GetPlayer( Player player )
    {
        for ( QueuedPlayer qplayer : WaitingPlayers )
        {
            if ( qplayer.GetPlayer().equals( player ) )
            {
                return qplayer;
            }
        }
        return null;
    }
    
    //Check if a player is already in queue
    public boolean PlayerIsWaiting( Player player )
    {
        for ( QueuedPlayer qplayer : WaitingPlayers )
        {
            if ( qplayer.GetPlayer().equals( player ) )
            {
               return true;
            }
        }
        
        return false;
        
    }
    
}
