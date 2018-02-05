package coinpurse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Some Money utility methods for practice using Lists and Comparator.
 * 
 * @author Pasut Kittiprapas
 *
 */
public class MoneyUtil {

	/**
	 * Sort the coins or bank notes by value from the coin list that we input
	 * 
	 * @param lc
	 *            is a list of valuable that you want to sort.
	 */
	public static void sortCoins(List<Valuable> lc) {
		Comparator<Valuable> sortedVal = new ValueComparator();
		Collections.sort(lc, sortedVal);
	}

	/**
	 * Print the coin or bank notes with value and currency
	 * 
	 * @param c
	 *            is a list of valuable that you want to print.
	 */
	public static void printCoins(List<Valuable> c) {
		for (Valuable coin : c) {
			System.out.println(coin);
		}
	}

	/**
	 * Method that examines all the coins or bank notes in a List and returns
	 * only the coins or bank notes that have a currency that matches the
	 * parameter value.
	 * 
	 * @param vals
	 *            is a List of Valuable objects. This list is not modified.
	 * @param currency
	 *            is the currency we want. Must not be null.
	 * @return a new List containing only the elements from valuable that have
	 *         the requested currency.
	 */
	public static List<Valuable> filterByCurrency(List<Valuable> vals, String currency) {
		List<Valuable> sortByCurrency = new ArrayList<Valuable>();
		for (int i = 0; i < vals.size(); i++) {
			if (vals.get(i).getCurrency().equalsIgnoreCase(currency)) {
				sortByCurrency.add(vals.get(i));
			}
		}
		return sortByCurrency;
	}
}
