package net.th3shadowbroker.XConomy;

import java.io.File;
import net.th3shadowbroker.XConomy.ATM.Objects.ATMConfig;
import net.th3shadowbroker.XConomy.Addons.CustomInterfaceManager;
import net.th3shadowbroker.XConomy.Addons.OverrideManager;
import net.th3shadowbroker.XConomy.Bank.Bank;
import net.th3shadowbroker.XConomy.Bank.FeeManager;
import net.th3shadowbroker.XConomy.Cache.PlayerCache;
import net.th3shadowbroker.XConomy.Defaults.Transfer.TransferQueue;
import net.th3shadowbroker.XConomy.Loaders.Commands;
import net.th3shadowbroker.XConomy.Loaders.Config;
import net.th3shadowbroker.XConomy.Loaders.Events;
import net.th3shadowbroker.XConomy.Loaders.Imports;
import net.th3shadowbroker.XConomy.Loaders.Messages;
import net.th3shadowbroker.XConomy.Loaders.Thirdparty;
import net.th3shadowbroker.XConomy.Objects.DateDifferenceManager;
import net.th3shadowbroker.XConomy.Objects.DevTool;
import net.th3shadowbroker.XConomy.Objects.Language;
import net.th3shadowbroker.XConomy.System.Console;
import net.th3shadowbroker.XConomy.System.LogFile;
import net.th3shadowbroker.XConomy.Vault.VaultHook;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.HandlerList;
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

    //Register XConomy a global economy system
    public VaultHook VaultHook;
    
    //Manages custom ATM-Interfaces
    public CustomInterfaceManager CustomInterfaces;
    
    //Manages time differences
    public DateDifferenceManager DateManager;
    
    //Dev-tool (ONLY FOR DEVS!)
    public DevTool Debugger;
    
    //Load it up
    @Override
    public void onEnable()
    {
        instance = this;
        cache = new PlayerCache();
        Console = new Console( this );
        
        Console.write( "Initializing null variables..." );
        TransferQueue = new TransferQueue();
        Debugger = new DevTool( this );
        
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
        DateManager = new DateDifferenceManager( this );
        FeePayement = new FeeManager();
        CustomInterfaces = new CustomInterfaceManager( this );
          
        Console.write( "Loading ATM's..." );
        ATMConfig = new ATMConfig( this , "ATM" );
        
        Console.write( "Checking for vault..." );
        setupService();
        
        Console.write( "Checking for economy imports..." );
        Imports EconomyImports = new Imports( this );
        
        Console.write( "Checking for thirdparty-plugins..." );
        setupThirdparty();
   
        Console.write( "Everything done !" );
        
    }
    
    //Shut it down
    @Override
    public void onDisable()
    {
        
        saveConfig();

        DateManager.CreateRestorePoint();
        
        Console.write( "Killing remaining listeners..." );
        
        HandlerList Handles = new HandlerList();
        Handles.unregister( this );
        
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

    //Setup thirdparty-plugins
    private void setupThirdparty()
    {
        
        Thirdparty thirdparty = new Thirdparty( this );
        
    }
    
    //Setup vault
    private void setupService()
    {
        if ( this.getServer().getPluginManager().isPluginEnabled( "Vault" ) )
        {
            VaultHook = new VaultHook();
        } else {
            Console.write( "Vault not found. Please use XConomy-API" );
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
