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
    	Main.init();
    }
    
    public static void init(){
    	MoneyFactory instance = null;
		Purse purse = new Purse(10);
		ResourceBundle bundle = ResourceBundle.getBundle("purse");
		String factoryclass = bundle.getString("moneyfactory");
		try {
			instance = (MoneyFactory) Class.forName(factoryclass).newInstance();
		} catch (ClassCastException cce) {
			System.out.println(factoryclass + " is not type MoneyFactory");
		} catch (Exception ex) {
			System.out.println("Error creating MoneyFactory " + ex.getMessage());
		}
		if (instance == null)
			System.exit(1);
		MoneyFactory.setMoneyFactory(instance);
		ConsoleDialog consoleDialog;
		if (factoryclass.equalsIgnoreCase("coinpurse.MalayMoneyFactory")) {
			consoleDialog = new ConsoleDialog(purse, MALAYCURRENCY);
		} else {
			consoleDialog = new ConsoleDialog(purse, THAICURRENCY);
		}

		consoleDialog.run();
    }
 }
