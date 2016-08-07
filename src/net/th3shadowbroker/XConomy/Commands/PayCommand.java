package net.th3shadowbroker.XConomy.Commands;

import net.th3shadowbroker.XConomy.Exceptions.InvalidAmountException;
import net.th3shadowbroker.XConomy.Exceptions.NotEnoughMoneyException;
import net.th3shadowbroker.XConomy.Loaders.Commands;
import net.th3shadowbroker.XConomy.Objects.Transaction;
import net.th3shadowbroker.XConomy.Permissions.Permissions;
import net.th3shadowbroker.XConomy.Permissions.Permissions.XConomyPermission;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PayCommand implements CommandExecutor
{
    
    private final main XConomy;
    private final Commands loader;
    
    public PayCommand( Commands loaderClass )
    {
        
        this.loader = loaderClass;
        
        this.XConomy = loaderClass.loader;
        
    }

    @Override
    public boolean onCommand( CommandSender sender, Command cmd, String cmdLabel, String[] args ) 
    {

        if ( sender instanceof Player )
        {
            
            Player p = (Player) sender;
            
            if ( cmd.getName().equalsIgnoreCase( "Pay" ) )
            {
                if ( p.hasPermission( Permissions.GetPermission( XConomyPermission.USER ) ) )
                {
                    if ( XConomy.Config.getBoolean( "Actions.AllowTransferFromEverywhere" ) )
                    {
                        if ( args.length == 2 )
                        {

                            try
                            {

                                //This player seems to be our target
                                Player target = Bukkit.getOfflinePlayer( args [0] ).getPlayer();

                                //Let's create a new transaction for the office!
                                Transaction TransferTransaction = new Transaction( p, target, Double.parseDouble( args[1] ) );

                                //Try to transfer the money
                                try {

                                    TransferTransaction.Transfer();

                                } catch (NotEnoughMoneyException ex) {

                                    p.sendMessage( XConomy.ChatPrefix() + XConomy.lang.getText( "AccountNotEnoughMoneyToOwnerBank" ) );

                                } catch (InvalidAmountException ex) {

                                    p.sendMessage( XConomy.ChatPrefix() + XConomy.lang.getText( "AccountInvalidAmount" ).replaceAll( "%AMOUNT%" , args[1] ) );

                                }

                            //If the given double isn't a double
                            } catch ( NumberFormatException ex ) {

                                p.sendMessage( XConomy.ChatPrefix() + XConomy.lang.getText( "AccountInvalidAmount" ).replaceAll( "%AMOUNT%" , args[1] ) );

                            //Any oher error    
                            } catch ( Exception ex ) {

                                p.sendMessage( XConomy.ChatPrefix() + XConomy.lang.getText( "AccountNotFound" ).replaceAll( "%OWNER%" , args[0] ) );

                            }

                            return true;
                        
                        } else {

                            p.sendMessage( XConomy.ChatPrefix() + "§cPlease use §e/pay <Player> <Amount>" );
                            return true;

                        }
                        
                    } else {
                        
                        
                        p.sendMessage( XConomy.ChatPrefix() + XConomy.lang.getText( "SystemPayCmdDenied" ) );
                        return true;
                        
                    }
                    
                }
            }
        } else {
            
            XConomy.Console.write( "You don't have enough money. Yes you! In front of the console!" );
            return true;
        }
        
        return false;
        
    }
    
    
    
}
