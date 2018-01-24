package coinpurse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A coin purse contains coins. You can insert coins, withdraw money, check the
 * balance, and check if the purse is full.
 * 
 * @author BasPasut
 */
public class Purse {
	/** Collection of objects in the purse. */
	private List<Coin> money;

	/**
	 * Capacity is maximum number of items the purse can hold. Capacity is set
	 * when the purse is created and cannot be changed.
	 */
	private final int capacity;

	/**
	 * Create a purse with a specified capacity.
	 * 
	 * @param capacity
	 *            is maximum number of coins you can put in purse.
	 */
	public Purse(int capacity) {
		this.capacity = capacity;
		this.money = new ArrayList<>();

	}

	/**
	 * Count and return the number of coins in the purse. This is the number of
	 * coins, not their value.
	 * 
	 * @return the number of coins in the purse
	 */
	public int count() {
		return money.size();
	}

	/**
	 * Get the total value of all items in the purse.
	 * 
	 * @return the total value of items in the purse.
	 */
	public double getBalance() {
		double amount = 0;
		for (int i = 0; i < money.size(); i++) {
			amount += money.get(i).getValue();
		}
		return amount;
	}

	/**
	 * Return the capacity of the coin purse.
	 * 
	 * @return the capacity
	 */
	// TODO write accessor method for capacity. Use Java naming convention.
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Test whether the purse is full. The purse is full if number of items in
	 * purse equals or greater than the purse capacity.
	 * 
	 * @return true if purse is full.
	 */
	public boolean isFull() {
		if (money.size() >= capacity) {
			return true;
		}
		return false;
	}

	/**
	 * Insert a coin into the purse. The coin is only inserted if the purse has
	 * space for it and the coin has positive value. No worthless coins!
	 * 
	 * @param coin
	 *            is a Coin object to insert into purse
	 * @return true if coin inserted, false if can't insert
	 */
	public boolean insert(Coin coin) {
		if (isFull() == true) {
			return false;
		}
		if (coin.getValue() <= 0) {
			return false;
		}
		this.money.add(coin);
		return true;
	}

	/**
	 * Withdraw the requested amount of money. Return an array of Coins
	 * withdrawn from purse, or return null if cannot withdraw the amount
	 * requested.
	 * 
	 * @param amount
	 *            is the amount to withdraw
	 * @return array of Coin objects for money withdrawn, or null if cannot
	 *         withdraw requested amount.
	 */
	public Coin[] withdraw(double amount) {
		Collections.sort(money, new Comparator<Coin>() {
			@Override
			public int compare(Coin o1, Coin o2) {
				if (o1 == null || o2 == null)
					return -1;
				if (o1.getValue() == o2.getValue())
					return 0;
				if (o2.getValue() - o1.getValue() < 0)
					return -1;
				return 1;
			}

		});

		List<Coin> temporaryList = new ArrayList<Coin>();
		for (int i = 0; i < money.size(); i++) {
			if (money.get(i).getValue() <= amount) {
				temporaryList.add(money.get(i));
				amount -= money.get(i).getValue();
			}
		}
		if (amount != 0) {
			return null;
		}

		for (int i = 0; i < money.size(); i++) {
			for (int j = 0; j < temporaryList.size(); j++) {
				if (temporaryList.get(j) == money.get(i)) {
					money.remove(i);
				}
			}
		}

		Coin[] array = new Coin[temporaryList.size()];
		temporaryList.toArray(array);
		return array;
	}

	/**
	 * toString returns a string description of the purse contents. It can
	 * return whatever is a useful description.
	 */
	public String toString() {
		return "Balance : " + getBalance();
	}

}
