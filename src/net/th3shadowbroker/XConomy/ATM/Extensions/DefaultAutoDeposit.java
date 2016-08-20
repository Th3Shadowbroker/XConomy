package net.th3shadowbroker.XConomy.ATM.Extensions;

import net.th3shadowbroker.XConomy.GUI.GUIItemExtension;
import net.th3shadowbroker.XConomy.main;

public class DefaultAutoDeposit extends GUIItemExtension
{
    
    private main XConomy;

    @Override
    public void OnConstruct() 
    {
        
        this.XConomy = main.getInstance();
        
    }

    @Override
    public void Run() 
    {

            String UnfilteredName =  XConomy.lang.getText("ATMItemDeposit") + ": ";
            String FilteredName = CurrentItem.getName().replaceAll( UnfilteredName , "");
            
            XConomy.BankManager.Deposit( Player , Double.parseDouble( FilteredName ) );
            
            Player.closeInventory();
            
    }
    
}
