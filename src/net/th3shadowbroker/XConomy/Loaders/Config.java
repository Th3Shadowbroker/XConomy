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
        
        config.options().header( "XConomy written by Th3Shadowbroker \n KEEP IN MIND: THE FEE-MANAGER WILL PAY THE FEES BY MILLISECONDS \n BY DEFAULT." );
        
        config.addDefault( "Bank.FeeDelay" , 86400 );
        
        config.addDefault( "ATM.StartAt" , 50000 );
        config.addDefault( "ATM.StepSize" , 10000 );
        config.addDefault( "ATM.CustomInterface" , "None" );
        
        config.addDefault( "ConsolePrefix" , "[XConomy]" );
        config.addDefault( "ChatPrefix" , "&9[&eX&9Conomy]" );
        
        config.addDefault( "Currency.Shortname" , "C" );
        config.addDefault( "Currency.FullName" , "Credits" );
        
        config.addDefault( "Currency.StartAccountWith" , 0.0 );
        config.addDefault( "Currency.StartBankWith" , 0.0 );
        
        config.addDefault( "Fees.Limits.1000" , 0.05 );
        config.addDefault( "Fees.Limits.10000" , 0.025 );
        config.addDefault( "Fees.Limits.100000" , 0.0125 );
        config.addDefault( "Fees.Limits.>100000" , 0.00625 );
        
        config.addDefault( "Messages.MessageOnTransfer" , true );
        
        config.addDefault( "Actions.CreateAccountOnJoin" , true );
        config.addDefault( "Actions.AllowTransferFromEverywhere" , false );
        config.addDefault( "Actions.LogTransactions" , false );
        
        config.options().copyDefaults( true );
        loader.saveConfig();
        
    }
    
}
