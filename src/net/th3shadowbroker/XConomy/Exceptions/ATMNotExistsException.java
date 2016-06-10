package net.th3shadowbroker.XConomy.Exceptions;

public class ATMNotExistsException extends Exception {
    
    public ATMNotExistsException()
    {
        //Nothing
    }
    
    public ATMNotExistsException( String message )
    {
        super( message );
    }
    
}
