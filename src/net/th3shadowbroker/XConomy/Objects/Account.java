package net.th3shadowbroker.XConomy.Objects;

import net.th3shadowbroker.XConomy.Exceptions.NotEnoughMoneyException;
import net.th3shadowbroker.XConomy.Exceptions.InvalidAmountException;
import java.io.File;
import java.util.UUID;
import net.th3shadowbroker.XConomy.API.MoneyTransferEvent;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
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
    
    //Construction
    public Account( Plugin plugin , OfflinePlayer p )
    {
        
        this.owner = p.getPlayer();
        
        this.plugin = plugin;
        
        this.accountsFile = new File( plugin.getDataFolder() , "accounts.yml" );
        
        this.accounts = YamlConfiguration.loadConfiguration( accountsFile );
        
        this.setup();
        
    }
    
    //Setup account
    private void setup()
    { 
        try {
            
            if ( this.owner != null && accounts.get( owner.getUniqueId().toString() ) == null )
            {
                
                accounts.set( owner.getUniqueId().toString() , 0.00 );
                
                this.save();

            } 
            
        } catch ( Exception ex ) {
            
            System.out.println( ex.getLocalizedMessage() );
   
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
    public void setMoney( double amount ) throws InvalidAmountException
    {
        if ( amount >= 0 )
        {
            
            accounts.set( owner.getUniqueId().toString() , amount );
            this.save();
            
        }   else    {
            
            throw new InvalidAmountException();
            
        }
    }
    
    //Add some money
    public void addMoney( double amount ) throws InvalidAmountException
    {
        if ( amount >= 0 )
        {
            
            double before = accounts.getDouble( owner.getUniqueId().toString() );
        
            accounts.set( owner.getUniqueId().toString() , before + amount );
        
            this.save();
            
        }   else    {
            
            throw new InvalidAmountException();
            
        }
    }
    
    //Take some money
    public void removeMoney( double amount ) throws NotEnoughMoneyException, InvalidAmountException
    {
        
        double before = accounts.getDouble( owner.getUniqueId().toString() );
        
        if ( amount >= 0 ) {
            if ( this.hasEnough( amount ) )
            {

                accounts.set( owner.getUniqueId().toString() , before - amount );

                this.save();

            }   else    {

                throw new NotEnoughMoneyException();

            }
        } else {
            
            throw new InvalidAmountException();
            
        }
        
    }
    
    //Transfer some money
    public void transferMoney( Player to , double amount ) throws NotEnoughMoneyException
    {
        
        double before_owner = accounts.getDouble( owner.getUniqueId().toString() );
        double before_target = accounts.getDouble( to.getUniqueId().toString() );
        
        if ( hasEnough( amount ) )
        {
            
            accounts.set( owner.getUniqueId().toString() , before_owner - amount );
        
            accounts.set( to.getUniqueId().toString() , before_target - amount );

            this.save();

            Bukkit.getServer().getPluginManager().callEvent( new MoneyTransferEvent( owner , to , amount ) );
            
        }   else    {
            
            throw new NotEnoughMoneyException();
            
        }
        
    }
    
    //Enough money ?
    public boolean hasEnough( double howMuch )
    {
        
        return howMuch <= getMoney();
        
    }
    
    //Account exists: Offline
    public static Boolean accountExists( File file , UUID uid )
    {
  
            File tmp = file;
            FileConfiguration tmpCfg = YamlConfiguration.loadConfiguration( tmp );

            return tmpCfg.get( uid.toString() ) != null;
            
    }
    
    //Account exists: Offline
    public static Boolean accountExists( UUID uid )
    {
  
            File tmp = new File( main.getInstance().getDataFolder() , "accounts.yml" );
            FileConfiguration tmpCfg = YamlConfiguration.loadConfiguration( tmp );

            return tmpCfg.get( uid.toString() ) != null;
            
    }
}
