package net.th3shadowbroker.XConomy;

import java.io.File;
import net.th3shadowbroker.XConomy.Loaders.Commands;
import net.th3shadowbroker.XConomy.Loaders.Config;
import net.th3shadowbroker.XConomy.Loaders.Events;
import net.th3shadowbroker.XConomy.Loaders.Messages;
import net.th3shadowbroker.XConomy.Objects.Language;
import net.th3shadowbroker.XConomy.System.Console;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {
    
    private String ConsolePrefix = "[XConomy] ";
    private String ChatPrefix = "§b[XConomy] ";
    public Console Console;
    public FileConfiguration Config;
    public Language lang;
    
    //Load it up
    @Override
    public void onEnable()
    {
        
        Console = new Console( this );
        
        Console.write( "Loading config..." );
        Config = getConfig();
        loadConfig();
        
        Console.write( "Loading prefixes..." );
        setPrefix();
        
        Console.write( "Loading events..." );
        registerEvents();
        
        Console.write( "Loading commands..." );
        registerCommands();
        
        Console.write( "Loading language..." );
        lang = new Language( this , new File( this.getDataFolder() , "messages.yml" ) );
        loadMessages();
        
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
    
}
