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
    private int TMP;

    //Construction
    public DateDifferenceManager( main Plugin )
    {
        this.XConomy = Plugin;
        this.ConfigFile = new File( XConomy.getDataFolder(), "timesave.tmp" );
        this.Config = YamlConfiguration.loadConfiguration( ConfigFile );
        this.Setup();
    }
    
    //Setup on construction
    private void Setup()
    {
        
        Config.options().header( "DONT CHANGE ANYTHING OR THE FEE-TIMER WILL RESET" );
        
        Config.addDefault( "FeesLastPaid", 0 );
        
        Config.options().copyDefaults( true );
        
        this.Save();
        
        this.TMP = GetCurrentRestorePoint();
        
    }
    
    //Get current state (from restore-point)
    public int GetCurrentRestorePoint()
    {
        
        return Config.getInt( "FeesLastPaid" );
        
    }
    
    //Get current state (from runtime)
    public int GetCurrent()
    {
        
        return this.TMP;
        
    }
    
    //Create restore point
    public void CreateRestorePoint()
    {
        
        Config.set( "FeesLastPaid" , TMP );
        
        this.Save();
        
    }

    //Send a down
    public void SendDown()
    {
        
        this.TMP -= 1;
        
        this.Save();
        
    }
    
    //Test command
    public void Test()
    {
        this.TMP = 3;
        
        this.Save();
    }
    
    //Reset last payement
    public void Reset()
    {
        
        Config.set( "FeesLastPaid" , XConomy.Config.getInt( "Bank.FeeDelay" ) );
        
        this.TMP = XConomy.Config.getInt( "Bank.FeeDelay" );
        
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
