package net.th3shadowbroker.XConomy.Cache.Events;

import net.th3shadowbroker.XConomy.Cache.PlayerCache;
import net.th3shadowbroker.XConomy.Exceptions.NotInCacheException;
import net.th3shadowbroker.XConomy.Loaders.Events;
import net.th3shadowbroker.XConomy.Objects.XConomyPlayer;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class RemoveEntryOnQuit implements Listener {
    
    private final Events loader;
    private final main plugin;
    
    //Construction
    public RemoveEntryOnQuit( Events loaderClass )
    {
        
        this.loader = loaderClass;
        
        this.plugin = loaderClass.loader;
        
    }
    
    @EventHandler( priority = EventPriority.MONITOR )
    public void RemoveCacheEntryOnQuit( PlayerQuitEvent ev )
    {
        
        Player player = ev.getPlayer();
        
        PlayerCache cache = plugin.getCache();
        
        try {
            
            cache.removeCacheEntry( new XConomyPlayer( player ) );
            
        } catch (NotInCacheException ex) {
            
            System.out.println( plugin.ConsolePrefix() + "Error while caching:" );
            ex.printStackTrace();
            
        }
        
    }
    
}
