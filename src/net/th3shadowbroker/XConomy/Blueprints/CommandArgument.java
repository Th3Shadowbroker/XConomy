package net.th3shadowbroker.XConomy.Blueprints;

import net.th3shadowbroker.XConomy.Loaders.Commands;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.entity.Player;

public abstract class CommandArgument {

    public String[] arguments;
    public Player player;
    public Commands loader;
    public main plugin;
    
    public abstract void RunAction();
 
    public void setVars( main plugin , String[] args , Commands commandLoader, Player p )
    {
        
        this.arguments = args;
        
        this.player = p;
        
        this.plugin = plugin;
        
        this.loader = commandLoader;
        
    }
    
}
