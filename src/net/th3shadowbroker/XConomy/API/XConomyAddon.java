package net.th3shadowbroker.XConomy.API;

import net.th3shadowbroker.XConomy.Addons.OverrideManager.OverrideType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public abstract class XConomyAddon 
{
    
    //What type is your addon
    private OverrideType OverrideType;
    public XConomyAPI XConomy;
    
    //Set the type of your addon
    public void SetOverrideType( OverrideType type )
    {
        this.OverrideType = type;
    }
    
    //Register your override
    public void RegisterAddon( Plugin YourPlugin ) throws Exception
    {
        this.XConomy = new XConomyAPI( YourPlugin );
        XConomy.GetXConomy().Overrides.RegisterOverride( this );
    }
    
    //Get your overrides type
    public OverrideType GetOverrideType()
    {
        return OverrideType;
    }
    
    //On loadup
    public abstract void OnEnable();
    
    //On run
    public abstract void OnRun( Player p );
    
    //On shutdown
    public abstract void OnDisable();
    
}
