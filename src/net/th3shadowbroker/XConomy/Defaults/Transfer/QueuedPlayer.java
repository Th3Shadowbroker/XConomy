package net.th3shadowbroker.XConomy.Defaults.Transfer;

import org.bukkit.entity.Player;

public class QueuedPlayer 
{
    
    private final Player Player;
    private final double Amount;
    
    //Construction
    public QueuedPlayer( Player player, double amount )
    {
        this.Player = player;
        this.Amount = amount;
    }
    
    //Get queued player
    public Player GetPlayer()
    {
        return Player;
    }
    
    //Get queued player's amount
    public double GetAmount()
    {
        return Amount;
    }
    
}
