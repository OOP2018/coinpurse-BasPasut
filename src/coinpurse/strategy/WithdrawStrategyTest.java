package coinpurse.strategy;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import coinpurse.BankNote;
import coinpurse.Coin;
import coinpurse.Money;
import coinpurse.Valuable;

public class WithdrawStrategyTest {
	private static final double TOL = 1.0E-6;
	private WithdrawStrategy strategy;
	private final long serialNumber = 1000000;
	private final String baht = "Baht";
	
	private Coin makeCoin(double value,String currency) {
		return new Coin(value, currency);
	}
	
	private BankNote makeBankNote(double value,String currency) {
		return new BankNote(value, currency, serialNumber);
	}

	@Before
	public void setUp() throws Exception {
		strategy = new GreedyWithdrawStrategy();
	}

	@Test (expected = NullPointerException.class)
	public void testTerminateCase(){
		List<Valuable> money = new ArrayList<Valuable>();
		List<Valuable> wd = new ArrayList<Valuable>();
		Valuable amount = new Money(10,baht);
		wd = strategy.withdraw(amount, money);
		double amountD = 0;
		for(Valuable v : wd){
			amountD += v.getValue();
		}
	}
	
	@Test
	public void testWithdrawCoin() {
		List<Valuable> money = new ArrayList<Valuable>();
		List<Valuable> wd = new ArrayList<Valuable>();
		money.add(makeCoin(5, baht));
		money.add(makeCoin(1,baht));
		money.add(makeCoin(10, baht));
		wd = strategy.withdraw(makeCoin(10,baht), money);
		assertTrue( wd != null );
		assertEquals(1, wd.size());
	}
	
	@Test
	public void testWithdrawBankNote() {
		List<Valuable> money = new ArrayList<Valuable>();
		List<Valuable> wd = new ArrayList<Valuable>();
		money.add(makeBankNote(100, baht));
		money.add(makeBankNote(500,baht));
		money.add(makeBankNote(20, baht));
		wd = strategy.withdraw(makeBankNote(500, baht), money);
		assertTrue( wd != null );
		assertEquals(1, wd.size());
	}
	@Test
	public void testMultiWithdraw() {
		List<Valuable> money = new ArrayList<Valuable>();
		List<Valuable> wd = new ArrayList<Valuable>();
		money.add(makeBankNote(50,baht));
		money.add(makeCoin(5,baht));
		Valuable v = new Money(55,baht);
		wd = strategy.withdraw(v, money);
		assertTrue(wd != null);
		assertEquals(2, wd.size());
		assertEquals(55, sum(wd),TOL);
		assertEquals(sum(wd), sum(money),TOL);
	}

	@Test
	public void testRecursiveWithdraw() {
		List<Valuable> money = new ArrayList<Valuable>();
		List<Valuable> wd = new ArrayList<Valuable>();
		Valuable amount = new Money(6,baht);
		money.add(makeCoin(5,baht));
		money.add(makeCoin(2,baht));
		money.add(makeCoin(2,baht));
		money.add(makeCoin(2,baht));
		wd = strategy.withdraw(amount, money);
		double result = 0;
		for(Valuable v : wd){
			result += v.getValue();
		}
		assertEquals(6, result,TOL);
	}

	
	@Test
	public void testImpossibleWithdraw() {
		List<Valuable> money = new ArrayList<Valuable>();
		List<Valuable> wd = new ArrayList<Valuable>();
		money.add(makeBankNote(20, baht));
		money.add(makeBankNote(100,"Ringgit"));
		wd = strategy.withdraw(makeBankNote(500, "Baht"), money);
		assertNull(wd);
	}
	
	/**
	 * Sum the value of some coins.
	 * @param val array of coins
	 * @return sum of values of the coins
	 */
	private double sum(List<Valuable> val)  {
		if (val == null) return 0.0;
		double sum = 0;
		for(Valuable v: val) if (v != null) sum += v.getValue();
		return sum;
	}
}

