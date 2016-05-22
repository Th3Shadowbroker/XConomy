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
        
        
    }
    
}
