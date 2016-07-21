/**
 * 
 */
package com.walmart.ticketservice.util;

/**
 * @author Gopal 
 * Predicate utility for filtering collections
 */
public interface Predicate<T> {
	boolean apply(T type);
}
