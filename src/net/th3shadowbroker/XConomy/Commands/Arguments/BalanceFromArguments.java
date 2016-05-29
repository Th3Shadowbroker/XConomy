package net.th3shadowbroker.XConomy.Commands.Arguments;

import net.th3shadowbroker.XConomy.Blueprints.CommandArgument;
import net.th3shadowbroker.XConomy.Objects.Account;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;

public class BalanceFromArguments extends CommandArgument {

    @Override
    public void RunAction() {
        if ( arguments.length == 1 )
        {
            try {
                
                OfflinePlayer target = Bukkit.getOfflinePlayer( arguments[0] );
                
                plugin.Console.write( target.getUniqueId().toString() );
                
                if ( Account.accountExists( target.getUniqueId() ) == true )
                {  
                    player.sendMessage( plugin.ChatPrefix() + plugin.lang.getText( "UserOthersBalanceCommand" ).replaceAll( "%PLAYER%" , target.getName())
                                                                                                               .replaceAll( "%BALANCE%" , String.valueOf( new Account( plugin , target.getPlayer() ).getMoney() ))
                                                                                                               );
  
                } else {
                    
                    player.sendMessage( plugin.ChatPrefix() + plugin.lang.getText( "AccountNotFound" ).replaceAll( "%OWNER%" , target.getName() ) );
                    
                }
                    
            } catch ( Exception ex ) {

                ex.printStackTrace();
                
                player.sendMessage( plugin.ChatPrefix() + "§cUser " + ChatColor.BLUE + arguments[0] + "§c does not exist / Auth servers are offline" );
                
            }
        }
    }
    
}
