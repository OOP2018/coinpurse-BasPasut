package coinpurse;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the Purse using JUnit.
 * This is a JUnit 4 test suite.  
 * 
 * IDEs (Eclipse, Netbeans, IntelliJ, BlueJ) include JUnit 4,
 * but you have to tell the IDE to add it to your project as a "Library".
 * To run these tests, right click on this file (in Project panel)
 * and choose Run As -> JUnit test
 * 
 * @author  Pasut Kittiprapas
 * @version 2018.01.19
 */
public class PurseTest {
	/** tolerance for comparing two double values */
	private static final double TOL = 1.0E-6;
	private static final String CURRENCY = "BAHT";
	
    /**
     * Sets up the test fixture.
     * Called before every test method.
     */
    @Before
    public void setUp() {
    	// nothing to initialize
    }
    
    /** Make a coin with the default currency. To save typing "new Coin(...)" */
    private Coin makeCoin(double value) {
		return new Coin(value,CURRENCY);
	}
    
    /** Make a banknote with the default currency. To save typing "new Coin(...)" */
    private BankNote makeBankNote(double value){
    	return new BankNote(value,CURRENCY);
    }

    /** Easy test that the Purse constructor is working. */
    @Test
    public void testConstructor()
    {
        Purse purse = new Purse(6);
        assertEquals(6, purse.getCapacity());
        assertEquals(false, purse.isFull());
        assertEquals(0, purse.count());
    }

    

    /** Insert some coins. Easy test. */
    @Test
    public void testInsert()
    {
        Purse purse = new Purse(6);
        Valuable coin1 = makeCoin(5);
        Valuable coin2 = makeCoin(10);
        Valuable coin3 = makeCoin(1);
        Valuable banknote1 = makeBankNote(20);
        Valuable banknote2 = makeBankNote(50);
        Valuable banknote3 = makeBankNote(100);
        assertTrue( purse.insert(banknote2));
        assertTrue( purse.insert(banknote3));
        assertTrue( purse.insert(banknote1));
        assertTrue( purse.insert(coin1));
        assertTrue( purse.insert(coin3));
        assertTrue( purse.insert(coin2));

        assertEquals( 6, purse.count() );
        // purse is full so insert should fail
        assertFalse( purse.insert(makeCoin(1)) );
    }


	/** Insert should reject coin with no value. */
    @Test
    public void testInsertNoValue()
    {
        Purse purse = new Purse(3);
        Valuable fakeCoin = new Coin(0, CURRENCY);
        Valuable fakeBankNote = new BankNote(0, CURRENCY);
        assertFalse( purse.insert(fakeCoin) );
        assertFalse( purse.insert(fakeBankNote) );
    }


    @Test(timeout=1000)
    public void testIsFull()
    {   // borderline case (capacity 1)
        Purse purse = new Purse(1);
        assertFalse( purse.isFull() );
        purse.insert( makeCoin(1) );
        assertTrue( purse.isFull() );
        // real test
        int capacity = 4;
        purse = new Purse(capacity);
        for(int k=1; k<=capacity; k++) {
            assertFalse(purse.isFull());
            purse.insert( makeCoin(k) );
        }
        // should be full now
        assertTrue( purse.isFull() );
        assertFalse( purse.insert( makeCoin(5) ) );
    }

	/** Should be able to insert same coin many times,
	 *  since spec doesn't say anything about this.
	 */
	@Test(timeout=1000)
	public void testInsertSameValuable()
	{
		int capacity = 6;
		double value = 10.0;
		Purse purse = new Purse(capacity);
		Valuable coin = new Coin(value, "THB");
		Valuable banknote = new BankNote(value, "THB");
		assertTrue( purse.insert(coin) );
		assertTrue( purse.insert(coin) ); // should be allowed
		assertTrue( purse.insert(coin) ); // should be allowed
		assertTrue( purse.insert(banknote) ); // should be allowed
		assertTrue( purse.insert(banknote) ); // should be allowed
		assertTrue( purse.insert(banknote) ); // should be allowed
		assertEquals( purse.getBalance(), 6*value, TOL);
	}

