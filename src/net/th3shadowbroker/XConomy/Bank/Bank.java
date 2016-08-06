package net.th3shadowbroker.XConomy.Bank;

import java.io.File;
import java.io.IOException;
import net.th3shadowbroker.XConomy.Exceptions.InvalidAmountException;
import net.th3shadowbroker.XConomy.Exceptions.NotEnoughMoneyException;
import net.th3shadowbroker.XConomy.Objects.Account;
import net.th3shadowbroker.XConomy.Objects.DoubleFormatter;
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
            
            if ( Bank.getString( p.getUniqueId().toString() ) == null )
            {
                
                Bank.set( p.getUniqueId().toString() , 0.0 );
                Save();
            
            }
            
        } catch ( Exception ex ) {
            
            XConomy.Console.write( ex.getMessage() );
            
        }
    }
    
    //Add some money
    public void Deposit( Player p, double amount )
    {
        if ( Bank.getString( p.getUniqueId().toString() ) != null )
        {
            
            Account TargetAccount = new Account( XConomy, p ); 
            
            double RoundedAmount = DoubleFormatter.Format( amount );
            
            try 
            {
                
                TargetAccount.removeMoney( RoundedAmount );
                
                Bank.set( p.getUniqueId().toString(), GetBalance( p ) + RoundedAmount );
                Save();
                
                p.sendMessage( XConomy.ChatPrefix() + XConomy.lang.getText( "MoneyTransferToSender" )
                                                      .replaceAll( "%AMOUNT%" , String.valueOf( RoundedAmount ) )
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
        if ( Bank.getString( p.getUniqueId().toString() ) != null )
        {
            
            Account TargetAccount = new Account( XConomy, p ); 
            
            double RoundedAmount = DoubleFormatter.Format( amount );
            
            if ( Bank.getDouble( p.getUniqueId().toString() ) >= RoundedAmount )
            {
                
                Bank.set( p.getUniqueId().toString(), GetBalance( p ) - RoundedAmount );
                
                
                
                try {
                    
                    TargetAccount.addMoney( RoundedAmount );
                    
                } catch (InvalidAmountException ex) {
                    
                    p.sendMessage( XConomy.ChatPrefix() + XConomy.lang.getText( "SystemUniErrMsg" ) );
                    
                }
                
                p.sendMessage( XConomy.ChatPrefix() + XConomy.lang.getText( "MoneyTransferToSender" )
                                                      .replaceAll( "%AMOUNT%" , String.valueOf( RoundedAmount ) )
                                                      .replaceAll( "%SHORTNAME%" , XConomy.Config.getString( "Currency.Shortname" ) )
                );
                
            } else {
                
                throw new NotEnoughMoneyException();
                
            }
   
        }
    }
    
    //Force money removement
    public void RemoveMoney( Player p, double amount )
    {
        
        Bank.set( p.getUniqueId().toString() , Bank.getDouble( p.getUniqueId().toString() ) - DoubleFormatter.Format( amount ) );
        Save();
        
    }
    
    //Get bank-account balance
    public double GetBalance( Player p )
    {
        if ( Bank.getString( p.getUniqueId().toString() ) != null )
        {
            
            return Bank.getDouble( p.getUniqueId().toString() );
            
        } else {
            
            return 0.0;
            
        }
    }
    
    //Get bank-account balance
    private double GetBalance( String uuid )
    {
        if ( Bank.getString( uuid ) != null )
        {
            
            return Bank.getDouble( uuid );
            
        } else {
            
            return 0.0;
            
        }
    }
    
    //Set a bank-account balance
    private void SetBalance( String uuid, double newAmount )
    {
        
        String path = uuid;
        
        Bank.set( path , DoubleFormatter.Format( newAmount ) );
        
        Save();
        
    }
    
    //Bank account has enough
    public boolean HasEnough( Player p, double amount )
    {
        
        double CurrentBankAccountBalance = Bank.getDouble( p.getUniqueId().toString() );
        
        if ( CurrentBankAccountBalance >= DoubleFormatter.Format( amount ) )
        {
            return true;
        }
        
        return false;
        
    }
    
    //Pay the fees to every account
    public void PayFees()
    {
        
        for( String BankAccount : Bank.getKeys(false) )
        {
            if( BankAccount != null )
            {
                
                XConomy.Console.write( "Paying fees to " + BankAccount );
                
                if ( GetBalance( BankAccount ) <= 1000 )                //1.000
                {
                 
                    this.SetBalance( BankAccount, GetBalance( BankAccount ) + GetBalance( BankAccount ) *  XConomy.Config.getDouble( "Fees.Limits.1000" )  );
                    
                } else if ( GetBalance( BankAccount ) <= 10000 ) {      //10.000
                    
                    this.SetBalance( BankAccount, GetBalance( BankAccount ) + GetBalance( BankAccount ) * XConomy.Config.getDouble( "Fees.Limits.10000" )  );
                    
                } else if ( GetBalance( BankAccount ) <= 100.000 ) {    //100.000
                    
                    this.SetBalance( BankAccount, GetBalance( BankAccount ) + GetBalance( BankAccount ) * XConomy.Config.getDouble( "Fees.Limits.100000" )  ) ;
                    
                } else if ( GetBalance( BankAccount ) > 100.000 ) {     //>100.000
                    
                    this.SetBalance( BankAccount, GetBalance( BankAccount ) + GetBalance( BankAccount ) * XConomy.Config.getDouble( "Fees.Limits.>100000" )  );
                    
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
