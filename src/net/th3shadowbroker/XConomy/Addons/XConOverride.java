package net.th3shadowbroker.XConomy.Addons;

import net.th3shadowbroker.XConomy.Addons.OverrideManager.OverrideType;

public class XConOverride 
{
    
    private final Class OverrideClass;
    private final OverrideType Type;
    
    //Constructor
    public XConOverride( Class OverrideClass, OverrideType Type )
    {
        
        this.OverrideClass = OverrideClass;
        
        this.Type = Type;
        
    }
    
    //Get override-class
    public Class GetOverride()
    {
        
        return this.OverrideClass;
        
    }
    
    //Get override-type
    public OverrideType GetOverrideType()
    {
        
        return this.Type;
        
    }
    
    
}
