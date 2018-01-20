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
	    	for(Coin coin : c) {
	    		System.out.println(coin);
	    	}
	    }
	
	 public static List<Coin> filterByCurrency(List<Coin> coins, String currency){
		 List<Coin> sortByCurrency = new ArrayList<Coin>();
		 for(int i = 0 ; i < coins.size() ; i++) {
			 if(coins.get(i).getCurrency().equalsIgnoreCase(currency)) {
				 sortByCurrency.add(coins.get(i));
			 }
		 }
		 return sortByCurrency;
	 }
	 
	public static void main(String[] args) {
		List<Coin> coins = new ArrayList<Coin>();
    	coins.add(new Coin(10.0,"Baht"));
    	coins.add(new Coin(50.5,"Baht"));
    	coins.add(new Coin(5.8,"Baht"));
    	coins.add(new Coin(54.2,"Ringgit"));
    	coins.add(new Coin(100,"Yuan"));
    	coins.add(new Coin(12.4,"Ringgit"));
    	
    	printCoins(coins);
    	System.out.println("---------------------");
    	sortCoins(coins);
    	printCoins(coins);
    	System.out.println("---------------------");
    	printCoins(filterByCurrency(coins, "Ringgit"));
	}
}
