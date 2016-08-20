package net.th3shadowbroker.XConomy.Bank;

import java.util.Timer;
import java.util.TimerTask;
import net.th3shadowbroker.XConomy.main;

public class FeeManager 
{

    private final main XConomy = main.getInstance();

    public FeeManager()
    {

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
                //Manage resetting
                if ( XConomy.DateManager.GetCurrent() <= 0 )
                {
                    XConomy.Console.write( "Resetting fee-timer" );
                    /*   Pay feed to everybody   */ XConomy.BankManager.PayFees();
                    /*   Reset counter   */         XConomy.DateManager.Reset();

                } else {
                    
                    /*   Send -1 to counter   */    XConomy.DateManager.SendDown();
                    
                }
                
            }
        };
        
        FeeTimer.schedule( FeeTask, 0, 60 * 20 );
        XConomy.Console.write( "Fee timer active..." );
        
    }
    
}
