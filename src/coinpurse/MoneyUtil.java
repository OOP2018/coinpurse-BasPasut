package coinpurse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Some Money utility methods for practice using Lists and Comparator.
 * 
 * @author BasPasut
 *
 */
public class MoneyUtil {

	/**
	 * Sort the coin by value from the coin list that we input
	 * 
	 * @param lc
	 *            is a list of coin that you want to sort.
	 */
	public static void sortCoins(List<Valuable> lc) {
		Comparator<Valuable> sortedVal = new ValueComparator();
		Collections.sort(lc,sortedVal);
	}

	/**
	 * Print the coin with value and currency
	 * 
	 * @param c
	 *            is a list of coin that you want to print.
	 */
	public static void printCoins(List<Valuable> c) {
		for (Valuable coin : c) {
			System.out.println(coin);
		}
	}

	/**
	 * Method that examines all the coins in a List and returns only the coins
	 * that have a currency that matches the parameter value.
	 * 
	 * @param coins
	 *            is a List of Coin objects. This list is not modified.
	 * @param currency
	 *            is the currency we want. Must not be null.
	 * @return a new List containing only the elements from coins that have the
	 *         requested currency.
	 */
	public static List<Valuable> filterByCurrency(List<Valuable> coins, String currency) {
		List<Valuable> sortByCurrency = new ArrayList<Valuable>();
		for (int i = 0; i < coins.size(); i++) {
			if (coins.get(i).getCurrency().equalsIgnoreCase(currency)) {
				sortByCurrency.add(coins.get(i));
			}
		}
		return sortByCurrency;
	}

//	 public static void main(String[] args) {
//	 List<Valuable> vals = new ArrayList<Valuable>();
//	 vals.add(new Coin(10.0, "Baht"));
//	 vals.add(new Coin(50.5, "Baht"));
//	 vals.add(new Coin(5.8, "Baht"));
//	 vals.add(new Coin(54.2, "Ringgit"));
//	 vals.add(new Coin(100, "Yuan"));
//	 vals.add(new Coin(12.4, "Ringgit"));
//	
//	 printCoins(vals);
//	 System.out.println("---------------------");
//	 sortCoins(vals);
//	 printCoins(vals);
//	 System.out.println("---------------------");
//	 printCoins(filterByCurrency(vals, "dollar"));
//	 }
}
