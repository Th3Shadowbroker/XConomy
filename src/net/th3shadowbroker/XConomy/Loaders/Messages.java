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
        
        //ATM messages
        lang.setTextOnce( "ATMTitle" , "&e%OWNER%s account" );
        lang.setTextOnce( "ATMAccountDescription" , "&2Current balance: %BALANCE%%SHORTNAME%" );
        lang.setTextOnce( "ATMItemDeposit" , "&2Deposit" );
        lang.setTextOnce( "ATMItemWithdraw" , "&cWithdraw" );
        lang.setTextOnce( "ATMItemTransfer" , "&9Transfer" );
        
        //User messages
        lang.setTextOnce( "UserBalanceCommand" , "&9Balance: %BALANCE%");
        lang.setTextOnce( "UserOthersBalanceCommand" , "&9%PLAYER%'s balance: %BALANCE%");
        
    }
    
}
