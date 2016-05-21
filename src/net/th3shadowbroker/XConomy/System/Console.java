package net.th3shadowbroker.XConomy.System;

import net.th3shadowbroker.XConomy.main;

public class Console {

   private main plugin;
   
   //Construction
   public Console( main plugin )
   {
       
       this.plugin = plugin;
       
   }
   
   //Send message to console
   public void write( String text )
   {
       
       System.out.println( plugin.ConsolePrefix() + text );
       
   }
    
}
