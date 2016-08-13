package net.th3shadowbroker.XConomy.Addons;

import java.util.ArrayList;
import net.th3shadowbroker.XConomy.Blueprints.ATMInterface;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.plugin.Plugin;

public class CustomInterfaceManager 
{
    
    private ArrayList<CustomInterfaceHandler> HandledInterfaces;
    private main XConomy;
    
    public CustomInterfaceManager( main XConomyMainClass )
    {
        this.XConomy = XConomyMainClass;
    }

    //Register your custom interface
    public void RegisterInterface( Plugin plugin, CustomInterfaceHandler handler )
    {
        
        HandledInterfaces.add( handler );
        XConomy.Console.writeAPI( plugin.getName() + " registered a new interface (" + handler.GetID() + ")" );
        
    }
    
    //Get current interface
    public ATMInterface GetInterface()
    {
        
        for (  CustomInterfaceHandler Interface : HandledInterfaces )
        {
           
            String tmp = XConomy.getConfig().getString( "ATM.CustomInterface" ).toLowerCase();
            
            if ( Interface.GetID().equals( tmp ) )
            {
                return Interface.GetInterface();
            }
            
        }
        
        return null;
        
    }
    
    //Custom interfaces enabled ?
    public boolean UseCustomIntefaces()
    {
        
        return !XConomy.getConfig().getString( "ATM.CustomInterface" ).equalsIgnoreCase( "none" );
        
    }
    
}
