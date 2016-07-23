/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.th3shadowbroker.XConomy.ATM.Extensions;

import net.th3shadowbroker.XConomy.Exceptions.InvalidAmountException;
import net.th3shadowbroker.XConomy.Exceptions.NotEnoughMoneyException;
import net.th3shadowbroker.XConomy.GUI.GUIItemExtension;
import net.th3shadowbroker.XConomy.Objects.Account;
import net.th3shadowbroker.XConomy.main;

/**
 *
 * @author Jens
 */
public class DefaultAutoDeposit extends GUIItemExtension
{
    
    private main XConomy;

    @Override
    public void OnConstruct() 
    {
        
        this.XConomy = main.getInstance();
        
    }

    @Override
    public void Run() 
    {
        
        try {
            
            String UnfilteredName =  XConomy.lang.getText("ATMItemDeposit") + ": ";
            String FilteredName = CurrentItem.getName().replaceAll( UnfilteredName , "");
            
            Account TargetAccount = new Account( XConomy, Player );
            TargetAccount.removeMoney( Integer.parseInt( FilteredName ) );
            
            /*
             *      ADD TO BANK STUFF
             */
            
        } catch (NotEnoughMoneyException ex) {
            
            Player.sendMessage( XConomy.ChatPrefix() + XConomy.lang.getText( "AccountNotEnoughMoneyToOwner" ) );
            
        } catch (InvalidAmountException ex) {
            
            Player.sendMessage( XConomy.ChatPrefix() + XConomy.lang.getText( "SystemUniErrMsg" ) );
            XConomy.Console.write( ex.getMessage() );
            ex.printStackTrace();
            
        }

    }
    
}
