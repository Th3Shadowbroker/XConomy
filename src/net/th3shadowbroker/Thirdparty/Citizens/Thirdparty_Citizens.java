package net.th3shadowbroker.Thirdparty.Citizens;

import net.th3shadowbroker.Thirdparty.Citizens.Events.CreateRemoveBankNPC;
import net.th3shadowbroker.Thirdparty.Citizens.Events.NPCAutoRemove;
import net.th3shadowbroker.Thirdparty.Citizens.Events.OpenBankNPCInterface;
import net.th3shadowbroker.XConomy.main;
import net.th3shadowbroker.Thirdparty.Citizens.Objects.Thirdparty_Config_Citizens;

public class Thirdparty_Citizens 
{
    
    public final main XConomy;
    public final Thirdparty_Config_Citizens Config;
    
    //Construction
    public Thirdparty_Citizens( main plugin )
    {

        this.XConomy = plugin;
        
        XConomy.Console.writeAPI( "Citizens detected and hooked..." );
        
        this.Config = new Thirdparty_Config_Citizens();
        
        this.RegisterCitizens();
        
    }

    //Hook citizens and register components
    private void RegisterCitizens()
    {
        
        //Register event to create/remove npc-banks
        XConomy.getServer().getPluginManager().registerEvents( new CreateRemoveBankNPC(this) , XConomy );
        
        //Register event to open interface
        XConomy.getServer().getPluginManager().registerEvents( new OpenBankNPCInterface(this) , XConomy );
        
        //Register event to remove NPC-Bank if there NPC has been deleted
        XConomy.getServer().getPluginManager().registerEvents( new NPCAutoRemove(this) , XConomy);
        
    }
    
}
