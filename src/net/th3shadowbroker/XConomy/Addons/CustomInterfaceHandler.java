
package net.th3shadowbroker.XConomy.Addons;

import net.th3shadowbroker.XConomy.Blueprints.ATMInterface;

public class CustomInterfaceHandler 
{
    
    private final ATMInterface Interface;
    private final String ID;
    
    //Construction
    public CustomInterfaceHandler( String id, ATMInterface customInterface )
    {
        this.ID = id;
        this.Interface = customInterface;
    }
    
    //Get handel-id
    public String GetID()
    {
        return ID.toLowerCase();
    }
    
    //Get the handled interface
    public ATMInterface GetInterface()
    {
        return Interface;
    }
    
}
