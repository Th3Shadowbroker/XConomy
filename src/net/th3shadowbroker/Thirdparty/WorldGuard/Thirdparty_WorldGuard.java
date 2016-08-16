package net.th3shadowbroker.Thirdparty.WorldGuard;

import net.th3shadowbroker.Thirdparty.WorldGuard.Objects.Thirdparty_Config_WorldGuard;
import net.th3shadowbroker.XConomy.main;

public class Thirdparty_WorldGuard 
{
    private final main XConomy;
    private final Thirdparty_Config_WorldGuard Config;
    
    //Construction
    public Thirdparty_WorldGuard( main plugin )
    {
        
        this.XConomy = plugin;
        
        XConomy.Console.writeAPI( "WorldGuard detected and hooked..." );
        
        this.Config = new Thirdparty_Config_WorldGuard();
        
        this.RegisterEvents();
        
        this.RegisterCommands();
        
    }
    
    //Register commands
    private void RegisterCommands()
    {
        
    }
    
    //Register events
    private void RegisterEvents()
    {
        
    }
    
}
