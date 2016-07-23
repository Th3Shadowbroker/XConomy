package net.th3shadowbroker.XConomy.ATM.Extensions;

import net.th3shadowbroker.XConomy.Exceptions.NotEnoughMoneyException;
import net.th3shadowbroker.XConomy.GUI.GUIItemExtension;
import net.th3shadowbroker.XConomy.main;

public class DefaultAutoWithdraw extends GUIItemExtension
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

            String UnfilteredName =  XConomy.lang.getText("ATMItemWithdraw") + ": ";
            String FilteredName = CurrentItem.getName().replaceAll( UnfilteredName , "");
            
            try {

                XConomy.BankManager.Withdraw( Player , Double.parseDouble( FilteredName ) );

            } catch (NotEnoughMoneyException ex) {

                Player.sendMessage( XConomy.ChatPrefix() + XConomy.lang.getText( "AccountNotEnoughMoneyToOwner" ) );

            }
            
            Player.closeInventory();

    }
    
}
