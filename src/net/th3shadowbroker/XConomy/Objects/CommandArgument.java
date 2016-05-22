package net.th3shadowbroker.XConomy.Objects;

import net.th3shadowbroker.XConomy.main;
import org.bukkit.entity.Player;

public abstract class CommandArgument {

    public String[] arguments;
    public Player player;
    public main plugin;
    
    public abstract void RunAction();
 
    public void setVars( main plugin , String[] args , Player p )
    {
        
        this.arguments = args;
        
        this.player = p;
        
        this.plugin = plugin;
        
    }
    
}
