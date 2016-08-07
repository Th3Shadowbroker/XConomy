package net.th3shadowbroker.XConomy.Vault;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import static net.milkbowl.vault.economy.EconomyResponse.ResponseType.*;
import net.th3shadowbroker.XConomy.Exceptions.InvalidAmountException;
import net.th3shadowbroker.XConomy.Exceptions.NotEnoughMoneyException;
import net.th3shadowbroker.XConomy.Objects.Account;
import net.th3shadowbroker.XConomy.Objects.DoubleFormatter;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

public class Economy_XConomy implements Economy
{

    private main XConomy = main.getInstance();
    
    @Override
    public boolean isEnabled() {
        
        return Bukkit.getPluginManager().isPluginEnabled( XConomy );
        
    }

    @Override
    public String getName() {
        
        return XConomy.getDescription().getName();
        
    }

    //That's true
    @Override
    public boolean hasBankSupport() {
        
        return true;
        
    }

    @Override
    public int fractionalDigits() {
        return 2;
    }

    @Override
    public String format(double d) {
       return String.valueOf( DoubleFormatter.Format(d) );
    }

    @Override
    public String currencyNamePlural() {
        
        return XConomy.Config.getString( "Currency.FullName" );
        
    }

    @Override
    public String currencyNameSingular() {
        
        return XConomy.Config.getString( "Currency.FullName" );
                
    }

    //Check account by name
    @Override
    public boolean hasAccount(String string) {
        
        OfflinePlayer p = Bukkit.getOfflinePlayer( string );
               
        return Account.accountExists( p.getUniqueId() );
        
    }

    //Check account by offline-player
    @Override
    public boolean hasAccount(OfflinePlayer op) {
        
        return Account.accountExists( op.getUniqueId() );
        
    }

    @Override
    public boolean hasAccount(String playername, String world) {
        
        return Account.accountExists( Bukkit.getOfflinePlayer(playername).getUniqueId() );
        
    }

    @Override
    public boolean hasAccount(OfflinePlayer op, String string) {
        
        return Account.accountExists( op.getUniqueId() );
        
    }

    //Get balance by name
    @Override
    public double getBalance(String string) {
        
        OfflinePlayer p = Bukkit.getOfflinePlayer( string );
        
        Account pAcc = new Account( XConomy, p.getPlayer() );
        
        return pAcc.getMoney();
        
    }

    //Get balance by offline player
    @Override
    public double getBalance(OfflinePlayer op) {
        
        Account pAcc = new Account( XConomy, op );
        
        return pAcc.getMoney();
        
        
    }

    @Override
    public double getBalance(String playername, String world) {
        
        OfflinePlayer op = Bukkit.getOfflinePlayer(playername);
        
        Account pAcc = new Account( XConomy, op );
        
        return pAcc.getMoney();
        
    }

    @Override
    public double getBalance(OfflinePlayer op, String string) {
        
        Account pAcc = new Account( XConomy, op );
        
        return pAcc.getMoney();
        
    }

    //Has enough by string
    @Override
    public boolean has(String string, double d) {
        
        OfflinePlayer p = Bukkit.getOfflinePlayer( string );
        
        Account pAcc = new Account( XConomy, p );
        
        return pAcc.hasEnough( d );
        
    }

    //Has enough by offline-player
    @Override
    public boolean has(OfflinePlayer op, double d) {
        
        Account pAcc = new Account( XConomy, op );
        
        return pAcc.hasEnough( d );
        
    }

    @Override
    public boolean has(String string, String string1, double d) {
       
        OfflinePlayer p = Bukkit.getOfflinePlayer( string );
        
        Account pAcc = new Account( XConomy, p );
        
        return pAcc.hasEnough( d );
        
    }

    @Override
    public boolean has(OfflinePlayer op, String string, double d) {
        
        Account pAcc = new Account( XConomy, op );
        
        return pAcc.hasEnough( d );
        
    }

