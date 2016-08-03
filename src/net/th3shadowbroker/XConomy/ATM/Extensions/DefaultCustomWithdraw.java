package net.th3shadowbroker.XConomy.ATM.Extensions;

import net.th3shadowbroker.XConomy.Defaults.DefaultCalcInterface;
import net.th3shadowbroker.XConomy.GUI.GUIItemExtension;

public class DefaultCustomWithdraw extends GUIItemExtension
{

    @Override
    public void OnConstruct() 
    {
        
        //Nothing to do here!
        
    }

    @Override
    public void Run() 
    {
        
        DefaultCalcInterface CustomAmount = new DefaultCalcInterface( Player , DefaultCalcInterface.CalcType.WITHDRAW );
                
        CustomAmount.OpenTo( Player, true );
        
    }
    
}
