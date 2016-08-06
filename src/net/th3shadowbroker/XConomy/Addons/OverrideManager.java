package net.th3shadowbroker.XConomy.Addons;

import java.util.ArrayList;
import net.th3shadowbroker.XConomy.API.XConomyAddon;

public class OverrideManager 
{
    
    private ArrayList< XConomyAddon > Overrides = new ArrayList< XConomyAddon >();
    
    //Possible types of override
    public enum OverrideType
    {
        ATM_INTERFACE,
        NONE
    }
    
    //Register new override
    public void RegisterOverride( XConomyAddon Addon ) throws Exception
    {
        if ( !Overrides.contains( Addon ) )
        {
            
            Overrides.add( Addon );
            
        } else {
            
            throw new Exception( "This addon is already registered." );
            
        }
    }
    
    //Get a specific type of override
    public XConomyAddon GetOverride( OverrideType type )
    {
        
        try 
        {
            if ( Overrides != null )
            {
                for ( XConomyAddon addon : Overrides )
                {
                    if ( addon.GetOverrideType().equals( type ) )
                    {
                        return addon;
                    }
                }
            }
        } catch ( Exception ex ) {
            
            //Nothing
            
        }
        
        return null;
        
    }
    
    //Enable all addons
    public void EnableAddons()
    {
        for ( XConomyAddon addon : Overrides )
        {
            addon.OnEnable();
        }
    }
    
    //Disable all addons
    public void DisableAddons()
    {
        for ( XConomyAddon addon : Overrides )
        {
            addon.OnDisable();
        }
    }
    
}
