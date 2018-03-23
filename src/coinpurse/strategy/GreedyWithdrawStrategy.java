package coinpurse.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import coinpurse.MoneyUtil;
import coinpurse.Valuable;
import coinpurse.ValueComparator;

/**
 * Concrete strategy that use Greedy algorithm to withdraw the money in the purse.
 * @author Pasut Kittiprapas
 *
 */
public class GreedyWithdrawStrategy implements WithdrawStrategy{

	
	private Comparator<Valuable> sortList = new ValueComparator();
	
	/**
	 * Find and return items from a collection whose total value equals the
	 * requested amount.
	 * 
	 * @param amount
	 *            is the amount of money to withdraw, with currency
	 * @param money
	 *            the contents that are available for possible withdraw. Must
	 *            not be null, but may be an empty list. This list is not
	 *            modified.
	 * @return if solution is found, return a List containing references from
	 *         the money parameter (List) whose sum equals the amount. If a
	 *         solution is not found, returns null.
	 * 
	 */
	@Override
	public List<Valuable> withdraw(Valuable amount, List<Valuable> items) {
		
		
		Collections.sort(items,sortList);
		
		double wd = amount.getValue();
		List<Valuable> curList = MoneyUtil.filterByCurrency(items, amount.getCurrency());
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

		return temporaryList;
	}

}
