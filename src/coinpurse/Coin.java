package coinpurse;

/**
 * Coin represents coinage (money) with fixed value and currency.
 * 
 * @author Pasut Kittiprapas
 *
 */
public class Coin extends Money {

	/**
	 * Initialize a coin with value and currency
	 * 
	 * @param value
	 * @param currency
	 */
	public Coin(double value, String currency) {
		super(value, currency);
	}

	/**
	 * Get the value and the currency of a coin
	 * 
	 * @return the value-currency (Ex. 50-Baht)
	 */
	public String toString() {
		return getValue() + "-" + getCurrency();

	}

}
