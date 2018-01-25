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
	public static void sortCoins(List<Coin> lc) {
		Collections.sort(lc);
	}

	/**
	 * Print the coin with value and currency
	 * 
	 * @param c
	 *            is a list of coin that you want to print.
	 */
	public static void printCoins(List<Coin> c) {
		for (Coin coin : c) {
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
	public static List<Coin> filterByCurrency(List<Coin> coins, String currency) {
		List<Coin> sortByCurrency = new ArrayList<Coin>();
		for (int i = 0; i < coins.size(); i++) {
			if (coins.get(i).getCurrency().equalsIgnoreCase(currency)) {
				sortByCurrency.add(coins.get(i));
			}
		}
		return sortByCurrency;
	}

	// public static void main(String[] args) {
	// List<Coin> coins = new ArrayList<Coin>();
	// coins.add(new Coin(10.0, "Baht"));
	// coins.add(new Coin(50.5, "Baht"));
	// coins.add(new Coin(5.8, "Baht"));
	// coins.add(new Coin(54.2, "Ringgit"));
	// coins.add(new Coin(100, "Yuan"));
	// coins.add(new Coin(12.4, "Ringgit"));
	//
	// printCoins(coins);
	// System.out.println("---------------------");
	// sortCoins(coins);
	// printCoins(coins);
	// System.out.println("---------------------");
	// printCoins(filterByCurrency(coins, "Ringgit"));
	// }
}
