package coinpurse;

import java.util.ResourceBundle;

/**
 * This class have responsible to create any kind of money.
 * 
 * @author Pasut Kittiprapas
 *
 */
public abstract class MoneyFactory {

	private static MoneyFactory factory = null;

	protected MoneyFactory() {
	}

	/**
	 * Get an instance of MoneyFactory. This method returns an object of a
	 * subclass.
	 * 
	 * @return an object of a subclass.
	 */
	public static MoneyFactory getInstance() {
		ResourceBundle bundle = ResourceBundle.getBundle("purse");
		String factoryClass = bundle.getString("moneyfactory");
		try {
			factory = (MoneyFactory) Class.forName(factoryClass).newInstance();
		} catch (ClassCastException e) {
			System.out.println(factoryClass + " is not type MoneyFactory");
		} catch (Exception ex) {
			System.out.println("Error creating MoneyFactory " + ex.getMessage());
		}
		if (factory == null) {
			System.exit(0);
		} else {
			setMoneyFactory(factory);
		}
		return factory;
	}

	/**
	 * Create new money object in the local currency.
	 * 
	 * @param value
	 * @throws IllegalArgumentException
	 */
	public abstract Valuable createMoney(double value);

	/**
	 * Accepts money value as a String and create money.
	 * 
	 * @param value
	 * @throws IllegalArgumentException
	 */
	public Valuable createMoney(String value) {
		double stringMoney;
		try {
			stringMoney = Double.parseDouble(value);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return createMoney(stringMoney);
	}

	/**
	 * 
	 * Set the MoneyFactory depending on the properties file.
	 *
	 * @param mf
	 */
	public static void setMoneyFactory(MoneyFactory mf) {
		factory = mf;

	}

}
