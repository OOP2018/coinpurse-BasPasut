package coinpurse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A purse contains coins and bank notes. You can insert coins or bank notes,
 * withdraw money, check the balance, and check if the purse is full.
 * 
 * @author Pasut Kittiprapas
 */
public class Purse {
	/** Collection of objects in the purse. */
	private static List<Valuable> money;

	private Comparator<Valuable> sortedMoney;

	/**
	 * Capacity is maximum number of items the purse can hold. Capacity is set
	 * when the purse is created and cannot be changed.
	 */
	private final int capacity;

	/**
	 * Create a purse with a specified capacity.
	 * 
	 * @param capacity
	 *            is maximum number of coins or bank notes you can put in purse.
	 */
	public Purse(int capacity) {
		this.capacity = capacity;
		this.money = new ArrayList<>();
		this.sortedMoney = new ValueComparator();

	}

	/**
	 * Count and return the number of coins or bank notes in the purse. This is
	 * the number of coins or bank notes, not their value.
	 * 
	 * @return the number of coins or bank notes in the purse
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
	 * Return the capacity of the purse.
	 * 
	 * @return the capacity
	 */
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
	 * Insert a money into the purse. The money is only inserted if the purse
	 * has space for it and the money has positive value. No worthless money!
	 * 
	 * @param money
	 *            is a Money object to insert into purse
	 * @return true if money inserted, false if can't insert
	 */
	public boolean insert(Valuable money) {
		if (isFull() == true) {
			return false;
		}
		if (money.getValue() <= 0) {
			return false;
		}
		this.money.add(money);
		return true;
	}

	/**
	 * Withdraw the requested amount of money. Return an array of Valuable
	 * withdrawn from purse, or return null if cannot withdraw the amount
	 * requested.
	 * 
	 * @param amount
	 *            is the amount to withdraw
	 * @return array of Valuable objects for money withdrawn, or null if cannot
	 *         withdraw requested amount.
	 */
	public Valuable[] withdraw(double amount) {

		Collections.sort(money, sortedMoney);

		List<Valuable> temporaryList = new ArrayList<Valuable>();
		for (int i = 0; i < money.size(); i++) {
			Valuable m = money.get(i);
			if (m.getValue() <= amount) {
				temporaryList.add(m);
				amount -= m.getValue();
			}
		}

		// Check to see if we successfully found exact amount
		if (amount != 0) {
			return null; // failed
		}

		for (int i = 0; i < money.size(); i++) {
			for (int j = 0; j < temporaryList.size(); j++) {
				if (temporaryList.get(j) == money.get(i)) {
					money.remove(i);
				}
			}
		}

		Valuable[] array = new Valuable[temporaryList.size()];
		temporaryList.toArray(array);
		return array;
	}

	/**
	 * Withdraw the requested amount of money with the same currency of as the
	 * parameter only. Return an array of Valuable withdrawn from purse, or
	 * return null if cannot withdraw the amount requested.
	 * 
	 * @param amount
	 *            is the amount to withdraw with currency.
	 * @return array of Valuable objects for money withdrawn, or null if cannot
	 *         withdraw requested amount.
	 */
	public Valuable[] withdraw(Valuable amount) {

		Collections.sort(money, sortedMoney);

		double wd = amount.getValue();
		List<Valuable> curList = MoneyUtil.filterByCurrency(money, amount.getCurrency());
		List<Valuable> temporaryList = new ArrayList<Valuable>();

		if (wd <= 0) {
			return null;
		}

		for (Valuable v : curList) {
			if (v.getValue() <= wd) {
				temporaryList.add(v);
				wd -= v.getValue();
			}
		}

		// Check to see if we successfully found exact amount
		if (wd != 0) {
			return null; // failed
		}

		for (Valuable v : temporaryList) {
			money.remove(v);
		}

		Valuable[] array = new Valuable[temporaryList.size()];
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
