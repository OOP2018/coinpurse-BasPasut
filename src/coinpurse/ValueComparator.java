package coinpurse;

import java.util.Comparator;

/**
 * Class that sort item that implement Valuable from large to small.
 * 
 * @author Pasut Kittiprapas
 *
 */
public class ValueComparator implements Comparator<Valuable> {

	/**
	 * Compare two objects that implement Valuable. * First compare them by
	 * currency, so that "Baht" < "Dollar". * If both objects have the same
	 * currency, order them by value.
	 */
	public int compare(Valuable o1, Valuable o2) {
		if (o1 == null || o2 == null)
			return -1;
		if (o1.getCurrency().compareToIgnoreCase(o2.getCurrency()) == 0) {
			if (o1.getValue() == o2.getValue())
				return 0;
			if (o1.getValue() > o2.getValue())
				return -1;
			if(o1.getValue() < o2.getValue())
				return 1;
		}
		return o1.getCurrency().compareToIgnoreCase(o2.getCurrency());
	}
}
