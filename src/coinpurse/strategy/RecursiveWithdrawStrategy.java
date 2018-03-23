package coinpurse.strategy;

import java.util.ArrayList;
import java.util.List;
import coinpurse.MoneyUtil;
import coinpurse.Valuable;

/**
 * One of the strategy to withdraw the money in the purse using recursive.
 * 
 * @author Pasut Kittiprapas
 *
 */
public class RecursiveWithdrawStrategy implements WithdrawStrategy {

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
	public List<Valuable> withdraw(Valuable amount, List<Valuable> money) {
		MoneyUtil.filterByCurrency(money, amount.getCurrency());
		return withdrawHelper(amount.getValue(), money);
	}

	/**
	 * Help find the money in the purse that can withdraw all the amount by
	 * going through all the money in the purse.
	 * 
	 * @param amount
	 * @param money
	 * @return
	 */
	public List<Valuable> withdrawHelper(double amount, List<Valuable> money) {
		if (amount < 0)
			return null;
		if (money.isEmpty() && amount != 0)
			return null;
		if (amount == 0)
			return new ArrayList<Valuable>();

		List<Valuable> left = withdrawHelper(amount - money.get(0).getValue(), money.subList(1, money.size()));
		List<Valuable> right = withdrawHelper(amount, money.subList(1, money.size()));
		if (left != null) {
			left.add(money.get(0));
			return left;
		}
		return right;
	}

}