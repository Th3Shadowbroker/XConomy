package net.th3shadowbroker.XConomy.Commands;

import net.th3shadowbroker.XConomy.Blueprints.ATMInterface;
import net.th3shadowbroker.XConomy.Defaults.DefaultATMInterface;
import net.th3shadowbroker.XConomy.GUI.GUIItemBlocker;
import net.th3shadowbroker.XConomy.Loaders.Commands;
import net.th3shadowbroker.XConomy.Loaders.Events;
import net.th3shadowbroker.XConomy.Permissions.Permissions;
import net.th3shadowbroker.XConomy.Permissions.Permissions.XConomyPermission;
import net.th3shadowbroker.XConomy.main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AtmCommand implements CommandExecutor
{

    private final Commands loader;
    private final main XConomy;
    
    public AtmCommand( Commands loaderClass )
    {
        this.loader = loaderClass;
        this.XConomy = loaderClass.loader;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args ) 
    {
        
        if ( sender instanceof Player )
        {
            
            Player p = (Player) sender;
            
            if ( cmd.getName().equalsIgnoreCase( "ATM" ) )
            {
                if ( p.hasPermission( Permissions.GetPermission( XConomyPermission.ATM ) ) )
                {
                    
                    //Check for custom interface
                    if ( XConomy.CustomInterfaces.UseCustomIntefaces() )
                        {
                            //If interface really exist
                            if ( XConomy.CustomInterfaces.GetInterface() != null )
                            {
                                
                                ATMInterface CustomInterface = XConomy.CustomInterfaces.GetInterface();
                                GUIItemBlocker CustomInterfaceBlocker = new GUIItemBlocker( Events.getLoaderInstance(), CustomInterface );
                                CustomInterface.OpenTo( p, true );
                                return true;
                             
                            //Shit happens
                            } else {
                               
                                DefaultATMInterface defaultInterface = new DefaultATMInterface(p);
                                GUIItemBlocker defaultInterfaceBlocker = new GUIItemBlocker( Events.getLoaderInstance(), defaultInterface );
                                defaultInterface.OpenTo( p , true );
                                return true;
                                
                            }
                            
                            
                        //If custom interfaces are disabled
                        } else {
                            
                            DefaultATMInterface defaultInterface = new DefaultATMInterface(p);
                            GUIItemBlocker defaultInterfaceBlocker = new GUIItemBlocker( Events.getLoaderInstance(), defaultInterface );
                            defaultInterface.OpenTo( p , true );
                            return true;
                            
                        }

                } else {
                    
                    p.sendMessage( XConomy.ChatPrefix() + XConomy.lang.getText( "SystemATMCommandNotAllowed" ) );
                    return true;
                    
                }
            }
            
            return true;
            
        } else {
        
            XConomy.Console.write( "No interface for the console m8. Sorry :(" );
            return true;
        }
  
    }
    
}
