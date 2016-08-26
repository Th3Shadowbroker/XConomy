package net.th3shadowbroker.XConomy.Objects;

import net.th3shadowbroker.XConomy.main;
import org.bukkit.configuration.file.FileConfiguration;

public class DevTool 
{
    
    private final FileConfiguration Config;
    private final main XConomy;
    
    public DevTool( main plugin )
    {
        
        this.XConomy = main.getInstance();
        this.Config = XConomy.getConfig();
        
    }
    
    public boolean PostDebug()
    {
        if ( Config.getString( "DebugMode" ) != null )
        {
            
            return Config.getBoolean( "DebugMode" );
            
        } else {
            
            return false;
            
        }
    }
    
}
