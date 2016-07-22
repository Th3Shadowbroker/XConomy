package net.th3shadowbroker.XConomy.GUI;

import net.th3shadowbroker.XConomy.Blueprints.ATMInterface;
import org.bukkit.entity.Player;

public abstract class GUIItemExtension 
{
    
    public Player Player;
    public ATMInterface CurrentInterface;
    
    public GUIItemExtension()
    {
        
        this.OnConstruct();
        
    }
    
    public void UpdateInformations( Player player, ATMInterface cInterface )
    {
        
        this.Player = player;
        
        this.CurrentInterface = cInterface;
        
    }
    
    public abstract void OnConstruct();
    
    public abstract void Run();
    
}
