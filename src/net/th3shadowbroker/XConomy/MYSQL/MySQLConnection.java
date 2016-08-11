package net.th3shadowbroker.XConomy.MYSQL;

import java.io.File;
import java.io.IOException;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class MySQLConnection 
{
    private final FileConfiguration Config;
    private final File ConfigFile;
    private final main XConomy;
    private final MySQLLogin Login;
    //private MySQL Connection; //<-- Continue here!
    
    //Construction
    public MySQLConnection( File file )
    {
        this.XConomy = main.getInstance();
        this.ConfigFile = file;
        this.Config = YamlConfiguration.loadConfiguration( new File( XConomy.getDataFolder() , file.getName() ) );
        this.SetupConfig();
        
        Login = new MySQLLogin(
                                    this.Config.getString( "MySQL.Host" ),
                                    this.Config.getString( "MySQL.Username" ),
                                    this.Config.getString( "MySQL.Password" ),
                                    this.Config.getString( "MySQL.Database" ),
                                    this.Config.getString( "MySQL.Table" )
                               );
        
        if ( Config.getBoolean( "Enabled" ) )
        {
            StartConnection();
        }
        
    }
    
    //Setup config
    private void SetupConfig()
    {
        
        Config.options().header( "XConomy MySQL-Sync. Configuration" );
        
        Config.addDefault( "Enabled" , false );
        Config.addDefault( "MySQL.Host" , "Host" );
        Config.addDefault( "MySQL.Username" , "User" );
        Config.addDefault( "MySQL.Password" , "Password" );
        Config.addDefault( "MySQL.Database" , "Database" );
        Config.addDefault( "MySQL.Table" , "XConomy" );
        
        Config.options().copyDefaults( true );
        
        this.SaveConfig();
        
    }
    
    //Save config
    private void SaveConfig()
    {
        try {
            Config.save( ConfigFile );
        } catch (IOException ex) {
            XConomy.Console.write( ex.getMessage() );
        }
    }
    
    //Start connection
    private void StartConnection()
    {
        
    }
    
}
