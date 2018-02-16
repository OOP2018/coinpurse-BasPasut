package coinpurse;

public class MoneyFactoryDemo {
	public static void main(String[] args) {
		MoneyFactory mf = MoneyFactory.getInstance();
		MoneyFactory mf2 = MoneyFactory.getInstance();
		
		Valuable m = mf.createMoney(10);
		Valuable m2 = mf.createMoney("10");
//		Valuable m3 = mf.createMoney("abc");
		
		Valuable nm = mf2.createMoney(10);
		Valuable nm2 = mf2.createMoney("10");
//		Valuable nm3 = mf2.createMoney("abc");
		
		System.out.println(m.toString());
		System.out.println(m2.toString());
//		System.out.println(m3.toString());
		System.out.println(nm.toString());
		System.out.println(nm2.toString());
//		System.out.println(nm3.toString());
		
	}
}
