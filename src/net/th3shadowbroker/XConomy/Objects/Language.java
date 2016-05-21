package net.th3shadowbroker.XConomy.Objects;

import java.io.File;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Language {

    private final main plugin;
    private final File langFile;
    private final FileConfiguration lang;
    
    //Construction
    public Language( main plugin, File config )
    {
        
        this.plugin = plugin;
        this.langFile = config;
        this.lang = YamlConfiguration.loadConfiguration( langFile );
        
    }
    
    //Save lang file
    private void save()
    {
        try {
        
            lang.save( langFile );
            
        } catch ( Exception ex ) {
            
            ex.printStackTrace();
            
        }
    }
    
    //Set default message
    public void setTextOnce( String path , String text )
    {
        if ( lang.getString( path ) == null )
        {
            
            lang.set( path , text );
            
            this.save();
            
        }
    }
    
    //Get message from message-file
    public String getText( String path )
    {
        if ( lang.getString( path ) != null )
        {
            
            return lang.getString( path ).replaceAll( "&" , "§" );
            
        } else {
            
            return "&cThis sentence does not exist. Please check lang-file message ".replaceAll( "&" , "§" ) + path ;
            
        }
    }
    
}
