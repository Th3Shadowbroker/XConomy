package net.th3shadowbroker.XConomy.ATM.Events;

import net.th3shadowbroker.XConomy.Blueprints.ATMInterface;
import net.th3shadowbroker.XConomy.Loaders.Events;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;

public class BlockATMBlockDestruction implements Listener {
    
    private final main plugin;
    private final Events loader;
    
    //Construction
    public BlockATMBlockDestruction( Events loaderClass )
    {
        
        this.plugin = loaderClass.loader;
        
        this.loader = loaderClass;

    }
    
    //The Events
    @EventHandler
    public void BlockATMBlockDestroy( BlockBreakEvent ev )
    {
        
        Player p = ev.getPlayer();
        Block clickedBlock = ev.getBlock();
        Location cBlock = new Location( clickedBlock.getWorld(), clickedBlock.getX(), clickedBlock.getY(), clickedBlock.getZ() );
        
        if ( plugin.ATMConfig.ATMExists( cBlock ) )
        {
            
            p.sendMessage( plugin.ChatPrefix() + plugin.lang.getText( "SystemATMBlockDestruction" ) );
            ev.setCancelled( true );
            
        }
        
    }
    
    @EventHandler
    public void BlockATMExplosion( BlockExplodeEvent ev )
    {

        for( Block ExplodingBlock : ev.blockList() )
        {
            
            Block clickedBlock = ExplodingBlock;
            Location cBlock = new Location( clickedBlock.getWorld(), clickedBlock.getX(), clickedBlock.getY(), clickedBlock.getZ() );

            if ( plugin.ATMConfig.ATMExists( cBlock ) )
            {

                ev.setCancelled( true );

            }
            
        }
        
    }
    
}
