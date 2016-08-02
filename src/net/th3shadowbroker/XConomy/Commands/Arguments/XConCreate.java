package net.th3shadowbroker.XConomy.Commands.Arguments;

import net.th3shadowbroker.XConomy.Blueprints.CommandArgument;
import net.th3shadowbroker.XConomy.Cache.CacheState;
import net.th3shadowbroker.XConomy.Objects.XConomyPlayer;

public class XConCreate extends CommandArgument {

    @Override
    public void RunAction() {
  
            try {
                
                if ( arguments.length != 1 )
                {
                   
                        if (arguments[1].equalsIgnoreCase( "bank" ) )
                        {

                            if ( plugin.getCache().getCacheEntry( new XConomyPlayer( player ) ).getState() == CacheState.NORMAL )
                            {

                                plugin.getCache().updateCacheEntry( new XConomyPlayer( player ), CacheState.WAIT_CREATE_BANK );
                                player.sendMessage( plugin.ChatPrefix() + "§2Rigt-click on the bank you want do create" );

                            } else {

                                plugin.getCache().updateCacheEntry( new XConomyPlayer( player ), CacheState.NORMAL );
                                player.sendMessage( plugin.ChatPrefix() + "§cBank creation cancelled" );

                            }

                        } else {

                        player.sendMessage( plugin.ChatPrefix() + "§cPlease use §9/xcon create <bank>" );

                        }
                        
                } else {
                    
                    player.sendMessage( plugin.ChatPrefix() + "§cPlease use §9/xcon create <bank>" );
                    
                }
                
            } catch ( Exception ex ) {
                
                player.sendMessage( plugin.ChatPrefix() + "§cThere was a cahing error. See console." );
                
                ex.printStackTrace();
                
            }
    }
    
}
