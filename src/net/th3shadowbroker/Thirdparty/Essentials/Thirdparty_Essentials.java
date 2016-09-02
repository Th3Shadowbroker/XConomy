package net.th3shadowbroker.Thirdparty.Essentials;

import net.th3shadowbroker.Thirdparty.Essentials.Objects.EssentialsOverride;
import net.th3shadowbroker.Thirdparty.Essentials.Objects.Thirdparty_Config_Essentials;
import net.th3shadowbroker.XConomy.main;

public class Thirdparty_Essentials 
{
    
    private final main XConomy;
    private final Thirdparty_Config_Essentials Config;
    
    //Construction
    public Thirdparty_Essentials( main plugin )
    {
        
        this.XConomy = plugin;
        
        this.Config = new Thirdparty_Config_Essentials();
        
        XConomy.Console.writeAPI( "Essentials detected and hooked..." );

        this.RegisterEssentials();
        
    }

    //Register all the stuff
    private void RegisterEssentials() 
    {
        
        EssentialsOverride EssOverride = new EssentialsOverride( Config );
        
    }
    
}
