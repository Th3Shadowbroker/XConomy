package net.th3shadowbroker.XConomy.Commands;

import net.th3shadowbroker.XConomy.Commands.Arguments.BalanceFromArguments;
import net.th3shadowbroker.XConomy.Loaders.Commands;
import net.th3shadowbroker.XConomy.Objects.Account;
import net.th3shadowbroker.XConomy.Permissions.Permissions;
import net.th3shadowbroker.XConomy.Permissions.Permissions.XConomyPermission;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BalanceCommand implements CommandExecutor {

    private main plugin;
    private Commands loader;
    
    public BalanceCommand( Commands loaderClass )
    {
        
        this.loader = loaderClass;
        this.plugin = loaderClass.loader;
        
    }
    
    @Override
    public boolean onCommand( CommandSender sender, Command cmd, String cmdLabel, String[] args ) {
        
        Player p = (Player) sender;
        
        if ( p.hasPermission( Permissions.GetPermission( XConomyPermission.USER ) ) )
        {
                if ( cmd.getName().equalsIgnoreCase( "balance" ) )
                {
                    //No arguments given: Get own balance
                    if ( args.length == 0 )
                    {
                        if ( Account.accountExists( p.getUniqueId() ) )
                        {

                            p.sendMessage( plugin.ChatPrefix() + plugin.lang.getText( "UserBalanceCommand" ).replaceAll( "%BALANCE%" , String.valueOf( new Account( plugin, p ).getMoney() ) ) );
                            return true;
                            
                        } else {

                            p.sendMessage( plugin.ChatPrefix() + plugin.lang.getText("AccountNotFound").replaceAll( "%OWNER%" , "you" ) );
                            return true;    
                        }


                    }

                    if ( args.length == 1 )
                    {
                       if ( p.hasPermission( Permissions.GetPermission( XConomyPermission.ADMIN ) ) )
                       {
                           BalanceFromArguments balanceFromArgs = new BalanceFromArguments();
                           balanceFromArgs.setVars( plugin , args , loader , p );
                           balanceFromArgs.RunAction();
                           return true;
                       }
                    }
                    
                }
        } 
        
       return true;
        
    }
    
}
