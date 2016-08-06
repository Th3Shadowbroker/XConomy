package net.th3shadowbroker.XConomy;

import java.io.File;
import net.th3shadowbroker.XConomy.ATM.Objects.ATMConfig;
import net.th3shadowbroker.XConomy.Addons.OverrideManager;
import net.th3shadowbroker.XConomy.Bank.Bank;
import net.th3shadowbroker.XConomy.Bank.FeeManager;
import net.th3shadowbroker.XConomy.Cache.PlayerCache;
import net.th3shadowbroker.XConomy.Defaults.Transfer.TransferQueue;
import net.th3shadowbroker.XConomy.Loaders.Commands;
import net.th3shadowbroker.XConomy.Loaders.Config;
import net.th3shadowbroker.XConomy.Loaders.Events;
import net.th3shadowbroker.XConomy.Loaders.Messages;
import net.th3shadowbroker.XConomy.Objects.Language;
import net.th3shadowbroker.XConomy.Objects.Plugins;
import net.th3shadowbroker.XConomy.System.Console;
import net.th3shadowbroker.XConomy.System.LogFile;
import net.th3shadowbroker.XConomy.Vault.VaultPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {
    
    private String ConsolePrefix = "[XConomy] ";
    private String ChatPrefix = "§b[XConomy] ";
    
    //XConomy's current instance
    private static main instance;
    
    //Object for lazy devs like me
    public Console Console;
    
    //XConomy's config
    public FileConfiguration Config;
    
    //Stores ATM-Informations
    public ATMConfig ATMConfig;
    
    //Handler for custom messages
    public Language lang;
    
    //Logs every transaction (if enabled)
    public LogFile TransactionLog;
    
    //The cache is storing players state-info
    private PlayerCache cache;
    
    //Manages the bank
    public Bank BankManager;
    
    //Manages overrides from external plugins
    public OverrideManager Overrides;
    
    //Manages the fees for every account
    public FeeManager FeePayement;
    
    //Stores players who are currently waiting for money transfer
    public TransferQueue TransferQueue;

    //Load it up
    @Override
    public void onEnable()
    {
        instance = this;
        cache = new PlayerCache();
        Console = new Console( this );
        
        Console.write( "Loading config..." );
        Config = getConfig();
        loadConfig();
        TransactionLog = new LogFile( new File( this.getDataFolder(), "transactions.log" ) );
        
        Console.write( "Loading prefixes..." );
        setPrefix();
        
        Console.write( "Loading events..." );
        registerEvents();
        
        Console.write( "Loading commands..." );
        registerCommands();
        
        Console.write( "Loading language..." );
        lang = new Language( this , new File( this.getDataFolder() , "messages.yml" ) );
        loadMessages();
        
        Console.write( "Loading bank-runtime..." );
        BankManager = new Bank( this, new File( this.getDataFolder(), "bank.yml" ) );
        FeePayement = new FeeManager();
        
        Console.write( "Loading ATM's..." );
        ATMConfig = new ATMConfig( this , "ATM" );
        
        Console.write( "Checking for vault..." );
        setupService();
   
        Console.write( "Everything done !" );
        
    }
    
    //Shut it down
    @Override
    public void onDisable()
    {
        
        saveConfig();
        Console.write( "XConomy disabled !" );
        
    }
    
    //Register all required commands
    private void registerCommands()
    {
        
        Commands commands = new Commands( this );
        
    }
    
    //Register all required events
    private void registerEvents()
    {
        
        Events events = new Events( this );
        
    }
    
    //Setup the prefix
    private void setPrefix()
    {
        
        ConsolePrefix = Config.getString( "ConsolePrefix" ) + " ";
        
        ChatPrefix = Config.getString( "ChatPrefix" ).replaceAll( "&" , "§" ) + "§f ";
        
    }
    
    //Setup the configuration
    private void loadConfig()
    {
        
        Config config = new Config( this );
        
    }
    
    //Setup messages
    private void loadMessages()
    {
        
        Messages messages = new Messages( this );
        
    }

    //Setup vault
    private void setupService()
    {
        if ( Plugins.VaultIsEnabled() )
        {
            VaultPlugin VaultLoader = new VaultPlugin();
        }
    }
    
    //Get console-prefix
    public String ConsolePrefix()
    {
        
        return this.ConsolePrefix;
        
    }
    
    //Get chat-prefix
    public String ChatPrefix()
    {
        
        return this.ChatPrefix;
        
    }
   
    //Get current instances cache
    public PlayerCache getCache()
    {
        return cache;
    }
    
    //Return current instance
    public static main getInstance()
    {
        return instance;
    }
    
}
