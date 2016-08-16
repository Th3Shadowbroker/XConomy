package net.th3shadowbroker.Thirdparty.WorldGuard.Objects;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Thirdparty_Config_WorldGuard 
{
    
    private final File ConfigFile;
    private final FileConfiguration Config;
    
    //Construction
    public Thirdparty_Config_WorldGuard()
    {
        
        this.ConfigFile = new File( main.getInstance().getDataFolder(), "thirdparty/WorldGuard.yml" );
        
        this.Config = YamlConfiguration.loadConfiguration( ConfigFile );
        
        this.Setup();
        
    }
    
    //Setup defaults
    private void Setup()
    {
        
        Config.options().header( "DON'T EDIT THIS FILE!" );
        
        this.Save();
        
    }
    
    //Add a region to file
    public void AddRegion( ProtectedRegion region )
    {
        
        List<String> RegisteredRegions = Config.getStringList( "Regions" );
        
        if ( !RegisteredRegions.contains( region.getId() ) )
        {
            RegisteredRegions.add( region.getId() );
        }
        
        this.Save();
        
    }
    
    //Remove a region from file
    public void RemoveRegion( ProtectedRegion region )
    {
        
        List<String> RegisteredRegions = Config.getStringList( "Regions" );
        
        if ( RegisteredRegions.contains( region.getId() ) )
        {
            RegisteredRegions.remove( region.getId() );
        }
        
        this.Save();
        
    }
    
    //Is a specific player in region
    public static boolean IsPlayerInPayRegion( Player p )
    {
        
        FileConfiguration Config = YamlConfiguration.loadConfiguration( new File( main.getInstance().getDataFolder(), "thirdparty/WorldGuard.yml" ) );
        
        if ( Bukkit.getPluginManager().isPluginEnabled( "WorldGuard" ) )
        {
            if ( Config.getStringList( "Regions" ) != null )
            {
                for ( String region : Config.getStringList( "Regions" ) )
                {
                    
                    //THIS PART IS UNDER CONSTRUCTION
                    
                }
            }
        }
        
        return true;
        
    }
    
    //Is region registered
    public boolean RegionRegistered( ProtectedRegion region )
    {
        
        List<String> RegisteredRegions = Config.getStringList( "Regions" );
        
        return RegisteredRegions.contains( region.getId() );
        
    }
    
    //Save config file
    private void Save()
    {
        
        try {
            Config.save( ConfigFile );
        } catch (IOException ex) {
            Logger.getLogger(Thirdparty_Config_WorldGuard.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
