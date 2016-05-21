package net.th3shadowbroker.XConomy.Loaders;

import net.th3shadowbroker.XConomy.main;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {
    
    private final main loader;
    private final FileConfiguration config;
    
    //Construction
    public Config( main loaderClass )
    {
        
        this.loader = loaderClass;
        this.config = loaderClass.Config;
        
        setup();
        
    }
    
    //Load default configuration values
    private void setup()
    {
        
        /*
         *      Load all default settings
         */
        
        config.addDefault( "ConsolePrefix" , "[XConomy]" );
        config.addDefault( "ChatPrefix" , "&9[&eX&9Conomy]" );
        config.addDefault( "Currency.Shortname" , "C" );
        config.addDefault( "Currency.FullName" , "Credits" );
        config.addDefault( "Messages.MessageOnTransfer" , true );
        config.addDefault( "Actions.CreateAccountOnJoin" , true );
        
        config.options().copyDefaults( true );
        loader.saveConfig();
        
    }
    
}
