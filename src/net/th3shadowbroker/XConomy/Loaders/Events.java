package net.th3shadowbroker.XConomy.Loaders;

import net.th3shadowbroker.XConomy.ATM.Events.BankCreation;
import net.th3shadowbroker.XConomy.ATM.Events.BankDeletion;
import net.th3shadowbroker.XConomy.ATM.Events.BlockATMBlockDestruction;
import net.th3shadowbroker.XConomy.ATM.Events.OpenATMInterface;
import net.th3shadowbroker.XConomy.Cache.Events.AddEntryOnJoin;
import net.th3shadowbroker.XConomy.Cache.Events.RemoveEntryOnQuit;
import net.th3shadowbroker.XConomy.Events.CreateAccountOnJoin;
import net.th3shadowbroker.XConomy.main;

public class Events {
    
    public final main loader;
    private static Events instance;

    //Construction
    public Events( main loaderClass )
    {
        
        this.loader = loaderClass;

        instance = this;
        
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
            //loader.getServer().getPluginManager().registerEvents( new TransactionTransfered( this ) , loader);
            
            //Account creation on join event
            loader.getServer().getPluginManager().registerEvents( new CreateAccountOnJoin( this ) , loader);
            
        /*
         *      Setup ATM creation events and etc.
         */
        
            //BankCreation event
            loader.getServer().getPluginManager().registerEvents( new BankCreation(this) , loader);
            
            //BankDeletion event
            loader.getServer().getPluginManager().registerEvents( new BankDeletion(this) , loader);
            
            //Atm interface events
            loader.getServer().getPluginManager().registerEvents( new OpenATMInterface(this) , loader);
            
            //Block ATM destruction
            loader.getServer().getPluginManager().registerEvents( new BlockATMBlockDestruction(this) , loader);
 
        
    }
    
    public static Events getLoaderInstance()
    {
        
        return instance;
        
    }
    
}
