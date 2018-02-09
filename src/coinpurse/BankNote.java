package coinpurse;

/**
 * BankNote represents one type of monetary that have fixed value and currency,
 * but each of them has a unique serial number.
 * 
 * @author Pasut Kittiprapas
 *
 */
public class BankNote extends Money {

	private static long nextSerialNumber = 1000000;
	private long serialNumber;

	/**
	 * Create a BankNote with specific value and currency.
	 * 
	 * @param value
	 * @param currency
	 */
	public BankNote(double value, String currency) {
		super(value, currency);
		this.serialNumber = nextSerialNumber++;
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
	 * Method to return the detail of this BankNote.
	 * 
	 * @return the detail of this BankNote.
	 */
	public String toString() {
		return getValue() + "-" + getCurrency() + " note [" + getSerial() + "]";
	}

}
