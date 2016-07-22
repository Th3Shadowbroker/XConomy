package net.th3shadowbroker.XConomy.Defaults;

import java.util.ArrayList;
import net.th3shadowbroker.XConomy.Blueprints.ATMInterface;
import net.th3shadowbroker.XConomy.GUI.GUIItemAction;
import net.th3shadowbroker.XConomy.GUI.GUIItemStack;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class DefaultATMInterface extends ATMInterface
{

    private final Player player;
    
    public DefaultATMInterface( Player player )
    {
        
        //Setup defaults
        this.SetupDefaults();
        
        this.player = player;
        
    }
    
    @Override
    public void registerActionEvents() {
        //Currently nothing to do here
    }
    
    private void SetupDefaults()
    {
        
        //An array-list to store our items
        ArrayList<GUIItemStack> Elements = new ArrayList<GUIItemStack>();
        
        //Decleration of the Deposit Items
        GUIItemStack DepositItem = new GUIItemStack( Material.EMERALD, XConomy.lang.getText( "ATMItemDeposit" ), null , 1, 1 ); Elements.add( DepositItem );
        GUIItemAction DepositAction = new GUIItemAction( DepositItem, this, null );
        
        //Decleration of the Withdraw Items
        GUIItemStack WithdrawItem = new GUIItemStack( Material.PAPER, XConomy.lang.getText( "ATMItemWithdraw" ), null , 1, 7 ); Elements.add( WithdrawItem );
        GUIItemAction WithdrawAction = new GUIItemAction( WithdrawItem, this.GetATMInterface(), null );
        
        //Decleration of the Transfer Items
        GUIItemStack TransferItem = new GUIItemStack( Material.BOOK_AND_QUILL, XConomy.lang.getText( "ATMItemTransfer" ), null, 1, 4 ); Elements.add( TransferItem );
        GUIItemAction TransferAction = new GUIItemAction( TransferItem, this.GetATMInterface(), null );
        
        //At the end we add all items to our brand new interface
        this.AddElements( Elements );
        
    }
    
}
