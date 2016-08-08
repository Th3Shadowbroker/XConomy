package net.th3shadowbroker.XConomy.Objects;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

@Deprecated
public class Plugins {
    
    public static boolean VaultIsEnabled()
    {
        try {
            
            Plugin Vault = Bukkit.getPluginManager().getPlugin( "Vault" );
            
            if ( Bukkit.getServer().getPluginManager().isPluginEnabled( Vault ) )
            {
                
                return true;
            
            } else {
                
                Bukkit.getServer().getPluginManager().enablePlugin( Vault );
                return true;
                
            }
            
        } catch ( Exception ex ) {
            
            return false;
            
        }
    }
    
}
