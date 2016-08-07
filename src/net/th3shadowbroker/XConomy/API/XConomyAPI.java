package net.th3shadowbroker.XConomy.API;

import java.util.UUID;
import net.th3shadowbroker.XConomy.Exceptions.AccountNotFoundException;
import net.th3shadowbroker.XConomy.Addons.OverrideManager;
import net.th3shadowbroker.XConomy.Bank.Bank;
import net.th3shadowbroker.XConomy.Cache.PlayerCache;
import net.th3shadowbroker.XConomy.Objects.Account;
import net.th3shadowbroker.XConomy.Permissions.Permissions;
import net.th3shadowbroker.XConomy.Permissions.Permissions.XConomyPermission;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;

public class XConomyAPI 
{
 
    private final Plugin Plugin;
    private final main XConomy;
    
    //Construction
    public XConomyAPI( Plugin YourPlugin )
    {
        this.Plugin = YourPlugin;
        this.XConomy = main.getInstance();
        
        XConomy.Console.writeAPI( YourPlugin.getName() + " is now using XConomy" );
        
    }
    
    //Get XConomy's bank system
    public Bank GetBankSystem()
    {
        return XConomy.BankManager;
    }
    
    //Get a players account by player
    public Account GetPlayerAccount( Player player ) throws AccountNotFoundException
    {
        if ( Account.accountExists( player.getUniqueId() ) )
        {
            
            return new Account( XConomy, player );
            
        } else {
            
            throw new AccountNotFoundException( "Account does not exist." );
            
        }
    }
    
    //Get a players account by offline account
    public Account GetPlayerAccount( OfflinePlayer player ) throws AccountNotFoundException
    {
        if ( Account.accountExists( player.getPlayer().getUniqueId() ) )
        {
            
            return new Account( XConomy, player );
            
        } else {
            
            throw new AccountNotFoundException( "Account does not exist." );
            
        }
    }
    
    //Get a players account by UUID
    public Account GetPlayerAccount( UUID uuid ) throws AccountNotFoundException
    {
        
        OfflinePlayer player = Bukkit.getOfflinePlayer( uuid );
        
        if ( Account.accountExists( player.getPlayer().getUniqueId() ) )
        {
            
            return new Account( XConomy, player );
            
        } else {
            
            throw new AccountNotFoundException( "Account does not exist." );
            
        }
        
    }
    
    //Get the currencies name
    public String GetCurrencyName( CurrencyName NameType )
    {
        if ( NameType == CurrencyName.SHORTNAME  )
        {
            
            return XConomy.Config.getString( "Currency.Shortname" );
            
        } else {
            
            return XConomy.Config.getString( "Currency.FullName" );
            
        }
    }
    
    //Enumerator for the different names
    public enum CurrencyName
    {
        SHORTNAME,
        FULLNAME
    }
    
    //Get XConomy's cache
    public PlayerCache GetCache()
    {
        return XConomy.getCache();
    }
    
    //Get XConomy's override-manager
    public OverrideManager GetOverrides()
    {
        return XConomy.Overrides;
    }
    
    //Write to console via XConomy
    public void Write( String message )
    {
        XConomy.Console.writeAPI( message );
    }
    
    //Get XConomys permissions
    public Permission GetPermission( XConomyPermission PermissionType )
    {
        return Permissions.GetPermission( PermissionType );
    }
    
    //Get XConomy
    public main GetXConomy()
    {
        return XConomy;
    }
    
}
