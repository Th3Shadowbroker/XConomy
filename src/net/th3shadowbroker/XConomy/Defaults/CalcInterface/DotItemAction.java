package net.th3shadowbroker.XConomy.Defaults.CalcInterface;

import net.th3shadowbroker.XConomy.Defaults.DefaultCalcInterface;
import net.th3shadowbroker.XConomy.GUI.GUIItemExtension;
import net.th3shadowbroker.XConomy.main;

public class DotItemAction extends GUIItemExtension
{

    private final main XConomy = main.getInstance();
    
    @Override
    public void OnConstruct() 
    {
        //Nothing to do here!
    }

    @Override
    public void Run() 
    {
        
        DefaultCalcInterface CurrentCalcInterface = (DefaultCalcInterface) CurrentInterface;
        
        if ( 
                /* Does not already contain a dot/comma */  !String.valueOf( CurrentCalcInterface.GetAmount() ).contains( "." ) &&
                /* Amount isn't equal to placeholder    */  !CurrentCalcInterface.getInventory().getItem(4).getItemMeta().getDisplayName().equals( "---" )
           )
        {
            
            CurrentCalcInterface.ForceUpdateAmount( "." );
            
        }
        
    }
    
}
