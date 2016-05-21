package net.th3shadowbroker.XConomy.Loaders;

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
        
    }
    
}
