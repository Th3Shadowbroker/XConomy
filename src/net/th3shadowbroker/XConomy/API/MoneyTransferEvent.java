package net.th3shadowbroker.XConomy.API;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class MoneyTransferEvent extends Event {

    private final Player sender;
    private final Player target;
    private final double amount;

    //Construction
    public MoneyTransferEvent( Player sender , Player target , double amount )
    {
        
        this.sender = sender;
        this.target = target;
        this.amount = amount;
        
    }
    
    //Get sender-player
    public Player getSender()
    {
        
        return sender;
        
    }
    
    //Get target-player
    public Player getTarget()
    {
        
        return target;
        
    }
    
    //Get amount
    public double getAmount()
    {
        
        return amount;
        
    }
    
    //Get handlers (not required)
    @Override
    public HandlerList getHandlers() 
    {
      return new HandlerList();
    }
    
    public static HandlerList getHandlerList() {
            return new HandlerList();
    }
    
}
