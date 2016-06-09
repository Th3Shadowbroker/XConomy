package net.th3shadowbroker.XConomy.Cache;

//Cache states
public enum CacheState
{
    
    //Default player state
    NORMAL,
    
    //Waiting for bank creation
    WAIT_CREATE_BANK,
    
    //Waiting for bank sign creation
    WAIT_CREATE_SIGN,
    
    //Waiting for transfer terminal creation
    WAIT_CREATE_TERMINAL,
    
    //Something else
    OTHER
    
}
