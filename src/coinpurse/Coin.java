package coinpurse;

/**
 * Coin represents coinage (money) with fixed value and currency.
 * 
 * @author Pasut Kittiprapas
 *
 * @param <Coin>
 */
public class Coin implements Valuable {

	private double value;
	private String currency;

	/**
	 * Initialize a coin with value and currency
	 * 
	 * @param value
	 * @param currency
	 */
	public Coin(double value, String currency) {
		if (value <= 0) {
			this.value = 0;
		} else {
			this.value = value;
		}
		this.currency = currency;
	}

	/**
	 * Get the value of a coin
	 * 
	 * @return the value of a coin
	 */
	public double getValue() {
		return value;
	}

	/**
	 * Get the currency of a coin
	 * 
	 * @return the currency of a coin
	 */
	public String getCurrency() {
		return currency;

	}

	/**
	 * Get the value and the currency of a coin
	 * 
	 * @return the value-currency (Ex. 50-Baht)
	 */
	public String toString() {
		return value + "-" + currency;

	}

	/**
	 * This method test whether two coins have the same value and currency or
	 * not. If it's equal, return true. If not, return false.
	 * 
	 * @param arg
	 *            coin that we want to compare.
	 * 
	 * @return true if two coins are equal and false if it's not equal.
	 */
	public boolean equals(Object arg) {
		if (arg == null)
			return false;
		if (arg.getClass() != this.getClass())
			return false;
		Coin c = (Coin) arg;
		if ((c.getValue() == this.getValue()) && c.getCurrency().equalsIgnoreCase(this.getCurrency()))
			return true;
		return false;
	}

}
