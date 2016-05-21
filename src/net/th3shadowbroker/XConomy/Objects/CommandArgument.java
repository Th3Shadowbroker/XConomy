package net.th3shadowbroker.XConomy.Objects;

public abstract class CommandArgument {
    
    private final String[] args;
    
    public CommandArgument( String[] args )
    {
        this.args = args;
    }
    
    public abstract void RunAction();
 
    
}
