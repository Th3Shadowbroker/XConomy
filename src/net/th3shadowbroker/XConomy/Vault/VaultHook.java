package net.th3shadowbroker.XConomy.Vault;

import net.milkbowl.vault.Vault;
import net.milkbowl.vault.economy.Economy;
import net.th3shadowbroker.XConomy.API.XConomyAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.ServicePriority;

public class VaultHook 
{

    public VaultHook()
    {
        Plugin VaultPlugin = Bukkit.getPluginManager().getPlugin( "Vault" );
        
        Vault VaultInstance = (Vault) VaultPlugin;
        
        XConomyAPI XConomy = new XConomyAPI( VaultInstance );
        
        Bukkit.getServicesManager().register(Economy.class, new Economy_XConomy(), VaultInstance, ServicePriority.Low);
    
    }
    
}
