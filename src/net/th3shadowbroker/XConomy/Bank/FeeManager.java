package net.th3shadowbroker.XConomy.Bank;

import java.util.Timer;
import java.util.TimerTask;
import net.th3shadowbroker.XConomy.main;

public class FeeManager 
{

    private final main XConomy = main.getInstance();
    private final int MultiplyNumber;
    
    public FeeManager()
    {
        
        this.MultiplyNumber = XConomy.Config.getInt( "Bank.FeeDelay" );
        
        this.SetupTimer();
        
    }
    
    private void SetupTimer()
    {
        
        Timer FeeTimer = new Timer();
        
        TimerTask FeeTask = new TimerTask()
        {
            @Override
            public void run()
            {
                XConomy.BankManager.PayFees();
            }
        };
        
        FeeTimer.schedule( FeeTask, 0, 1000 * 60 * 60 * this.MultiplyNumber );
        XConomy.Console.write( "Fee timer is now active and will pay every " + this.MultiplyNumber + " hours..." );
        
    }
    
}
