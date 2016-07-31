package net.th3shadowbroker.XConomy.Vault;

import java.util.List;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.th3shadowbroker.XConomy.Objects.Account;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

public class XConVault implements Economy
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String format(double d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String currencyNamePlural() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String currencyNameSingular() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public boolean hasAccount(String string, String string1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasAccount(OfflinePlayer op, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public double getBalance(String string, String string1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getBalance(OfflinePlayer op, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean has(OfflinePlayer op, String string, double d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EconomyResponse withdrawPlayer(String string, double d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer op, double d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EconomyResponse withdrawPlayer(String string, String string1, double d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer op, String string, double d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EconomyResponse depositPlayer(String string, double d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer op, double d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EconomyResponse depositPlayer(String string, String string1, double d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer op, String string, double d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EconomyResponse createBank(String string, String string1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EconomyResponse createBank(String string, OfflinePlayer op) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EconomyResponse deleteBank(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EconomyResponse bankBalance(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EconomyResponse bankHas(String string, double d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EconomyResponse bankWithdraw(String string, double d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EconomyResponse bankDeposit(String string, double d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EconomyResponse isBankOwner(String string, String string1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EconomyResponse isBankOwner(String string, OfflinePlayer op) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EconomyResponse isBankMember(String string, String string1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EconomyResponse isBankMember(String string, OfflinePlayer op) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getBanks() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
