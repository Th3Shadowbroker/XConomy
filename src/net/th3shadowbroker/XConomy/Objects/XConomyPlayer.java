package net.th3shadowbroker.XConomy.Objects;

import java.util.UUID;
import net.th3shadowbroker.XConomy.Cache.CacheState;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.entity.Player;

public class XConomyPlayer {
    
    private final Player player;
    private final Account account;
    private final UUID uid;
    private CacheState state;
    
    //Construction
    public XConomyPlayer( Player player )
    {
        
        this.player = player;
        
        this.account = new Account( main.getInstance() , player );
        
        this.uid = player.getUniqueId();
        
        this.state = CacheState.NORMAL;
        
    }
        
    //Get the player
    public Player getPlayer()
    {
        return player;
    }
    
    //Get the current players account
    public Account getAccount()
    {
        return account;
    }
    
    //Get the current players uuid
    public UUID getUUID()
    {
        return uid;
    }
    
    //Get the current state
    public CacheState getState()
    {
        return state;
    }

    //Set the current state to another
    public void setState( CacheState newState )
    {
        this.state = newState;
    }
}
