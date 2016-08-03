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
        
        config.options().header( "XConomy written by Th3Shadowbroker \n KEEP IN MIND: THE FEE-MANAGER WILL PAY THE FEES EVERY HOUR \n BY DEFAULT." );
        
        config.addDefault( "Bank.FeeDelay" , 1 );
        
        config.addDefault( "ConsolePrefix" , "[XConomy]" );
        config.addDefault( "ChatPrefix" , "&9[&eX&9Conomy]" );
        config.addDefault( "Currency.Shortname" , "C" );
        config.addDefault( "Currency.FullName" , "Credits" );

        config.addDefault( "Fees.Limits.1000" , 0.05 );
        config.addDefault( "Fees.Limits.10000" , 0.025 );
        config.addDefault( "Fees.Limits.100000" , 0.0125 );
        config.addDefault( "Fees.Limits.>100000" , 0.00625 );
        
        config.addDefault( "Messages.MessageOnTransfer" , true );
        
        config.addDefault( "Actions.CreateAccountOnJoin" , true );
        config.addDefault( "Actions.AllowTransferFromEverywhere" , true );
        config.addDefault( "Actions.LogTransactions" , false );
        
        config.options().copyDefaults( true );
        loader.saveConfig();
        
    }
    
}
