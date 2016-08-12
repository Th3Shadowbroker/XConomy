package net.th3shadowbroker.XConomy.Objects;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class DateDifferenceManager
{
    
    private final File ConfigFile;
    private final FileConfiguration Config;
    private final main XConomy;

    //Construction
    public DateDifferenceManager( main Plugin )
    {
        this.XConomy = Plugin;
        this.ConfigFile = new File( XConomy.getDataFolder(), "timesave.tmp" );
        this.Config = YamlConfiguration.loadConfiguration( ConfigFile );
        this.Setup();
        this.Save();
    }
    
    //Setup on construction
    private void Setup()
    {
        
        Config.options().header( "DO NOT ACCESS THIS FILE WHILE XCONOMY IS RUNNING\nIT WILL BE TO FAST FOR YOU" );
        
        Config.addDefault( "FeesLastPayed", 0 );
        
        Config.options().copyDefaults( true );
        
        this.Save();
        
    }
    
    //Get current state
    public int GetCurrent()
    {
        return Config.getInt( "FeesLastPayed" );
    }

    //Send a down
    public void SendDown()
    {
        
        int LastState = Config.getInt( "FeesLastPayed" );
        
        Config.set( "FeesLastPayed" , LastState - 1 );
        
        this.Save();
        
    }
    
    //Test command
    public void Test()
    {
        Config.set( "FeesLastPayed" , 3 );
        
        this.Save();
    }
    
    //Reset last payement
    public void Reset()
    {
        
        Config.set( "FeesLastPayed" , XConomy.Config.getInt( "Bank.FeeDelay" ) );
        this.Save();
        
    }
    
    //Save time-config file
    public void Save()
    {
        try {
            Config.save( ConfigFile );
        } catch (IOException ex) {
            Logger.getLogger(DateDifferenceManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
