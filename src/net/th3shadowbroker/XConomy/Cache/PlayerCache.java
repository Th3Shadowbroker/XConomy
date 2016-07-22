package net.th3shadowbroker.XConomy.Cache;

import java.util.ArrayList;
import java.util.List;
import net.th3shadowbroker.XConomy.Exceptions.AlreadyInCacheException;
import net.th3shadowbroker.XConomy.Exceptions.NotInCacheException;
import net.th3shadowbroker.XConomy.Objects.XConomyPlayer;

public class PlayerCache 
{
    
    private List<XConomyPlayer> CachedPlayers = new ArrayList<XConomyPlayer>();
    
    //Construction
    public PlayerCache()
    {
        /*
         *      Nothing to do here !
         */
    }
    
    //Add cache-entry
    public void addCacheEntry( XConomyPlayer entry ) throws AlreadyInCacheException
    {
        if ( !CachedPlayers.contains( entry ) )
        {
            
            CachedPlayers.add( entry ); System.out.println( "[AncientCore] " + entry.getUUID() );
            
        }   else    {
            
            throw new AlreadyInCacheException();
            
        }
    }
    
    //Remove cache-entry
    public void removeCacheEntry( XConomyPlayer entry ) throws NotInCacheException
    {
 
            for ( XConomyPlayer player : CachedPlayers )
            {
                if ( entry.getUUID().equals( player.getUUID() ) )
                {
                    
                    CachedPlayers.remove( player ); System.out.println( "[AncientCore] " + entry.getUUID() );
                    
                    return;
                    
                }
            }

            throw new NotInCacheException();

    }
    
    //Update an existing entry
    public void updateCacheEntry( XConomyPlayer entry , CacheState newState ) throws NotInCacheException
    {
        
        for ( XConomyPlayer player : CachedPlayers )
        {
            if ( entry.getUUID().equals( player.getUUID() ) )
            {
                
                player.setState( newState );
                return;
                
            }
        }
        
        throw new NotInCacheException();
        
    }
    
    //Cache entry exists
    public boolean cacheEntryExists( XConomyPlayer entry )
    {
        
        return CachedPlayers.contains( entry );
        
    }
    
    //Get cache-entry
    public XConomyPlayer getCacheEntry( XConomyPlayer entry )
    {
        
        for ( XConomyPlayer player : CachedPlayers )
        {
            if ( player.getUUID().equals( entry.getUUID() ) )
            {
                
                return player;
                
            }
        }
        
        return null;
        
    }
    
    //Get the whole cache
    public ArrayList<XConomyPlayer> getCache()
    {   
        return new ArrayList<XConomyPlayer>( CachedPlayers );  
    }
    
    //Get all players with specific state
    public ArrayList<XConomyPlayer> getPlayersWithState( CacheState state )
    {
        
        ArrayList<XConomyPlayer> foundPlayers = new ArrayList<XConomyPlayer>();
        
        for ( XConomyPlayer player : CachedPlayers)
        {
            if ( player.getState().equals( state ) )
            {
                foundPlayers.add( player );
            }  
        }
        
        return foundPlayers;
        
    }
    
}

