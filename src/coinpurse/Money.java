package coinpurse;

/**
 * This class is responsible for being a factory to create any type of money
 * that have fixed value and currency.
 * 
 * @author Pasut Kittiprapas
 *
 */
public class Money implements Valuable {

	protected double value;
	protected String currency;

	/**
	 * Create a money with fixed value and currency
	 * 
	 * @param value
	 * @param currency
	 */
	public Money(double value, String currency) {
		if (value <= 0) {
			this.value = 0;
		} else {
			this.value = value;
		}
		this.currency = currency;
	}

	/**
	 * Method to get the value of this Valuable.
	 * 
	 * @return the value of this Valuable.
	 */
	public double getValue() {
		return value;
	}

	/**
	 * Method to get the currency of this Valuable.
	 * 
	 * @return the currency of this Valuable.
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * This method test whether two monetary have the same value and currency or
	 * not. If it's equal, return true. If not, return false.
	 * 
	 * @param arg
	 *            monetary that we want to compare.
	 * 
	 * @return true if two monetary are equal and false if it's not equal.
	 */
	public boolean equals(Object arg) {
		if (arg == null)
			return false;
		if (this.getClass() != arg.getClass())
			return false;
		Money m = (Money) arg;
		return ((m.getValue() == this.getValue()) && m.getCurrency().equalsIgnoreCase(this.getCurrency()));
	}

	/**
	 * Compare two objects that implement Valuable. * First compare them by
	 * currency, so that "Baht" < "Dollar". * If both objects have the same
	 * currency, order them by value.
	 *
	 * @param v
	 *            is the valuable that want to compare.
	 * 
	 * @return 0 if both monetary have the same currency and same value, 1 if it
	 *         is greater than other, and -1 if it is less than other.
	 */
	public int compareTo(Valuable v) {
		if (v == null)
			return -1;
		if (this.getCurrency().compareToIgnoreCase(v.getCurrency()) == 0) {
			return Double.compare(this.getValue(), v.getValue());
		}
		return this.getCurrency().compareToIgnoreCase(v.getCurrency());
	}
}