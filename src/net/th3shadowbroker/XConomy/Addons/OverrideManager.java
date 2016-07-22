package net.th3shadowbroker.XConomy.Addons;

import java.util.ArrayList;

public class OverrideManager 
{
    
    private ArrayList<XConOverride> Overrides = new ArrayList<XConOverride>();
    
    //Type of manual override
    public enum OverrideType
    {
        ATM_INTERFACE,
        NONE
    }
    
    //Register a new override
    public void RegisterOverride( XConOverride Override )
    {
        if ( !Overrides.contains( Override ) && !OverrideExists( Override.GetOverrideType() ) )
        {
            
            Overrides.add( Override );
            
        } else {
            
            throw new NullPointerException( "This exception is currently not implemented." );
            
        }  
    }
    
    //Check for specific override
    public boolean OverrideExists( OverrideType Type )
    {
        for ( XConOverride Override : Overrides )
        {
            if ( Override.GetOverrideType() == Type )
            {
                
                return true;
                
            }
        }
        
        return false;
        
    }
    
    //Get a specific override
    public XConOverride GetOverride( OverrideType Type )
    {
        for ( XConOverride Override : Overrides ) 
        {
            if ( Override.GetOverrideType() == Type ) 
            {
                
                return Override;
                
            }
        }
        
        return null;
    }
    
}
