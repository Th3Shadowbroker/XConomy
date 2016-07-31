package net.th3shadowbroker.XConomy.Defaults.CalcInterface;

import net.th3shadowbroker.XConomy.Defaults.DefaultCalcInterface;
import net.th3shadowbroker.XConomy.GUI.GUIItemExtension;

public class NumberItemAction extends GUIItemExtension
{

    @Override
    public void OnConstruct() 
    {
        
        //Nothing to do here!
        
    }

    @Override
    public void Run() 
    {
        try{
        
            //Check if item is null-button item
            if ( CurrentItem.getPos() != 40 )
            {
                
                int CurrentAmount = CurrentItem.getAmount();
                
                DefaultCalcInterface CurrentCalcInterface = (DefaultCalcInterface) CurrentInterface;
                
                CurrentCalcInterface.UpdateAmount( CurrentAmount );
                
            } else {
                
                DefaultCalcInterface CurrentCalcInterface = (DefaultCalcInterface) CurrentInterface;
                
                CurrentCalcInterface.UpdateAmount( 0 );
                
            }
            
        } catch ( Exception ex ) {
        
            ex.printStackTrace();
                
        }
    }

}
