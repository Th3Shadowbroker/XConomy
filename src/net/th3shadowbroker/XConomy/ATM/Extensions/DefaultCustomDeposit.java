package net.th3shadowbroker.XConomy.ATM.Extensions;

import net.th3shadowbroker.XConomy.Defaults.DefaultCalcInterface;
import net.th3shadowbroker.XConomy.GUI.GUIItemExtension;

public class DefaultCustomDeposit extends GUIItemExtension
{

    @Override
    public void OnConstruct() 
    {
        
        // Nothing
        
    }

    @Override
    public void Run() 
    {
       
                DefaultCalcInterface CustomAmount = new DefaultCalcInterface( Player , DefaultCalcInterface.CalcType.DEPOSIT );
                
                CustomAmount.OpenTo( Player, true );
        
    }
    
}
