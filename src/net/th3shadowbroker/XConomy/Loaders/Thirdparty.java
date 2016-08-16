package net.th3shadowbroker.XConomy.Loaders;

import net.th3shadowbroker.Thirdparty.Citizens.Thirdparty_Citizens;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.Bukkit;

public class Thirdparty 
{
 
    private final main loader;
        
    //Construction
    public Thirdparty( main loaderClass )
    {
        
        this.loader = loaderClass;
        
        this.Setup();
        
    }
    
    //Setup
    private void Setup()
    {
        
        //Citizens thirdparty addon
        if ( Bukkit.getPluginManager().isPluginEnabled( "Citizens" ) )
        {
            Thirdparty_Citizens Citizens = new Thirdparty_Citizens( loader );
        }
        
    }
    
}
