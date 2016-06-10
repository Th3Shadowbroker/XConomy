package net.th3shadowbroker.XConomy.ATM.Objects;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.th3shadowbroker.XConomy.ATM.ATM;
import net.th3shadowbroker.XConomy.Exceptions.ATMExistsException;
import net.th3shadowbroker.XConomy.Exceptions.ATMNotExistsException;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ATMConfig {
    
    private final main plugin;
    private final String filename;
    private final FileConfiguration config;
    
    //Conmstruction
    public ATMConfig( main plugin, String filename )
    {
        
        this.plugin = plugin;
        
        this.filename = filename;
        
        this.config = YamlConfiguration.loadConfiguration( new File( this.plugin.getDataFolder() , this.filename + ".yml" ) );
        
        this.save();
        
    }
    
    //Create an new ATM
    public void addATM( ATM atm ) throws ATMExistsException
    {
        
        String key = createATMKey( atm );
                
        if ( config.getStringList( key ) != null )
        {
            
            config.set( key + ".X" , atm.getLocation().getBlockX() );
            config.set( key + ".Y" , atm.getLocation().getBlockY() );
            config.set( key + ".Z" , atm.getLocation().getBlockZ() );
            config.set( key + ".W" , atm.getLocation().getWorld().getName() );
         
            this.save();
            
        }
        else
        {
            
            throw new ATMExistsException();
            
        }
        
    }
    
    //Remove an existing ATM
    public void removeATM( ATM atm ) throws ATMNotExistsException
    {
        
        String key = createATMKey( atm );
        
        if ( config.getStringList( key )!= null )
        {
            
            config.set( key , null );
            this.save();
            
        }
        else
        {
            
            throw new ATMNotExistsException();
            
        }
    }
    
    //ATM exists
    public boolean ATMExists( Location location )
    {
        try {
            
            this.getATM( location );
            return true;
            
        } catch (ATMNotExistsException ex) {
            
            return false;
            
        }
    }
    
    //Get an existing ATM
    public ATM getATM( Location location ) throws ATMNotExistsException
    {

        String needle = createATMKey( location );
        
        for ( String ATMEntry : config.getStringList( "ATM" ) )
        {

            String haystack = ATMEntry;
            
            if ( needle.equals( haystack ) )
            {
                
                return new ATM( new Location(
                                    Bukkit.getWorld( config.getString( needle + ".W" ) ),
                                    config.getDouble( needle + ".X" ),
                                    config.getDouble( needle + ".Y" ),
                                    config.getDouble( needle + ".Z" )
                                ));
                
            }
        }
        
        throw new ATMNotExistsException();
        
    }
    
    //Create an ATMS's key used as it's name
    public static String createATMKey( ATM atm )
    {
        String tmp = "ATM." + atm.getLocation().getWorld().getName()+ atm.getLocation().getX() + atm.getLocation().getY() + atm.getLocation().getZ();
        return tmp.replaceAll( " " , "" );
    }
    
    //Create an ATMS's key used as it's location
    public static String createATMKey( Location loc )
    {
        String tmp = "ATM." + loc.getWorld().getName()+ loc.getX() + loc.getY() + loc.getZ();
        return tmp.replaceAll( " " , "" );
    }
    
    //Save config
    private void save()
    {
        try {
            
            this.config.save( new File( this.plugin.getDataFolder() , this.filename + ".yml" ) );
            
        } catch (IOException ex) {
            
            Logger.getLogger(ATMConfig.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
}
