package net.th3shadowbroker.XConomy.Loaders;

import net.th3shadowbroker.XConomy.Import.Essentials_Import;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.Bukkit;

public class Imports 
{
    
    private final main XConomy;
    
    //Construction
    public Imports( main XConomy )
    {
        
        this.XConomy = XConomy;
        
        this.CheckImports();
        
    }
    
    //Check for possible imports
    public void CheckImports()
    {
        
        //Import essentials data
        if ( Bukkit.getPluginManager().isPluginEnabled( "Essentials" ) )
        {
            Essentials_Import EssImport = new Essentials_Import();
            EssImport.OnImport();
        }
    
        
    }
    
}
