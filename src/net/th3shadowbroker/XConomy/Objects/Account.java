package net.th3shadowbroker.XConomy.Objects;

import java.io.File;
import net.th3shadowbroker.XConomy.API.MoneyTransferEvent;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Account {
    
    private final Player owner;
    private final FileConfiguration accounts;
    private final Plugin plugin;
    private final File accountsFile;
    
    //Construction
    public Account( Plugin plugin , Player p )
    {
        
        this.owner = p;
        
        this.plugin = plugin;
        
        this.accountsFile = new File( plugin.getDataFolder() , "accounts.yml" );
        
        this.accounts = YamlConfiguration.loadConfiguration( accountsFile );
        
        this.setup();
        
    }
    
    //Setup account
    private void setup()
    { 
        try {
            
            if ( accounts.getString( owner.getUniqueId().toString() ) == null )
            {
                accounts.set( owner.getUniqueId().toString() , 0.00 );
                
                this.save();

            } 
            
        } catch ( Exception ex ) {
            
            ex.printStackTrace();
            
        }
    }
    
    //Save changes
    private void save()
    {
        try {
            
            accounts.save( accountsFile );
            
        } catch ( Exception ex ) {
            
            ex.printStackTrace();
            
        }
    }
    
    //Get current credits
    public double getMoney()
    {
        
        return accounts.getDouble( owner.getUniqueId().toString() );
        
    }
    
    //Set account-credits
    public void setMoney( double amount )
    {
        
        accounts.set( owner.getUniqueId().toString() , amount );
        this.save();
        
    }
    
    //Add some money
    public void addMoney( double amount )
    {
        
        double before = accounts.getDouble( owner.getUniqueId().toString() );
        
        accounts.set( owner.getUniqueId().toString() , before + amount );
        
        this.save();
        
    }
    
    //Take some money
    public void removeMoney( double amount )
    {
        
        double before = accounts.getDouble( owner.getUniqueId().toString() );
        
        accounts.set( owner.getUniqueId().toString() , before - amount );
        
        this.save();
        
    }
    
    //Transfer some money
    public void transferMoney( Player to , double amount )
    {
        
        double before_owner = accounts.getDouble( owner.getUniqueId().toString() );
        double before_target = accounts.getDouble( to.getUniqueId().toString() );
        
        accounts.set( owner.getUniqueId().toString() , before_owner - amount );
        
        accounts.set( to.getUniqueId().toString() , before_target - amount );
        
        this.save();
        
        Bukkit.getServer().getPluginManager().callEvent( new MoneyTransferEvent( owner , to , amount ) );
        
    }
    
    //Account exists
    public static boolean accountExists( main plugin , Player p )
    {
        
        File tmp = new File( plugin.getDataFolder() , "accounts.yml" );
        FileConfiguration tmpCfg = YamlConfiguration.loadConfiguration( tmp );
        
        if ( tmpCfg.getString( p.getUniqueId().toString() ) == null )
        {
            
            return false;
            
        } else {
            
            return true;
            
        }
        
    }
    
}
