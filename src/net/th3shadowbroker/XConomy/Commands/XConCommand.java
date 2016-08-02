package net.th3shadowbroker.XConomy.Commands;

import net.th3shadowbroker.XConomy.Commands.Arguments.XConAdd;
import net.th3shadowbroker.XConomy.Commands.Arguments.XConCreate;
import net.th3shadowbroker.XConomy.Commands.Arguments.XConRemove;
import net.th3shadowbroker.XConomy.Commands.Arguments.XConSet;
import net.th3shadowbroker.XConomy.Commands.Arguments.XConTest;
import net.th3shadowbroker.XConomy.Loaders.Commands;
import net.th3shadowbroker.XConomy.Permissions.Permissions;
import net.th3shadowbroker.XConomy.Permissions.Permissions.XConomyPermission;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class XConCommand implements CommandExecutor {
    
    private final main XConomy;
    private final Commands loader;
    
    //Construction
    public XConCommand( Commands loaderClass )
    {
        
        this.loader = loaderClass;
        
        this.XConomy = loaderClass.loader;
        
    }

    @Override
    public boolean onCommand( CommandSender sender, Command cmd, String cmdLabel, String[] args ) {
        
        Player p = (Player) sender;
        
        if ( cmd.getName().equalsIgnoreCase( "xcon" ) )
        {
            
            if ( p instanceof Player )
            {
            
                
                
                //Admin only commands
                if ( p.hasPermission( Permissions.GetPermission( XConomyPermission.ADMIN ) ) )
                {
                    if ( args.length > 0 )
                    {
                        
                        //Add command
                        if ( args[0].equalsIgnoreCase( "Add" ) )
                        {
                            XConAdd XConAddCmd = new XConAdd();
                            XConAddCmd.setVars( XConomy, args, loader, p );
                            XConAddCmd.RunAction();
                            return true;
                        }

                        //Remove command
                        if ( args[0].equalsIgnoreCase( "Remove" ) )
                        {
                            XConRemove XConRemoveCmd = new XConRemove();
                            XConRemoveCmd.setVars( XConomy, args, loader, p );
                            XConRemoveCmd.RunAction();
                            return true;
                        }

                        //Set command
                        if ( args[0].equalsIgnoreCase( "Set" ) )
                        {
                            XConSet XConSetCmd = new XConSet();
                            XConSetCmd.setVars( XConomy, args, loader, p );
                            XConSetCmd.RunAction();
                            return true;
                        }

                        //Create command
                        if ( args[0].equalsIgnoreCase( "Create" ) )
                        {
                            XConCreate XConCreateCmd = new XConCreate();
                            XConCreateCmd.setVars( XConomy, args, loader, p );
                            XConCreateCmd.RunAction();
                            return true;
                        }

                        //Test command
                        if ( args[0].equalsIgnoreCase( "Test" ) )
                        {
                            XConTest XConTestCmd = new XConTest();
                            XConTestCmd.setVars( XConomy, args, loader, p );
                            XConTestCmd.RunAction();
                            return true;
                        }
                        
                    }  
                }

                //User commands
                if ( p.hasPermission( Permissions.GetPermission( XConomyPermission.USER ) ) )
                {
                   if ( args.length > 0 ) 
                   {
                        //Info command
                        if ( args[0].equalsIgnoreCase( "Info" ) )
                        {
                            p.sendMessage( XConomy.ChatPrefix() + "§bXConomy §ever." + XConomy.getDescription().getVersion() );
                            p.sendMessage( XConomy.ChatPrefix() + "§bWritten by §e" + XConomy.getDescription().getAuthors() );
                            p.sendMessage( XConomy.ChatPrefix() + "§bWebsite: §e" + XConomy.getDescription().getWebsite() );
                            return true;
                        }
                   }
                }
            
                
                //Placeholder for null-argument
                p.performCommand( "balance" );
                return true;

                
            //If console is sender    
            } else  {
            
                XConomy.Console.write( "The console isn't allmighty my friend" );
                return true;
            }
 
        }
        
        return false;
        
    }
    
}
