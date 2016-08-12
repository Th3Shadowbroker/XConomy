package net.th3shadowbroker.XConomy.Commands.Arguments;

import java.io.File;
import net.th3shadowbroker.XConomy.Blueprints.CommandArgument;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class XConTest extends CommandArgument
{

    private final main XConomy = main.getInstance();
    
    @Override
    public void RunAction() 
    {
        if ( arguments[0].equalsIgnoreCase( "test" ) )
        {
            
            XConomy.Console.write( "< Starting XConomy-Test >" );
            
            for ( String ConfigEntry : XConomy.Config.getKeys(true) )
            {
                XConomy.Console.write( "Setting loaded-> " + ConfigEntry );
            }
            
            FileConfiguration AccountsCfg = YamlConfiguration.loadConfiguration( new File( XConomy.getDataFolder(), "accounts.yml" ) );
            
            for ( String Account : AccountsCfg.getKeys(true) )
            {
                XConomy.Console.write( "Account found-> " + Account );
            }
            
            
            FileConfiguration BankCfg = YamlConfiguration.loadConfiguration( new File( XConomy.getDataFolder(), "bank.yml" ) );
            
            for ( String BankAccount : BankCfg.getKeys(true) )
            {
                XConomy.Console.write( "Bankaccount found-> " + BankAccount );
            }
            
            XConomy.DateManager.Test();

            player.sendMessage( XConomy.ChatPrefix() + "§2Please check console for test-report" );
            
        }
    }
    
}
