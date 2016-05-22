package net.th3shadowbroker.XConomy.Commands;

import net.th3shadowbroker.XConomy.Commands.Arguments.XConSet;
import net.th3shadowbroker.XConomy.Loaders.Commands;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class XConCommand implements CommandExecutor {
    
    private final main plugin;
    private final Commands loader;
    
    //Construction
    public XConCommand( Commands loaderClass )
    {
        
        this.loader = loaderClass;
        
        this.plugin = loaderClass.loader;
        
    }

    @Override
    public boolean onCommand( CommandSender sender, Command cmd, String cmdLabel, String[] args ) {
        
        Player p = (Player) sender;
        
        if ( sender instanceof Player )
        {
            if ( p.hasPermission( "XConomy.admin" ) | p.isOp() )
            {
                if ( cmd.getName().equalsIgnoreCase( "xcon" ) ) //Xcon command
                {
                    
                    //No arguments
                    if ( args.length == 0 )
                    {
                        p.sendMessage( plugin.ChatPrefix() + "§9Current version " + plugin.getDescription().getVersion() );
                        p.sendMessage( plugin.ChatPrefix() + "§9XConomy created by " + plugin.getDescription().getAuthors() );
                    }
                    
                    //Arguments
                    if ( args.length > 0 )
                    {
                        
                        //Set money argument
                        if ( args[0].equalsIgnoreCase( "set" ) )
                        { 
                          XConSet SetCommand = new XConSet();
                          SetCommand.setVars( plugin , args , p );
                          SetCommand.RunAction();
                        }
                        
                        //Add money argument
                        if ( args[0].equalsIgnoreCase( "add" ) )
                        { }
                        
                        //Remove money argument
                        if ( args[0].equalsIgnoreCase( "remove" ) )
                        { }
                            
                    }
                    
                }
            }
        } else {
            
           /*
            *       If command send from console
            */
           
           plugin.Console.write( "Please use InGame commands." );
           
        }
        
        return false;
    }
    
}
