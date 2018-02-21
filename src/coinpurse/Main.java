package coinpurse;

import java.util.ResourceBundle;

/**
 * A main class to create objects and connect objects together.
 * The user interface needs a reference to coin purse.
 * @author Pasut Kittiprapas
 */
public class Main {
	
	private static final String THAICURRENCY = "BAHT";
	private static final String MALAYCURRENCY = "RINGGIT";
	
    /**
     * Configure and start the application.
     * @param args not used
     */
    public static void main( String[] args ) {
    	
    	
		Purse purse = new Purse(10);
		ResourceBundle bundle = ResourceBundle.getBundle("purse");
		String factoryclass = bundle.getString("moneyfactory");
		ConsoleDialog consoleDialog;
		if(factoryclass.equalsIgnoreCase("coinpurse.ThaiMoneyFactory")){
			consoleDialog = new ConsoleDialog(purse,THAICURRENCY);
		}
		else{
			consoleDialog = new ConsoleDialog(purse,MALAYCURRENCY);
		}
		
		consoleDialog.run();

    }
 }
