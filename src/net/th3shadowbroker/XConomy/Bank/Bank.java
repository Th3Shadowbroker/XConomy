package net.th3shadowbroker.XConomy.Bank;

import java.io.File;
import java.io.IOException;
import net.th3shadowbroker.XConomy.Exceptions.InvalidAmountException;
import net.th3shadowbroker.XConomy.Exceptions.NotEnoughMoneyException;
import net.th3shadowbroker.XConomy.Objects.Account;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Bank 
{
    
    private final main XConomy;
    private final File BankFile;
    private final FileConfiguration Bank;
        
    //Constructor
    public Bank( main plugin, File BankFile )
    {
        
        this.XConomy = plugin;
        this.BankFile = BankFile;
        this.Bank = YamlConfiguration.loadConfiguration( BankFile );
        
    }
    
    //Create bank account
    public void CreateBankAccount( Player p )
    {
        try {
            
            if ( Bank.getString( "Bank." + p.getUniqueId().toString() ) == null )
            {
                
                Bank.set( "Bank." + p.getUniqueId().toString() , 0.0 );
                Save();
            
            }
            
        } catch ( Exception ex ) {
            
            XConomy.Console.write( ex.getMessage() );
            
        }
    }
    
    //Add some money
    public void Deposit( Player p, double amount )
    {
        if ( Bank.getString( "Bank." + p.getUniqueId().toString() ) != null )
        {
            
            Account TargetAccount = new Account( XConomy, p ); 
            
            try 
            {
                
                TargetAccount.removeMoney( amount );
                
                Bank.set( "Bank." + p.getUniqueId().toString(), GetBalance( p ) + amount );
                Save();
                
                p.sendMessage( XConomy.ChatPrefix() + XConomy.lang.getText( "MoneyTransferToSender" )
                                                      .replaceAll( "%AMOUNT%" , String.valueOf( amount ) )
                                                      .replaceAll( "%SHORTNAME%" , XConomy.Config.getString( "Currency.Shortname" ) )
                );
                
            } catch (NotEnoughMoneyException ex) {
                
                p.sendMessage( XConomy.ChatPrefix() + XConomy.lang.getText( "AccountNotEnoughMoneyToOwner" ) );
                
            } catch (InvalidAmountException ex) {
                
                p.sendMessage( XConomy.ChatPrefix() + XConomy.lang.getText( "SystemUniErrMsg" ) );
                
            }
   
        }
    }
    
    //Get some money
    public void Withdraw( Player p, double amount ) throws NotEnoughMoneyException
    {
        if ( Bank.getString( "Bank." + p.getUniqueId().toString() ) != null )
        {
            
            Account TargetAccount = new Account( XConomy, p ); 
            
            if ( Bank.getDouble( "Bank." + p.getUniqueId().toString() ) >= amount )
            {
                
                Bank.set( "Bank." + p.getUniqueId().toString(), GetBalance( p ) - amount );
                
                
                
                try {
                    
                    TargetAccount.addMoney( amount );
                    
                } catch (InvalidAmountException ex) {
                    
                    p.sendMessage( XConomy.ChatPrefix() + XConomy.lang.getText( "SystemUniErrMsg" ) );
                    
                }
                
                p.sendMessage( XConomy.ChatPrefix() + XConomy.lang.getText( "MoneyTransferToSender" )
                                                      .replaceAll( "%AMOUNT%" , String.valueOf( amount ) )
                                                      .replaceAll( "%SHORTNAME%" , XConomy.Config.getString( "Currency.Shortname" ) )
                );
                
            } else {
                
                throw new NotEnoughMoneyException();
                
            }
   
        }
    }
    
    //Get bank-account balance
    public double GetBalance( Player p )
    {
        if ( Bank.getString( "Bank." + p.getUniqueId().toString() ) != null )
        {
            
            return Bank.getDouble( "Bank." + p.getUniqueId().toString() );
            
        } else {
            
            return 0.0;
            
        }
    }
    
    //Get bank-account balance
    private double GetBalance( String uuid )
    {
        if ( Bank.getString( "Bank." + uuid ) != null )
        {
            
            return Bank.getDouble( "Bank." + uuid );
            
        } else {
            
            return 0.0;
            
        }
    }
    
    //Set a bank-account balance
    private void SetBalance( String uuid, double newAmount )
    {
        
        String path = "Bank." + uuid;
        
        Bank.set( path , newAmount );
        
        Save();
        
    }
    
    //Pay the fees to every account
    public void PayFees()
    {
        
        for( String BankAccount : Bank.getStringList( "Bank" ) )
        {
            if( BankAccount != null )
            {
                
                if ( GetBalance( BankAccount ) <= 1000 )                //1.000
                {
                 
                    this.SetBalance( BankAccount, GetBalance( BankAccount ) * XConomy.Config.getDouble( "Fees.Limits.1000" ) );
                    
                } else if ( GetBalance( BankAccount ) <= 10000 ) {      //10.000
                    
                    this.SetBalance( BankAccount, GetBalance( BankAccount ) * XConomy.Config.getDouble( "Fees.Limits.10000" ) );
                    
                } else if ( GetBalance( BankAccount ) <= 100.000 ) {    //100.000
                    
                    this.SetBalance( BankAccount, GetBalance( BankAccount ) * XConomy.Config.getDouble( "Fees.Limits.100000" ) );
                    
                } else if ( GetBalance( BankAccount ) > 100.000 ) {     //>100.000
                    
                    this.SetBalance( BankAccount, GetBalance( BankAccount ) * XConomy.Config.getDouble( "Fees.Limits.>100000" ) );
                    
                } else {
                    
                    XConomy.Console.write( "There was a problem while paying the fees to " + BankAccount );
                    
                }
                
            }
        }
        
    }
    
    //Save bank-file
    private void Save()
    {
        try {
            
            Bank.save( BankFile );
            
        } catch (IOException ex) {
            
            XConomy.Console.write( ex.getMessage() );
            ex.printStackTrace();
            
        }
    }
            
}
