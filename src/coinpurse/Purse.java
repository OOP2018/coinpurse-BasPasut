package coinpurse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import coinpurse.strategy.GreedyWithdrawStrategy;
import coinpurse.strategy.RecursiveWithdrawStrategy;
import coinpurse.strategy.WithdrawStrategy;

/**
 * A purse contains money. You can insert money, withdraw money, check the
 * balance, and check if the purse is full.
 * 
 * @author Pasut Kittiprapas
 */
public class Purse {
	/** Collection of objects in the purse. */
	private List<Valuable> money;	
	private WithdrawStrategy wds;

	/**
	 * Capacity is maximum number of items the purse can hold. Capacity is set
	 * when the purse is created and cannot be changed.
	 */
	private final int capacity;

	/**
	 * Create a purse with a specified capacity.
	 * 
	 * @param capacity
	 *            is maximum number of money you can put in purse.
	 */
	public Purse(int capacity) {
		this.capacity = capacity;
		this.money = new ArrayList<>();
		wds = new GreedyWithdrawStrategy();
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
	
	public void setStrategy(WithdrawStrategy wds){
		this.wds = wds;
	}

	/**
	 * Test whether the purse is full. The purse is full if number of items in
	 * purse equals or greater than the purse capacity.
	 * 
	 * @return true if purse is full.
	 */
	public boolean isFull() {
		return (this.count() >= capacity);
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
		Valuable v = new Money(amount, "Baht");
		return withdraw(v);
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
		List<Valuable> wdList = null;
		if(amount.getValue() >= 0){
		wdList = wds.withdraw(amount, money);
		}
		
		if(wdList == null){
			return null;
		}		
		
		for (Valuable v : wdList) {
			money.remove(v);
		}
		Valuable[] array = new Valuable[wdList.size()];
		wdList.toArray(array);
		return array;
	}

	/**
	 * toString returns a string description of the purse contents. It can
	 * return whatever is a useful description.
	 */
	public String toString() {
		return "Balance : " + getBalance() + "\n";
	}
}
