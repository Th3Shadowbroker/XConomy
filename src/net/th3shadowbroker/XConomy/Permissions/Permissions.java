package net.th3shadowbroker.XConomy.Permissions;

import org.bukkit.permissions.Permission;

public class Permissions 
{
    
    public enum XConomyPermission
    {
        ADMIN,
        USER,
        
        ATM
        
    }
    
    public static Permission GetPermission( XConomyPermission PermType )
    {
        if ( PermType == XConomyPermission.ADMIN )
        {
            return new Permission( "XConomy.admin" );
        } 
        
        if ( PermType == XConomyPermission.USER )
        {
            return new Permission( "XConomy.user" );
        }
        
        if ( PermType == XConomyPermission.ATM )
        {
            return new Permission( "XConomy.atm" );
        }
        
        return null;
        
    }
    
}
