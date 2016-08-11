package net.th3shadowbroker.XConomy.MYSQL;

public class MySQLLogin 
{
    
    private final String Host;      //DB-Host
    private final String Username;  //DB-User
    private final String Password;  //DB-Password
    private final String Database;  //DB-Database
    private final String Table;     //DB-Table
    
    //Types of login-data
    public enum LoginData
    {
        HOST,
        USERNAME,
        PASSWORD,
        DATABASE,
        TABLE
    }
    
    //Construction
    public MySQLLogin( String host, String username, String password, String database, String table )
    {
        this.Host = host;
        this.Username = username;
        this.Password = password;
        this.Database = database;
        this.Table = table;
    }
    
    //Get login-data
    public String GetData( LoginData DataType )
    {
        switch( DataType )
        {
            case HOST:
                return this.Host;
            case USERNAME:
                return this.Username;
            case PASSWORD:
                return this.Password;
            case DATABASE:
                return this.Database;
            case TABLE:
                return this.Table;
        }
        
        return null;
        
    }
    
}
