package net.th3shadowbroker.XConomy.Objects;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class TransactionID 
{
    
    private final String ID;
    private final UUID SenderID;
    private final UUID TargetID;
    private final Transaction Transaction;
    
    public TransactionID( Transaction transaction )
    {
        this.Transaction = transaction;
        
        this.SenderID = Transaction.GetSender().getUniqueId();
        
        this.TargetID = Transaction.GetTarget().getUniqueId();
        
        this.ID = CreateID();
        
    }
    
    private String CreateID()
    {
        
        DateFormat DateFormatter = new SimpleDateFormat( "dd/mm/yyyy" );
        DateFormat TimeFormatter = new SimpleDateFormat( "HH:mm:ss" );
        Date Date = new Date();
        
        String TimeStamp = DateFormatter.format( Date ) + "@" + TimeFormatter.format( Date );
        
        return SenderID.toString() + ">" + TargetID.toString() + "-" + TimeStamp;
        
    }
    
    public String GetID()
    {
        
        return ID;
        
    }
    
}
