package net.th3shadowbroker.XConomy.API;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class AccountCreatedEvent extends Event {

    private final Player player;

    //Construction
    public AccountCreatedEvent( Player player )
    {
        
        this.player = player;

    }
    
    //Get the player who did this
    public Player getPlayer()
    {
        
        return player;
        
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
