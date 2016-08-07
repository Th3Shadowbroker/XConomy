package net.th3shadowbroker.XConomy.Exceptions;

public class AccountNotFoundException extends Exception
{
    
    public AccountNotFoundException()
    {
        //Nothing to do here!
    }
    
    public AccountNotFoundException( String message )
    {
        super( message );
    }
    
}
