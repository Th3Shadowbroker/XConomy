package net.th3shadowbroker.XConomy.ATM;

import org.bukkit.Location;

public class ATM {
    
    private final Location location;
    
    //Construction
    public ATM( Location location )
    {
        this.location = location;
    }
       
    //Get ATM's location
    public Location getLocation()
    {
        return location;
    }
    
}
