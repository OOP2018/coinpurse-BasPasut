package coinpurse;

/**
 * This class have responsible to create any kind of money.
 * 
 * @author Pasut Kittiprapas
 *
 */
public abstract class MoneyFactory {

	private static MoneyFactory factory;

	/**
	 * Get an instance of MoneyFactory. This method returns an object of a
	 * subclass.
	 * 
	 * @return an object of a subclass.
	 */
	public static MoneyFactory getInstance() {
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
	public abstract Valuable createMoney(String value);

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
