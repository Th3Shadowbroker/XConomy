package net.th3shadowbroker.XConomy.Loaders;

import net.th3shadowbroker.XConomy.Commands.AtmCommand;
import net.th3shadowbroker.XConomy.Commands.BalanceCommand;
import net.th3shadowbroker.XConomy.Commands.PayCommand;
import net.th3shadowbroker.XConomy.Commands.XConCommand;
import net.th3shadowbroker.XConomy.main;

public class Commands {
    
    public final main loader;

    //Construction
    public Commands( main loaderClass )
    {
        
        this.loader = loaderClass;

        setup();
        
    }
    
    //Load default configuration values
    private void setup()
    {
        
        /*
         *      Setup all required commands
         */
        
            //Command executor: /xcon <args>
            loader.getCommand( "xcon" ).setExecutor( new XConCommand( this ) );
            
            //Command executor: /balance [Player]
            loader.getCommand( "balance" ).setExecutor( new BalanceCommand( this ) );
            
            //Command executor: /pay <Player> <Amount>
            loader.getCommand( "pay" ).setExecutor( new PayCommand( this ) );
            
            //Command executor: /atm
            loader.getCommand( "atm" ).setExecutor( new AtmCommand( this ) );
        
    }
    
}
