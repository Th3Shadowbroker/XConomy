package net.th3shadowbroker.XConomy.Loaders;

import net.th3shadowbroker.XConomy.ATM.Events.BankCreation;
import net.th3shadowbroker.XConomy.Cache.Events.AddEntryOnJoin;
import net.th3shadowbroker.XConomy.Cache.Events.RemoveEntryOnQuit;
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
         *      Setup cache events   
         */
           
            //Add player on join
            loader.getServer().getPluginManager().registerEvents( new AddEntryOnJoin( this ) , loader);
            
            //Remove player on quit
            loader.getServer().getPluginManager().registerEvents( new RemoveEntryOnQuit( this ) , loader);
        
        /*
         *      Setup all required events
         */
        
            //Money transfer event
            loader.getServer().getPluginManager().registerEvents( new MoneyTransfered( this ) , loader);
            
            //Account creation on join event
            loader.getServer().getPluginManager().registerEvents( new CreateAccountOnJoin( this ) , loader);
            
        /*
         *      Setup ATM creation events 
         */
        
            //BankCreation event
            loader.getServer().getPluginManager().registerEvents( new BankCreation(this) , loader);
 
        
    }
    
}
