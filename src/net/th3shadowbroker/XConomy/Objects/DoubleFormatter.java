package net.th3shadowbroker.XConomy.Objects;

import java.text.DecimalFormat;

public class DoubleFormatter 
{
    
    //Format double to decimal
    public static double Format( double in )
    {
        //Store input in array
        String[] tmp = String.valueOf( in ).split( "\\." );

        //Declerate a new string that will be used to format
        String FormatString = "";
        
        //For each number before the dot we add a new # for formatting
        for ( int i = 0; i != tmp.length; i++ )
        {
            FormatString = FormatString + "#";
        }
        
        //This is required to convert our double in decimal
        DecimalFormat df = new DecimalFormat( FormatString + ".##" );

        //Return the "decimalized" double from input
        return Double.parseDouble( df.format( in ).replaceAll( "," ,"." ) );
        
    }
    
    //Get numbers behind the comma
    public static int GetAfterCommaLength( double in )
    {
        
        String[] tmp = String.valueOf( in ).split( "\\." );
        
        return tmp[1].length();
        
    }
    
}
