package net.th3shadowbroker.XConomy.Commands.Arguments;

import java.io.File;
import net.th3shadowbroker.XConomy.Objects.Account;
import net.th3shadowbroker.XConomy.Blueprints.CommandArgument;
import net.th3shadowbroker.XConomy.Cache.CacheState;
import net.th3shadowbroker.XConomy.Exceptions.InvalidAmountException;
import net.th3shadowbroker.XConomy.Exceptions.NotEnoughMoneyException;
import net.th3shadowbroker.XConomy.Objects.XConomyPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;

public class XConRemove extends CommandArgument {

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
                
                    try {
                        
                        try{

                        targetAccount.removeMoney( amount );

                        player.sendMessage( plugin.ChatPrefix() + plugin.lang.getText( "AccountMoneyRemoved" ).replaceAll( "%PLAYER%" , target.getName() )
                                                                                                              .replaceAll( "%AMOUNT%" , arguments[2] )
                                                                                                              .replaceAll( "%FULLNAME%" , plugin.Config.getString( "Currency.FullName" ).toLowerCase() ) );

                        }   catch   ( NotEnoughMoneyException ex )   {

                            player.sendMessage( plugin.ChatPrefix() + plugin.lang.getText( "AccountNotEnoughMoney" ).replaceAll( "%PLAYER%" , target.getName() )
                                                                                                                    .replaceAll( "%AMOUNT%" , arguments[2] )
                                                                                                                    .replaceAll( "%FULLNAME%" , plugin.Config.getString( "Currency.FullName" ).toLowerCase() ) );

                        }
                        
                    } catch ( InvalidAmountException ex ){
                        
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
            
                player.sendMessage( plugin.ChatPrefix() + "§cPlease use " + ChatColor.GOLD + "/xcon remove:del [Player:Bank] [Amount]" );
                
            }
            
        } else if( arguments.length == 2 ){
            
            try {
                
                if ( arguments[1].equalsIgnoreCase( "bank" ) )
                {
 
                        XConomyPlayer currentState = plugin.getCache().getCacheEntry( new XConomyPlayer( player ) );
                        
                        if ( currentState.getState() == CacheState.NORMAL )
                        {
                            
                            plugin.getCache().updateCacheEntry( new XConomyPlayer( player ), CacheState.WAIT_DELETION );
                            player.sendMessage( plugin.ChatPrefix() + "§cRigt-click on the bank you want do remove" );
                            
                        } else {
                            
                            plugin.getCache().updateCacheEntry( new XConomyPlayer( player ), CacheState.NORMAL );
                            player.sendMessage( plugin.ChatPrefix() + "§cBank deletion canceled" );
                            
                        }

                } else {
                    
                    throw new Exception();
                    
                }
                
            } catch ( Exception ex ) {
                
                player.sendMessage( plugin.ChatPrefix() + "§cPlease use " + ChatColor.GOLD + "/xcon remove:del [Player:Bank] [Amount]" );
                
            }
        
        } else {
            
            player.sendMessage( plugin.ChatPrefix() + "§cPlease use " + ChatColor.GOLD + "/xcon remove:del [Player:Bank] [Amount]" );
            
        }
        
    }
    
}
