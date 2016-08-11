package net.th3shadowbroker.XConomy.Import;

import java.io.File;
import java.util.UUID;
import net.th3shadowbroker.XConomy.Blueprints.EconomyImport;
import net.th3shadowbroker.XConomy.Exceptions.InvalidAmountException;
import net.th3shadowbroker.XConomy.Objects.Account;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class Essentials_Import extends EconomyImport
{

    private final main XConomy = main.getInstance();
    
    @Override
    public void OnImport() 
    {
        
        Plugin Essentials = Bukkit.getPluginManager().getPlugin( "Essentials" );
        
        File dir = new File( Essentials.getDataFolder(), "/userdata" );

        //For each userdata file in essentials
        for( File file : dir.listFiles() )
        {
            //Is YAML ?
            if ( file.getAbsolutePath().endsWith( ".yml" ) )
            {
                //Handle userdata file as YAML-Config
                FileConfiguration AccountFile = YamlConfiguration.loadConfiguration( file );
                
                //The account's id
                UUID AccountID = UUID.fromString( file.getName().replaceAll( ".yml" , "" ) );
                
                //Player has an EssentialsEco-Account
                if ( AccountFile.getString( "money" ) != null )
                {
                    //Player doesn't have an XConomy account (required to preven overrideing)
                    if ( !Account.accountExists( AccountID ) )
                    {
                        //Player's new account from XConomy
                        XConomy.Console.write( "Creating account: " + AccountID.toString() );

                        try {
                            
                            //Set new amount to XConomy-Account
                            Account.setByOverride( AccountID, Double.parseDouble( AccountFile.getString( "money" ) ) );
                            
                            //It's done!
                            XConomy.Console.write( AccountID.toString() + " imported from EssentialsEconomy" );
                            
                        } catch (InvalidAmountException ex) {
                            
                            //If old essentials-eco amount is invalid (negative or not a double)
                            try {
                                
                                //If something is going wrong balance will be set to 0.0
                                Account.setByOverride( AccountID, 0.0 );
                                
                            } catch (InvalidAmountException ex1) {
                                
                                //Impossible
                                
                            }
                            
                        }
                        
                    }
                }
                
            }
        }
        
    }
    
}