    @Override
    public EconomyResponse withdrawPlayer(String string, double d) {
        
        OfflinePlayer op = Bukkit.getOfflinePlayer(string);
        
        Account opAcc = new Account( XConomy, op.getPlayer() );
        
        try {
            
            opAcc.removeMoney( d );
            
        } catch (NotEnoughMoneyException ex) {
            
            Logger.getLogger(Economy_XConomy.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (InvalidAmountException ex) {
            
            Logger.getLogger(Economy_XConomy.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return new EconomyResponse( d, opAcc.getMoney(), SUCCESS, "" );
        
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer op, double d) {
        
        Account opAcc = new Account( XConomy, op.getPlayer() );
        
        try {
            
            opAcc.removeMoney( d );
            
        } catch (NotEnoughMoneyException ex) {
            
            Logger.getLogger(Economy_XConomy.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (InvalidAmountException ex) {
            
            Logger.getLogger(Economy_XConomy.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return new EconomyResponse( d, opAcc.getMoney(), SUCCESS, "" );
        
    }

    @Override
    public EconomyResponse withdrawPlayer(String string, String string1, double d) {
        
        OfflinePlayer op = Bukkit.getOfflinePlayer(string);
        
        Account opAcc = new Account( XConomy, op.getPlayer() );
        
        try {
            
            opAcc.removeMoney( d );
            
        } catch (NotEnoughMoneyException ex) {
            
            Logger.getLogger(Economy_XConomy.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (InvalidAmountException ex) {
            
            Logger.getLogger(Economy_XConomy.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return new EconomyResponse( d, opAcc.getMoney(), SUCCESS, "" );
        
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer op, String string, double d) {
        
        Account opAcc = new Account( XConomy, op.getPlayer() );
        
        try {
            
            opAcc.removeMoney( d );
            
        } catch (NotEnoughMoneyException ex) {
            
            Logger.getLogger(Economy_XConomy.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (InvalidAmountException ex) {
            
            Logger.getLogger(Economy_XConomy.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return new EconomyResponse( d, opAcc.getMoney(), SUCCESS, "" );
        
    }

    @Override
    public EconomyResponse depositPlayer(String string, double d) {
        
        OfflinePlayer op = Bukkit.getOfflinePlayer(string);
        
        Account opAcc = new Account( XConomy, op.getPlayer() );
        
        try {
            
            opAcc.addMoney( d );

        } catch (InvalidAmountException ex) {
            
            Logger.getLogger(Economy_XConomy.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return new EconomyResponse( d, opAcc.getMoney(), SUCCESS, "" );
        
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer op, double d) {
        
        Account opAcc = new Account( XConomy, op.getPlayer() );
        
        try {
            
            opAcc.addMoney( d );

        } catch (InvalidAmountException ex) {
            
            Logger.getLogger(Economy_XConomy.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return new EconomyResponse( d, opAcc.getMoney(), SUCCESS, "" );
        
    }

    @Override
    public EconomyResponse depositPlayer(String string, String string1, double d) {
        
        OfflinePlayer op = Bukkit.getOfflinePlayer(string);
        
        Account opAcc = new Account( XConomy, op.getPlayer() );
        
        try {
            
            opAcc.addMoney( d );

        } catch (InvalidAmountException ex) {
            
            Logger.getLogger(Economy_XConomy.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return new EconomyResponse( d, opAcc.getMoney(), SUCCESS, "" );
        
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer op, String string, double d) {

        Account opAcc = new Account( XConomy, op.getPlayer() );
        
        try {
            
            opAcc.addMoney( d );

        } catch (InvalidAmountException ex) {
            
            Logger.getLogger(Economy_XConomy.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return new EconomyResponse( d, opAcc.getMoney(), SUCCESS, "" );
        
    }

    @Override
    public EconomyResponse createBank(String string, String string1) {

         return new EconomyResponse( 0.0, 0.0, SUCCESS, "" );
        
    }

    @Override
    public EconomyResponse createBank(String string, OfflinePlayer op) {
        
        return new EconomyResponse( 0.0, 0.0, SUCCESS, "" );
        
    }

    @Override
    public EconomyResponse deleteBank(String string) {
        
        return new EconomyResponse( 0.0, 0.0, SUCCESS, "" );
        
    }

    @Override
    public EconomyResponse bankBalance(String string) {
        
        return new EconomyResponse( 0.0, XConomy.BankManager.GetBalance( Bukkit.getOfflinePlayer(string).getPlayer() ), SUCCESS, "" );
        
    }

    @Override
    public EconomyResponse bankHas(String string, double d) {
        
        return new EconomyResponse( 0.0, 0.0, SUCCESS, "" );
        
    }

    @Override
    public EconomyResponse bankWithdraw(String string, double d) {
        return new EconomyResponse( 0.0, 0.0, SUCCESS, "" );
    }

    @Override
    public EconomyResponse bankDeposit(String string, double d) {
        return new EconomyResponse( 0.0, 0.0, SUCCESS, "" );
    }

    @Override
    public EconomyResponse isBankOwner(String string, String string1) {
        return new EconomyResponse( 0.0, 0.0, SUCCESS, "" );
    }

    @Override
    public EconomyResponse isBankOwner(String string, OfflinePlayer op) {
        return new EconomyResponse( 0.0, 0.0, SUCCESS, "" );
    }

    @Override
    public EconomyResponse isBankMember(String string, String string1) {
        return new EconomyResponse( 0.0, 0.0, SUCCESS, "" );
    }

    @Override
    public EconomyResponse isBankMember(String string, OfflinePlayer op) {
        return new EconomyResponse( 0.0, 0.0, SUCCESS, "" );
    }

    @Override
    public List<String> getBanks() {
        return new ArrayList<String>();
    }

    @Override
    public boolean createPlayerAccount(String string) {
        
        //Already automated
        return true;
        
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer op) {
        
        //Already automated
        return true;
        
    }

    @Override
    public boolean createPlayerAccount(String string, String string1) {
        
        //Already automated
        return true;
        
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer op, String string) {
        
        //Already automated
        return true;
        
    }
    
}
