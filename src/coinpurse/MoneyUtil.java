package coinpurse;

import java.util.ArrayList;
import java.util.Arrays;
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
	public static void sortMoney(List<? extends Valuable> lc) {
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
	 * Return the larger argument, based on sort order, using the objects' own
	 * compareTo method for comparing.
	 * 
	 * @param args
	 *            one or more Comparable objects to compare.
	 * @return the argument that would be last if sorted the elements.
	 * @throws IllegalArgumentException
	 *             if no arguments given.
	 */
	public static <E extends Comparable<? super E>> E max(E... args) {
		E arg = args[0];
		for (int i = 0; i < args.length - 1; i++) {
			if (args[i].compareTo(args[i + 1]) > 0) {
				arg = args[i];
			} else if (args[i].compareTo(args[i + 1]) < 0) {
				arg = args[i + 1];
			} else {
				arg = args[i];
			}
		}
		return arg;

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
	public static <E extends Valuable> List<E> filterByCurrency(List<E> vals, String currency) {
		List<E> sortByCurrency = new ArrayList<E>();
		for (int i = 0; i < vals.size(); i++) {
			if (vals.get(i).getCurrency().equalsIgnoreCase(currency)) {
				sortByCurrency.add(vals.get(i));
			}
		}
		return sortByCurrency;
	}

	public static void main(String[] args) {
		// String max = MoneyUtil.max("dog","zebra","cat");
		// System.out.println(max);
		// Money m1 = new BankNote(100, "Baht");
		// Money m2 = new BankNote(500, "Baht");
		// Money m3 = new BankNote(20, "Baht");
		// Money max2 = MoneyUtil.max(m1,m2,m3);
		// System.out.println(max2);
		List<Coin> coins = Arrays.asList(new Coin(5, "Baht"), new Coin(0.1, "Ringgit"), new Coin(5, "Cent"));
		List<Coin> result = MoneyUtil.filterByCurrency(coins, "Baht");
		for(Coin coin : coins){
			System.out.println(coin.toString());
		}
		for(Coin coin : result){
			System.out.println(coin.toString());
		}
	}
}
