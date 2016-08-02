package net.th3shadowbroker.XConomy.Defaults.CalcInterface;

import net.th3shadowbroker.XConomy.Defaults.DefaultCalcInterface;
import net.th3shadowbroker.XConomy.GUI.GUIItemExtension;
import net.th3shadowbroker.XConomy.main;

public class OKItemAction extends GUIItemExtension
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
       try{
           
           XConomy.Console.write( "Transfer confirmed successfully!" );
           
           DefaultCalcInterface CurrentCalcInterface = (DefaultCalcInterface) CurrentInterface;
           
           //Check type and do the right things
           switch ( CurrentCalcInterface.GetCalcType() )
           {
               
               case DEPOSIT:
                   
                   XConomy.BankManager.Deposit( Player , CurrentCalcInterface.GetAmount() );
                   
               case WITHDRAW:
                   
                   XConomy.BankManager.Withdraw( Player , CurrentCalcInterface.GetAmount() );
                   
               case TRANSFER:
               
                    //Currently not implemented
                   
           }
           
           Player.closeInventory();
           
           
       } catch ( Exception ex ) {
           
           ex.printStackTrace();
           
       }
       
    }
    
}
