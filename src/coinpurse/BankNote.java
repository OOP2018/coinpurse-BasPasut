package coinpurse;

/**
 * BankNote represents one type of monetary that have fixed value and currency,
 * but each of them has a unique serial number.
 * 
 * @author Pasut Kittiprapas
 *
 */
public class BankNote extends Money {

	private long serialNumber;
	private static long nextSerialNumber = 1000000;

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
	 * Create a BankNote with specific value, currency, and serialnumber.
	 * 
	 * @param value
	 * @param currency
	 * @param serialNumber
	 */
	public BankNote(double value, String currency , long serialNumber){
		super(value , currency);
		this.serialNumber = serialNumber;
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
