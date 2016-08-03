package net.th3shadowbroker.XConomy.Objects;

import net.th3shadowbroker.XConomy.API.MoneyTransferEvent;
import net.th3shadowbroker.XConomy.Exceptions.InvalidAmountException;
import net.th3shadowbroker.XConomy.Exceptions.NotEnoughMoneyException;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Transaction 
{
    
    private final Player Sender;
    private final Player Target;
    private final double Amount;
    private final TransactionID ID;
    private final main XConomy = main.getInstance();
    
    //Construction
    public Transaction( Player sender, Player target, double amount )
    {
        this.Sender = sender;
        this.Target = target;
        this.Amount = amount;
        this.ID = new TransactionID( this );
    }
    
    //Transfer the transaction
    public void Transfer() throws NotEnoughMoneyException, InvalidAmountException
    {
       
        Account SendersAcc = new Account( XConomy, Sender );
        
        //Check the "pocket" at first
        if ( SendersAcc.hasEnough( Amount ) )
        {
            
            SendersAcc.removeMoney( Amount );
            
            //Target is online ?
            if ( Target.isOnline() )
            {
                
                Account TargetsAcc = new Account( XConomy, Target );
                
                TargetsAcc.addMoney( Amount );

            } else {
                
                Sender.sendMessage( XConomy.ChatPrefix() + XConomy.lang.getText( "SystemPlayerSearchingDatabase" ) );
                
                Player OfflineTarget = Bukkit.getOfflinePlayer( Target.getUniqueId() ).getPlayer();
                
                Account TargetsAcc = new Account( XConomy, OfflineTarget );
                
                TargetsAcc.addMoney( Amount );
                
            }
            
            //Call event
            Bukkit.getPluginManager().callEvent( new MoneyTransferEvent( Sender, Target, Amount ) );
            
            //Log transaction
            LogTransaction();
            
            return;
            
        }
        
        //At least we ceck the bank account
        if ( XConomy.BankManager.HasEnough( Sender, Amount ) )
        {
            
            XConomy.BankManager.RemoveMoney( Sender, Amount );
            
            //Target is online ?
            if ( Target.isOnline() )
            {
                
                Account TargetsAcc = new Account( XConomy, Target );
                
                TargetsAcc.addMoney( Amount );

            } else {
                
                Sender.sendMessage( XConomy.ChatPrefix() + XConomy.lang.getText( "SystemPlayerSearchingDatabase" ) );
                
                Player OfflineTarget = Bukkit.getOfflinePlayer( Target.getUniqueId() ).getPlayer();
                
                Account TargetsAcc = new Account( XConomy, OfflineTarget );
                
                TargetsAcc.addMoney( Amount );
                
            }
            
            //Call event
            Bukkit.getPluginManager().callEvent( new MoneyTransferEvent( Sender, Target, Amount ) );
            
            //Log transaction
            LogTransaction();
            
            return;
            
        }
        
        throw new NotEnoughMoneyException( "Not enough money!" );
        
    }
    
    //Log a transaction if enabled
    private void LogTransaction()
    {
        if ( XConomy.Config.getBoolean( "Actions.LogTransactions" ) )
        {
            XConomy.TransactionLog.WriteEntry( ID.GetID()+ "--> " + Amount );
        }
    }
    
    //Get the transaction sender
    public Player GetSender()
    {
        return Sender;
    }
    
    //Get the transactions target
    public Player GetTarget()
    {
        return Target;
    }
    
    //Get the transaction amount
    public double GetAmount()
    {
        return Amount;
    }
    
    //Get transaction id
    public TransactionID GetID()
    {
        return ID;
    }
    
}
