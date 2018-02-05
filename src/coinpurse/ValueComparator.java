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
		if (o1.getValue() == o2.getValue())
			return 0;
		if (o2.getValue() - o1.getValue() < 0)
			return -1;
		return 1;
	}
}
