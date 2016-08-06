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
        
        DefaultCalcInterface CurrentCalcInterface = (DefaultCalcInterface) CurrentInterface;
        
        if ( String.valueOf( CurrentCalcInterface.GetAmount() ).contains( "." ) )
        {
            
            String[] tmp = String.valueOf( CurrentCalcInterface.GetAmount() ).split( "." );
            
            if ( tmp.length >= 2 )
            {
                return;
            }
            
        }
            
        try{
        
            //Check if item is null-button item
            if ( CurrentItem.getPos() != 40 )
            {
                
                int CurrentAmount = CurrentItem.getAmount();

                CurrentCalcInterface.UpdateAmount( CurrentAmount );
                
            } else {
 
                CurrentCalcInterface.UpdateAmount( 0 );
                
            }
            
        } catch ( Exception ex ) {
        
            ex.printStackTrace();
                
        }
    }

}
