package coinpurse;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the MoneyFactory using JUnit. This is a JUnit 4 test suite.
 * 
 * IDEs (Eclipse, Netbeans, IntelliJ, BlueJ) include JUnit 4, but you have to
 * tell the IDE to add it to your project as a "Library". To run these tests,
 * right click on this file (in Project panel) and choose Run As -> JUnit test
 * 
 * @since you want to test ThaiMoney, you have to switch purse.properties to
 *        coinpurse.ThaiMoneyFactory before test it. Do the same if you want to
 *        test MalayMoney, too.
 * 
 * @author Pasut Kittiprapas
 * @version 2018.01.19
 */
public class MoneyFactoryTest {
	
	private static final double TOL = 1.0E-6;
	private static final String CURRENCY = "BAHT";
	private MoneyFactory mf = MoneyFactory.getInstance();

	/**
	 * Sets up the test fixture. Called before every test method.
	 */
	@Before
	public void setUp() {
		// nothing to initialize
	}

	/**
	 * Create valuable by using createMoney method with double parameter in
	 * MoneyFactory class
	 * 
	 * @param value
	 * @return
	 */
	public Valuable makeMoney(double value) {
		return mf.createMoney(value);
	}

	/**
	 * Create valuable by using createMoney method with String parameter in
	 * MoneyFactory class
	 * 
	 * @param value
	 * @return
	 */
	public Valuable makeMoney(String value) {
		return mf.createMoney(value);
	}

	/**
	 * Test method createMoney(double) in MoneyFactory
	 * 
	 */
	@Test
	public void testCreateMoneyThai() {
		MoneyFactory.setMoneyFactory(new ThaiMoneyFactory());
		double a = 1;
		double b = 10;
		double c = 20;
		double d = 50;

		Valuable v = makeMoney(a);
		Valuable v1 = makeMoney(b);
		Valuable v2 = makeMoney(c);
		Valuable v3 = makeMoney(d);

		assertEquals("1.0-Baht coin", v.toString());
		assertEquals("10.0-Baht coin", v1.toString());
		assertEquals(c,v2.getValue(), TOL);
		assertEquals(d,v3.getValue(), TOL);
		assertEquals("Baht",v2.getCurrency());
		
	}

	/**
	 * Test method createMoney(String) in MoneyFactory
	 */
	@Test
	public void testCreateMoneyThaiString() {
		MoneyFactory.setMoneyFactory(new ThaiMoneyFactory());
		String a = "1";
		String b = "10";
		String c = "20";
		String d = "50";

		Valuable v = makeMoney(a);
		Valuable v1 = makeMoney(b);
		Valuable v2 = makeMoney(c);
		Valuable v3 = makeMoney(d);

		assertEquals("1.0-Baht coin", v.toString());
		assertEquals("10.0-Baht coin", v1.toString());
		assertEquals(20.0,v2.getValue(), TOL);
		assertEquals(50.0,v3.getValue(), TOL);
		assertEquals("Baht",v2.getCurrency());
	}

	/**
	 * Test method createMoney(double) in MoneyFactory
	 * 
	 */
	@Test
	public void testCreateMoneyMalay() {
		MoneyFactory.setMoneyFactory(new MalayMoneyFactory());
		double a = 0.05;
		double b = 0.1;
		double c = 20;
		double d = 50;

		Valuable v = makeMoney(a);
		Valuable v1 = makeMoney(b);
		Valuable v2 = makeMoney(c);
		Valuable v3 = makeMoney(d);

		assertEquals("5.0-Sen coin", v.toString());
		assertEquals("10.0-Sen coin", v1.toString());
		assertEquals(c,v2.getValue(), TOL);
		assertEquals(d,v3.getValue(), TOL);
		//Check whether it is "Ringgit" not "Sen".
		assertEquals("Ringgit",v.getCurrency());
		assertEquals("Ringgit",v2.getCurrency());
	}

	/**
	 * Test method createMoney(String) in MoneyFactory
	 */
	@Test
	public void testCreateMoneyMalayString() {
		MoneyFactory.setMoneyFactory(new MalayMoneyFactory());
		String a = "0.05";
		String b = "0.1";
		String c = "20";
		String d = "50";

		Valuable v = makeMoney(a);
		Valuable v1 = makeMoney(b);
		Valuable v2 = makeMoney(c);
		Valuable v3 = makeMoney(d);

		assertEquals("5.0-Sen coin", v.toString());
		assertEquals("10.0-Sen coin", v1.toString());
		assertEquals(20.0,v2.getValue(), TOL);
		assertEquals(50.0,v3.getValue(), TOL);
		//Check whether it is "Ringgit" not "Sen".
		assertEquals("Ringgit",v.getCurrency());
		assertEquals("Ringgit",v2.getCurrency());
	}
	
	/**
	 * Test whether the {@link IllegalArgumentException} has been thrown or not.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testExceptionCase() {
		String a = "abc";
		String b = "1000000";
		double c = -1;

		Valuable v = makeMoney(a);
		Valuable v2 = makeMoney(b);
		Valuable v3 = makeMoney(c);
		

	}
	
}