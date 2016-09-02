package net.th3shadowbroker.Thirdparty.Essentials.Objects;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Thirdparty_Config_Essentials 
{
    
    private final File ConfigFile;
    private final FileConfiguration Config;
    
    //Construction
    public Thirdparty_Config_Essentials()
    {
        
        this.ConfigFile = new File( main.getInstance().getDataFolder(), "thirdparty/Essentials.yml" );
        
        this.Config = YamlConfiguration.loadConfiguration( ConfigFile );
        
        this.Setup();
        
    }
    
    //Setup defaults
    private void Setup()
    {
        
        Config.options().header( "FOR SOME REASON ESSENTIALS SLOWS DOWN XCONOMY" );
        
        Config.addDefault( "OverrideTimeout.Enabled" ,  true );
        Config.addDefault( "OverrideTimeout.OverrideValue" , 175D );
        
        Config.options().copyDefaults( true );
        this.Save();
        
    }
    
    //Override default
    public boolean OverrideTimeout()
    {
        
        if ( Config.getString( "OverrideTimeout.Enabled" ) != null )
        {
            return Config.getBoolean( "OverrideTimeout.Enabled" );
        }
        
        return false;
        
    }
    
    //Get override value
    public double GetTimeout()
    {
        
        return Config.getDouble( "OverrideTimeout.OverrideValue" );
        
    }
    
    //Save config
    private void Save()
    { 
        try {
            Config.save(ConfigFile);
        } catch (IOException ex) {
            Logger.getLogger(Thirdparty_Config_Essentials.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
