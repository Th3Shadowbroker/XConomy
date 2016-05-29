package net.th3shadowbroker.XConomy.Commands.Arguments;

import java.io.File;
import net.th3shadowbroker.XConomy.Blueprints.CommandArgument;
import net.th3shadowbroker.XConomy.Exceptions.InvalidAmountException;
import net.th3shadowbroker.XConomy.Objects.Account;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;

public class XConAdd extends CommandArgument {

    @Override
    public void RunAction() {
     
        if ( arguments.length == 3 )
        {
            try {
            
                OfflinePlayer target = Bukkit.getOfflinePlayer( arguments[1] );
                double amount = Double.parseDouble( arguments[2] );
                
                if ( Account.accountExists( new File( plugin.getDataFolder(), "accounts.yml" ), target.getUniqueId() ) == true )
                {
                    
                    Account targetAccount = new Account( plugin , target );
                
                    try{

                        targetAccount.addMoney( amount );

                        player.sendMessage( plugin.ChatPrefix() + plugin.lang.getText( "AccountMoneyAdded" ).replaceAll( "%PLAYER%" , target.getName() )
                                                                                                              .replaceAll( "%AMOUNT%" , arguments[2] )
                                                                                                              .replaceAll( "%FULLNAME%" , plugin.Config.getString( "Currency.FullName" ).toLowerCase() ) );

                    }   catch   ( InvalidAmountException ex )   {

                        player.sendMessage( plugin.ChatPrefix() + plugin.lang.getText( "AccountInvalidAmount" ).replaceAll( "%PLAYER%" , target.getName() )
                                                                                                               .replaceAll( "%AMOUNT%" , arguments[2] )
                                                                                                               .replaceAll( "%FULLNAME%" , plugin.Config.getString( "Currency.FullName" ).toLowerCase() ) );

                    }
                    
                }   else    {
                    
                    player.sendMessage( plugin.ChatPrefix() + plugin.lang.getText( "AccountNotFound" ).replaceAll( "%OWNER%" , target.getName() )
                                                                                                      .replaceAll( "%AMOUNT%" , arguments[2] )
                                                                                                      .replaceAll( "%FULLNAME%" , plugin.Config.getString( "Currency.FullName" ).toLowerCase() ) );
                    
                    
                }
                
                
            } catch ( Exception ex ) {
            
                player.sendMessage( plugin.ChatPrefix() + "§cPlease use " + ChatColor.GOLD + "/xcon add <Player> <Amount>" );
                
            }
        }
        
    }
    
}
