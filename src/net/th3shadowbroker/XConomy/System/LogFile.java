package net.th3shadowbroker.XConomy.System;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogFile 
{
    
    private final File File;
    
    //Constructions
    public LogFile( File file )
    {
        
        this.File = file;
        
        if ( !File.exists() )
        {
            try {
                
                File.createNewFile();
                
            } catch (IOException ex) {
                
                ex.printStackTrace();
                
            }
        }
        
    }
    
    public void WriteEntry( String LogEntry )
    {
        try {
            
            PrintWriter LogWriter = new PrintWriter( File.getAbsolutePath() );
            
            LogWriter.println( LogEntry );
            
            LogWriter.close();
            
        } catch (FileNotFoundException ex) {
            
            ex.printStackTrace();
            
        }
    }
    
}
