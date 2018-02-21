package coinpurse;

/**
 * This class responsible for creating Malay money with given value.(Value must
 * be real in Malaysia otherwise it will throw an exception).
 * 
 * @author Pasut Kittiprapas
 *
 */
public class MalayMoneyFactory extends MoneyFactory {

	private final String MALAYCURRENCY = "Ringgit";
	private static long nextSerialNumber = 1000000;

	/**
	 * Create new money object in the local currency.
	 * 
	 * @param value
	 * @throws IllegalArgumentException
	 */
	@Override
	public Valuable createMoney(double value) {
		if (value == 0.05 || value == 0.1 || value == 0.2 || value == 0.5) {
			return new Coin(value, MALAYCURRENCY);
		}

		else if (value == 1 || value == 2 || value == 5 || value == 10 || value == 20 || value == 50 || value == 100) {
			return new BankNote(value, MALAYCURRENCY, nextSerialNumber++);
		} else {
			throw new IllegalArgumentException(value + " is not avaliable in this currency.");
		}
	}
}
