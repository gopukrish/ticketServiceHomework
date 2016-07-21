/**
 * 
 */
package com.walmart.ticketservice.util;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Gopal 
 * Common utility class to provide service like get price, filter
 *         list etc.
 */
public class CommonUtils {

	/**
	 * @param price
	 *            - String format of price
	 * @return converted double price
	 */
	public static double getPrice(String price) {
		double prc = 0;
		if (price != null) {
			String[] prices = price.split("\\$");
			prc = Double.parseDouble(prices[1]);
		}
		return prc;
	}

	/**
	 * @param elem
	 *            - Element to apply predicate
	 * @param predicate
	 *            - Predicate interface
	 * @return List
	 */
	public static <T> Collection<T> filter(Collection<T> elem,
			Predicate<T> predicate) {
		Collection<T> filteredCollection = new ArrayList<T>();
		for (T t : elem) {
			if (predicate.apply(t)) {
				filteredCollection.add(t);
			}
		}
		return filteredCollection;
	}
}
