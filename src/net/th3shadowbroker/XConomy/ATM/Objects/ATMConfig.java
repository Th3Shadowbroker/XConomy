package net.th3shadowbroker.XConomy.ATM.Objects;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.th3shadowbroker.XConomy.ATM.ATM;
import net.th3shadowbroker.XConomy.Exceptions.ATMExistsException;
import net.th3shadowbroker.XConomy.Exceptions.ATMNotExistsException;
import net.th3shadowbroker.XConomy.main;
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
        
        if ( config.getString( key + ".X" ) == null )
        {
            
            config.set( key + ".X" , atm.getLocation().getBlockX() );
            config.set( key + ".Y" , atm.getLocation().getBlockY() );
            config.set( key + ".Z" , atm.getLocation().getBlockZ() );
            config.set( key + ".W" , atm.getLocation().getWorld().getName() );
            
            save();
            
        } else {
            
            throw new ATMExistsException();
            
        }
        
    }
    
    //Remove an existing ATM
    public void removeATM( ATM atm ) throws ATMNotExistsException
    {
        
        String key = createATMKey( atm );
        
        if ( config.getStringList( key ) != null )
        {
            
            config.set( key + ".X" , null );
            config.set( key + ".Y" , null );
            config.set( key + ".Z" , null );
            config.set( key + ".W" , null );
            
            save();
            
        } else {
            
            throw new ATMNotExistsException();
            
        }
        
    }
    
    //ATM exists
    public boolean ATMExists( Location location )
    {
  
            return config.getString( createATMKey( location ) + ".X" ) != null;

    }
    
    //Get an existing ATM
    public ATM getATM( Location location ) throws ATMNotExistsException
    {

        String key = createATMKey( location );
        
        if ( config.getString( key + ".X" ) != null )
        {
            
            return new ATM( location );
            
        } else {
        
            throw new ATMNotExistsException();
            
        }
        
    }
    
    //Create an ATMS's key used as it's name
    public static String createATMKey( ATM atm )
    {
        String tmp = "ATM." + atm.getLocation().getWorld().getName() + String.valueOf( atm.getLocation().getBlockX() + "#" )
                                                                     + String.valueOf( atm.getLocation().getBlockY() + "#" )
                                                                     + String.valueOf( atm.getLocation().getBlockZ() + "#" )
                                                                    ;
        return tmp;
    }
    
    //Create an ATMS's key used as it's location
    public static String createATMKey( Location loc )
    {
        String tmp = "ATM." + loc.getWorld().getName() + String.valueOf( loc.getBlockX() + "#" )
                                                       + String.valueOf( loc.getBlockY() + "#" )
                                                       + String.valueOf( loc.getBlockZ() + "#" )
                                                                    ;
        return tmp;
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
