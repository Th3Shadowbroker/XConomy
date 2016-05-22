package net.th3shadowbroker.XConomy.Commands.Arguments;

import net.th3shadowbroker.XConomy.Objects.Account;
import net.th3shadowbroker.XConomy.Objects.CommandArgument;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class XConSet extends CommandArgument {

    @Override
    public void RunAction() {
 
      if ( arguments.length == 3 )
      {
          
          try {
              
            Player target = Bukkit.getPlayer( arguments[1] ); //Target-player
              
            if ( Account.accountExists( plugin , target ) ) //Account already exists
            {
                
                Account targetAccount = new Account( plugin , player );
                
                targetAccount.setMoney( Double.parseDouble( arguments[2] ) );
                
                player.sendMessage( plugin.ChatPrefix() + plugin.lang.getText( "AccountBalanceSet" ).replaceAll( "%PLAYER%" , target.getName() ).replaceAll( "%AMOUNT%" , arguments[2] )
                                                                                                    .replaceAll( "%FULLNAME%" , plugin.Config.getString( "Currency.FullName" ) ).toLowerCase() );
                
            } else {
                
                player.sendMessage( plugin.ChatPrefix() + plugin.lang.getText( "AccountNotFound" ).replaceAll( "%OWNER%" , target.getName() ) );
                
            }
          
          } catch ( Exception ex ) {
              
              player.sendMessage( plugin.ChatPrefix() + "§cPlease use " + ChatColor.GOLD + "/xcon set <Player> <Amount>" );
              plugin.Console.write( ex.getMessage() );
              ex.printStackTrace();
          }
          
      } else {
          
          player.sendMessage( plugin.ChatPrefix() + "§cPlease use " + ChatColor.GOLD + "/xcon set <Player> <Amount>" );
          
      }
    }
    
}
