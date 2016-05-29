package net.th3shadowbroker.XConomy.Events;

import java.io.File;
import net.th3shadowbroker.XConomy.API.AccountCreatedEvent;
import net.th3shadowbroker.XConomy.Loaders.Events;
import net.th3shadowbroker.XConomy.Objects.Account;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class CreateAccountOnJoin implements Listener {

    private final Events loader;
    private final main plugin;
    
    //Construction
    public CreateAccountOnJoin( Events loaderClass )
    {
        
        this.loader = loaderClass;
        this.plugin = loaderClass.loader;
        
    }
    
    @EventHandler( priority = EventPriority.HIGHEST )
    public void onPlayerJoin( PlayerJoinEvent e )
    {
        if ( plugin.Config.getBoolean( "Actions.CreateAccountOnJoin" ) == true && Account.accountExists( new File( plugin.getDataFolder(), "accounts.yml" ), e.getPlayer().getUniqueId() ) == false )
        {
            try {
                
                Account account = new Account( plugin, e.getPlayer() );
                
                if ( plugin.lang.getText( "AccountCreated" ) != null )
                {
                    
                    e.getPlayer().sendMessage( plugin.ChatPrefix() + plugin.lang.getText( "AccountCreated" ).replaceAll( "%PLAYER%" , e.getPlayer().getName() ) );
                    
                }
                
                Bukkit.getServer().getPluginManager().callEvent( new AccountCreatedEvent( e.getPlayer() ) );
                
            } catch ( Exception ex ) {
                
                ex.printStackTrace();
                
            }
        }
    }
    
}
