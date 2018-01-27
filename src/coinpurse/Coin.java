package coinpurse;

/**
 * Coin represents coinage (money) with fixed value and currency.
 * 
 * @author BasPasut
 *
 * @param <Coin>
 */
public class Coin implements Comparable<Coin>, Valuable {

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
	 * This method test that this money and another money which has more value.
	 * If this money has more value, return 1. If it's equal, return 0. If it
	 * has less value, return -1.
	 * 
	 * @param c
	 * @return
	 */
	public int compareTo(Coin c) {
		if (c == null)
			return -1;
		if (this.value < c.getValue())
			return -1;
		else if (this.value > c.getValue())
			return 1;
		return 0;
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
