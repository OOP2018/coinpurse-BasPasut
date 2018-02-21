package coinpurse;

/**
 * This class responsible for creating Thai money with given value.(Value must
 * be real in Thailand otherwise it will throw an exception).
 * 
 * @author Pasut Kittiprapas
 *
 */
public class ThaiMoneyFactory extends MoneyFactory {

	private final String THAICURRENCY = "Baht";

	private static long nextSerialNumber = 1000000;

	/**
	 * Create new money object in the local currency.
	 * 
	 * @param value
	 * @throws IllegalArgumentException
	 */
	@Override
	public Valuable createMoney(double value) {
		if (value == 1 || value == 2 || value == 5 || value == 10) {
			return new Coin(value, THAICURRENCY);
		} else if (value == 20 || value == 50 || value == 100 || value == 500 || value == 1000)
			return new BankNote(value, THAICURRENCY, nextSerialNumber++);
		else {
			throw new IllegalArgumentException(value + " is not avaliable in this currency.");
		}
	}
	
}
