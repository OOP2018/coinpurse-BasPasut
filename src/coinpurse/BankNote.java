package coinpurse;

/**
 * 	BankNote represents one type of monetary that have fixed value and currency,
 * but each of them has a unique serial number.
 * 
 * @author Pasut Kittiprapas
 *
 */
public class BankNote implements Valuable {

	private static long nextSerialNumber = 1000000;
	private double value;
	private String currency;
	private long serialNumber;

	/**
	 * Create a BankNote with specific value and currency.
	 * 
	 * @param value
	 * @param currency
	 */
	public BankNote(double value, String currency) {
		this.value = value;
		this.currency = currency;
		this.serialNumber = nextSerialNumber++;
	}

	/**
	 * Method to get the value of this BankNote.
	 * 
	 * @return the value of this BankNote.
	 */
	public double getValue() {
		return value;
	}

	/**
	 * Method to get the currency of this BankNote.
	 * 
	 * @return the currency of this banknote.
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * Method to get the serial number of this BankNote.
	 * 
	 * @return the serial number of this BankNote.
	 */
	public long getSerial() {
		return serialNumber;
	}

	/**
	 * This method test whether two bank notes have the same value and currency
	 * or not. If it's equal, return true. If not, return false.
	 * 
	 * @param obj
	 *            bank note that we want to compare.
	 * 
	 * @return true if two bank notes are equal and false if it's not equal.
	 */
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		BankNote b = (BankNote) obj;
		if ((this.getValue() == b.getValue()) && (this.getCurrency().equalsIgnoreCase(b.getCurrency()))) {
			return true;
		}
		return false;
	}

	/**
	 * Method to return the detail of this BankNote.
	 * 
	 * @return the detail of this BankNote.
	 */
	public String toString() {
		return value + "-" + currency + " note [" + getSerial() + "]";
	}

}
