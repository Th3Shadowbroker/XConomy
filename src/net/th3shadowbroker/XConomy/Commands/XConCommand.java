package net.th3shadowbroker.XConomy.Commands;

import net.th3shadowbroker.XConomy.Commands.Arguments.XConAdd;
import net.th3shadowbroker.XConomy.Commands.Arguments.XConRemove;
import net.th3shadowbroker.XConomy.Commands.Arguments.XConSet;
import net.th3shadowbroker.XConomy.Loaders.Commands;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.ChatColor;
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

                    //No arguments given, so redirect to balance
                    if ( args.length == 0 )
                    {
                        p.performCommand( "balance" );
                        return true;
                    }
                    
                    //Arguments
                    if ( args.length > 0 )
                    {
                        
                        if ( args[0].equalsIgnoreCase( "info" ) )
                        {
                        p.sendMessage( plugin.ChatPrefix() + "§9Current version " + plugin.getDescription().getVersion() );
                        p.sendMessage( plugin.ChatPrefix() + "§9XConomy created by " + plugin.getDescription().getAuthors() );
                        return true;
                        }
                        
                        //Set money argument
                        if ( args[0].equalsIgnoreCase( "set" ) )
                        { 
                          XConSet SetCommand = new XConSet();
                          SetCommand.setVars( plugin , args , loader , p );
                          SetCommand.RunAction();
                          return true;
                        }
                        
                        //Add money argument
                        if ( args[0].equalsIgnoreCase( "add" ) )
                        { 
                          XConAdd AddCommand = new XConAdd();
                          AddCommand.setVars( plugin , args , loader , p );
                          AddCommand.RunAction();
                          return true;
                        }
                        
                        //Remove money argument
                        if ( args[0].equalsIgnoreCase( "remove" ) | args[0].equalsIgnoreCase( "del" ) )
                        {
                          XConRemove RemoveCommand = new XConRemove();
                          RemoveCommand.setVars( plugin , args , loader , p );
                          RemoveCommand.RunAction();
                          return true;
                        }
                          
                        //Invalid argument, so redirect to balance command
                        p.performCommand( "balance " + args[0] );
                        
                        //p.sendMessage( plugin.ChatPrefix() + "§cPlease use " + ChatColor.GOLD + "/xcon <info:set:add:del> [Player] [Amount]" );
                        return true;
                        
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