	/** Test 1st Add one coin and remove it. 
	 * 	Test 2nd Add one BankNote and remove it. 
	 * */
	@Test(timeout=1000)
	public void testEasyWithdraw() {
		Purse purse = new Purse(10);
		double [] values = {1, 20, 0.5, 10}; // values of coins we will insert
		
		for(double value : values) {
			Valuable coin = makeCoin(value);
			assertTrue(purse.insert(coin));
			assertEquals(value,  purse.getBalance(), TOL);
			Valuable [] result = purse.withdraw(value);
			assertTrue( result != null );
			assertEquals( 1, result.length );
			assertSame(  coin, result[0] ); // should be same object
			assertEquals( 0, purse.getBalance(), TOL );
		}
		
		double [] values2 = {10, 200, 0.5, 100};
		for(double value2 : values2) {
			Valuable note = makeBankNote(value2);
			assertTrue(purse.insert(note));
			assertEquals(value2,  purse.getBalance(), TOL);
			Valuable [] result = purse.withdraw(value2);
			assertTrue( result != null );
			assertEquals( 1, result.length );
			assertSame(  note, result[0] ); // should be same object
			assertEquals( 0, purse.getBalance(), TOL );
		}
	}
	

	/** Add 4 coins and then withdraw in pairs, but not in same order. */
	@Test(timeout=1000)
	public void testMultiWithdraw() {
		Purse purse = new Purse(10);
		Valuable[] money = { makeCoin(5.0), makeCoin(10.0), makeBankNote(20),makeBankNote(50)};
		// insert them all
		for(Valuable v: money) assertTrue( purse.insert(v) );
		
		double amount1 = money[1].getValue() + money[3].getValue();
		double amount2 = money[0].getValue() + money[2].getValue();
		assertEquals(amount1+amount2, purse.getBalance(), TOL );
		
		Valuable [] wd1 = purse.withdraw(amount1);
		assertEquals(amount1, sum(wd1), TOL );
		
		assertEquals(amount2, purse.getBalance(), TOL );
		Valuable [] wd2 = purse.withdraw(amount2);
		
		// should be empty now
		assertEquals(0, purse.getBalance(), TOL );
	}
	
	/** Add 4 coins and then withdraw in pairs, but not in same order. */
	@Test(timeout=1000)
	public void testMultiWithdrawBankNoteAndCoin() {
		Purse purse = new Purse(10);
		Valuable[] money = { makeBankNote(50.0), makeCoin(10.0), makeBankNote(20.0), makeCoin(5.0) };
		// insert them all
		for(Valuable coin: money) assertTrue( purse.insert(coin) );
		
		double amount1 = money[1].getValue() + money[3].getValue();
		double amount2 = money[0].getValue() + money[2].getValue();
		assertEquals(amount1+amount2, purse.getBalance(), TOL );
		
		Valuable [] wd1 = purse.withdraw(amount1);
		assertEquals(amount1, sum(wd1), TOL );
		
		assertEquals(amount2, purse.getBalance(), TOL );
		Valuable [] wd2 = purse.withdraw(amount2);
		
		// should be empty now
		assertEquals(0, purse.getBalance(), TOL );
	}

	/** Withdraw full amount in purse, using varying numbers of objects. */
	@Test(timeout=1000)
	public void testWithdrawEverything() {
		Purse purse = new Purse(10);
		// Coins we want to insert and then withdraw.
		// Use values such that greedy will succeed, but not monotonic
		List<Valuable> money = Arrays.asList(
				makeCoin(1.0), makeBankNote(0.5), makeCoin(10.0), makeBankNote(0.25), makeCoin(5.0)
				);
		// num = number of coins to insert and then withdraw
		for(int num=1; num <= money.size(); num++) {
			double amount = 0.0;
			List<Valuable> subList = money.subList(0, num);
			for(Valuable c: subList) {
				purse.insert(c);
				amount += c.getValue();
			}
			// balance should be exactly what we just inserted
			assertEquals( amount, purse.getBalance(), TOL);
			// can we withdraw it all?
			Valuable[] result = purse.withdraw(amount);
			String errmsg = String.format("couldn't withdraw %.2f but purse has %s",
					amount, Arrays.toString(subList.toArray()) );
			assertNotNull( errmsg, result );
			// is the amount correct?
			assertEquals("Withdraw wrong amount", amount, sum(result), TOL);
			// should not be anything left in the purse
			assertEquals( 0.0, purse.getBalance(), TOL);
		}
	}


	@Test(timeout=1000)
	public void testImpossibleWithdraw() {
		Purse purse = new Purse(10);
		assertNull( purse.withdraw(1) );
		purse.insert( makeCoin(20) );
		assertNull( purse.withdraw(1) );
		assertNull( purse.withdraw(19) );
		assertNull( purse.withdraw(21) );
		purse.insert( makeCoin(20) ); // now it has 20 + 20
		assertNull( purse.withdraw(30) );
	}
	
	/**
	 * Sum the value of some coins.
	 * @param val array of coins
	 * @return sum of values of the coins
	 */
	private double sum(Valuable[] val)  {
		if (val == null) return 0.0;
		double sum = 0;
		for(Valuable c: val) if (c != null) sum += c.getValue();
		return sum;
	}
}