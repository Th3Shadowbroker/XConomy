package net.th3shadowbroker.XConomy.Objects;

import java.util.ArrayList;

public class ColorRemover 
{
    
    private static ArrayList<String> ColorCodes;
    
    private static void LoadColorCodes()
    {
        //Decimal color-codes
        ColorCodes.add( "§0" );
        ColorCodes.add( "§1" );
        ColorCodes.add( "§2" );
        ColorCodes.add( "§3" );
        ColorCodes.add( "§4" );
        ColorCodes.add( "§5" );
        ColorCodes.add( "§6" );
        ColorCodes.add( "§7" );
        ColorCodes.add( "§8" );
        ColorCodes.add( "§9" );
        
        //Hex color-codes
        ColorCodes.add( "§a" );
        ColorCodes.add( "§b" );
        ColorCodes.add( "§c" );
        ColorCodes.add( "§d" );
        ColorCodes.add( "§e" );
        ColorCodes.add( "§f" );
        
    }
    
    //Remove all known color-codes
    public static String RemoveColorFrom( String input )
    {
        LoadColorCodes();
        
        String tmp = input;
        
        for ( String ColorCode : ColorCodes )
        {
            if ( tmp.contains( ColorCode ) )
            {
                tmp = tmp.replaceAll( ColorCode , "" );
            }
        }
        
        return tmp;
        
    }
    
}
