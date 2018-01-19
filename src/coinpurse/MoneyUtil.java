package coinpurse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MoneyUtil {
	
	/**
	 * Sort the coin by value from the coin list that we input
	 * @param lc
	 */
	public static void sortCoins(List<Coin> lc){
		Collections.sort(lc);
	}
	
	/**
	 * Print the coin with value and currency
	 * @param c
	 */
	 public static void printCoins(List<Coin> c){
	    	System.out.println(c.toString());
	    }
	
	public static void main(String[] args) {
		List<Coin> coins = new ArrayList<Coin>();
    	coins.add(new Coin(10.0,"Baht"));
    	coins.add(new Coin(50.5,"Baht"));
    	coins.add(new Coin(5.8,"Baht"));
    	printCoins(coins);
    	sortCoins(coins);
    	printCoins(coins);
	}
}
