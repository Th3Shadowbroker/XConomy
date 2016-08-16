package net.th3shadowbroker.Thirdparty.Citizens.Objects;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.citizensnpcs.api.npc.NPC;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Thirdparty_Config_Citizens 
{
    
    private final File ConfigFile;
    private final FileConfiguration Config;
    
    //Construction
    public Thirdparty_Config_Citizens()
    {
        
        this.ConfigFile = new File( main.getInstance().getDataFolder(), "thirdparty/Citizens.yml" );
        
        this.Config = YamlConfiguration.loadConfiguration( this.ConfigFile );
        
        this.Setup();
        
    }
    
    //Save a new NPC to config
    public void SaveNPC( NPC npc )
    {
        
        List<String> ExistingContent = Config.getStringList( "NPCS" );
        
        if ( !ExistingContent.contains( npc.getName() + String.valueOf( npc.getId() ) ) )
        {
            ExistingContent.add( npc.getName() + String.valueOf( npc.getId() ) );
        }
        
        Config.set( "NPCS" , ExistingContent );
        
        this.Save();
        
    }
    
    //Remove an NPC from config
    public void RemoveNPC( NPC npc )
    {
        
        List<String> ExistingContent = Config.getStringList( "NPCS" );
        
        if ( ExistingContent.contains( npc.getName() + String.valueOf( npc.getId() ) ) )
        {
            ExistingContent.remove( npc.getName() + String.valueOf( npc.getId() ) );
        }
        
        Config.set( "NPCS" , ExistingContent );
        
        this.Save();
        
    }
    
    //NPC exists ?
    public boolean NPCRegistered( NPC npc )
    {
        
        String NPCCode = npc.getName() + String.valueOf( npc.getId() );
        
        return Config.getStringList( "NPCS" ).contains( NPCCode );
        
    }
    
    //Setup config
    private void Setup()
    {
        
        Config.options().header( "DON'T EDIT THIS FILE!" );
        
        Save();
        
    }
    
    //Save config
    private void Save()
    {
        
        try {
            Config.save( ConfigFile );
        } catch (IOException ex) {
            Logger.getLogger(Thirdparty_Config_Citizens.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
