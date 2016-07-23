package net.th3shadowbroker.XConomy.GUI;

import net.th3shadowbroker.XConomy.Blueprints.ATMInterface;
import org.bukkit.entity.Player;

public abstract class GUIItemExtension 
{
    
    public Player Player;
    public ATMInterface CurrentInterface;
    public GUIItemStack CurrentItem;
    
    public GUIItemExtension()
    {
        
        this.OnConstruct();
        
    }
    
    public void UpdateInformations( Player player, ATMInterface cInterface, GUIItemStack item )
    {
        
        this.Player = player;
        
        this.CurrentInterface = cInterface;
        
        this.CurrentItem = item;
        
    }
    
    public abstract void OnConstruct();
    
    public abstract void Run();
    
}
