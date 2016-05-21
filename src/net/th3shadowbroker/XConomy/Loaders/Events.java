package net.th3shadowbroker.XConomy.Loaders;

import net.th3shadowbroker.XConomy.Events.CreateAccountOnJoin;
import net.th3shadowbroker.XConomy.Events.MoneyTransfered;
import net.th3shadowbroker.XConomy.main;

public class Events {
    
    public final main loader;

    //Construction
    public Events( main loaderClass )
    {
        
        this.loader = loaderClass;

        setup();
        
    }
    
    //Load default configuration values
    private void setup()
    {
        
        /*
         *      Setup all required events
         */
        
            //Money transfer event
            loader.getServer().getPluginManager().registerEvents( new MoneyTransfered( this ) , loader);
            
            //Account creation on join event
            loader.getServer().getPluginManager().registerEvents( new CreateAccountOnJoin( this ) , loader);
        
    }
    
}
