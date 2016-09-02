package net.th3shadowbroker.Thirdparty.Essentials.Objects;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class EssentialsOverride 
{
    
    private final Thirdparty_Config_Essentials Config;
    
    //Construction
    public EssentialsOverride( Thirdparty_Config_Essentials config )
    {
        
        this.Config = config;
        
        this.OverrideConfigEntry();
        
        if ( main.getInstance().Debugger.PostDebug() )
        {
            main.getInstance().Console.writeAPI( "Loading override..." );
        }
        
    }
        
    //Override default
    private void OverrideConfigEntry()
    {
    
        if ( main.getInstance().Debugger.PostDebug() )
        {
            main.getInstance().Console.writeAPI( String.valueOf( Config.OverrideTimeout() ) );
        }
        
        if ( Config.OverrideTimeout() ) 
        {
            
            Plugin Essentials = Bukkit.getPluginManager().getPlugin( "Essentials" );
            FileConfiguration EssCfg = YamlConfiguration.loadConfiguration( new File( Essentials.getDataFolder(), "config.yml" ) );
            
            main.getInstance().Console.writeAPI( "Checking for EssentialsEco settings..." );

            if ( EssCfg.getString( "economy-lag-warning" ) != null )
            {
                
                Bukkit.getPluginManager().disablePlugin( Essentials );

                EssCfg.set( "economy-lag-warning" ,  Config.GetTimeout() );

                try {
                    EssCfg.save( new File( Essentials.getDataFolder(), "config.yml" ) );
                } catch (IOException ex) {
                    Logger.getLogger(EssentialsOverride.class.getName()).log(Level.SEVERE, null, ex);
                }

                Bukkit.getPluginManager().enablePlugin( Essentials );
                
            }
            
        }
    }
    
}
