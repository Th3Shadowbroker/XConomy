package net.th3shadowbroker.XConomy.Loaders;

import net.th3shadowbroker.XConomy.Objects.Language;
import net.th3shadowbroker.XConomy.main;

public class Messages {
    
    private final main loader;
    
    //Construction
    public Messages( main loaderClass )
    {
        
        this.loader = loaderClass;
        
        this.setup();
        
    }
    
    //Setup language-file
    private void setup()
    {
        
        Language lang = loader.lang;
        
        //Money transfer
        lang.setTextOnce( "MoneyTransferToSender" , "&2Transfer successfull (%AMOUNT% %SHORTNAME%)" );
        lang.setTextOnce( "MoneyTransferToTarget" , "&2%SENDER% transferred %AMOUNT% to your account" );

        //Account messages
        lang.setTextOnce( "AccountCreated" , "&2Hey %PLAYER%, you're account has been created :)" );
        lang.setTextOnce( "AccountNotFound" , "&cSorry but there is no account owned by %OWNER%" );
        lang.setTextOnce( "AccountBalanceSet" , "&2%PLAYER%'s balance was set to %AMOUNT% %FULLNAME%" );
        lang.setTextOnce( "AccountMoneyAdded" , "&2%AMOUNT% credits were added to %PLAYER%'s account" );
        lang.setTextOnce( "AccountMoneyRemoved" , "&2%AMOUNT% credits were removed from %PLAYER%'s account" );
        lang.setTextOnce( "AccountInvalidAmount" , "&c%AMOUNT% is not a valid amount" );
        lang.setTextOnce( "AccountNotEnoughMoney" , "&cThere is not enough money on %PLAYER%'s account" );
        lang.setTextOnce( "AccountNotEnoughMoneyToOwner" , "&cYou don't have enough money" );
        lang.setTextOnce( "AccountNotEnoughMoneyToOwnerBank" , "&cYou don't have enough money on your bank" );
        
        //ATM messages
        lang.setTextOnce( "ATMTitle" , "&e%OWNER%s account" );
        lang.setTextOnce( "ATMAccountDescription" , "&2Current balance: %BALANCE%%SHORTNAME%" );
        
        lang.setTextOnce( "ATMItemDeposit" , "&2Deposit" );
        lang.setTextOnce( "ATMItemCustomDeposit" , "&2Custom deposit" );
        
        lang.setTextOnce( "ATMItemWithdraw" , "&cWithdraw" );
        lang.setTextOnce( "ATMItemCustomWithdraw" , "&cCustom withdraw" );
        
        lang.setTextOnce( "ATMItemTransfer" , "&9Transfer" );
        lang.setTextOnce( "ATMItemCustomTransfer" , "&9Custom transfer" );
        
        //Transfer messages
        lang.setTextOnce( "Transfer.TypeTarget" , "&2Please type in the target" );
        lang.setTextOnce( "Transfer.TypeAmount" , "&2Please type in the amount you want to send");
        lang.setTextOnce( "Transfer.OutOfRange" , "&cTransfer cancelled" );
        lang.setTextOnce( "Transfer.CancelMessage" , "&cYou are already started a transaction. Type # to cancel." );
        
        //User messages
        lang.setTextOnce( "UserBalanceCommand" , "&9Balance: %BALANCE%");
        lang.setTextOnce( "UserOthersBalanceCommand" , "&9%PLAYER%'s balance: %BALANCE%");
        
        //System messages
        lang.setTextOnce( "SystemUniErrMsg" , "&cThere was an error. See console." );
        lang.setTextOnce( "SystemATMBlockDestruction" , "&cATM's can't be destroyed" );
        lang.setTextOnce( "SystemATMExists" , "&cThere is already an ATM at this location" );
        lang.setTextOnce( "SystemATMCreated" , "&2ATM created successfully at this location" );
        lang.setTextOnce( "SystemATMRemoved" , "&cATM removed successfully from this location" );
        lang.setTextOnce( "SystemATMNotFound" , "&cATM not found" );
        lang.setTextOnce( "SystemPlayerSearchingDatabase" , "&2Please wait... searching database..." );
        
    }
    
}
