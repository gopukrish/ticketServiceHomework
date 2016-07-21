/**
 * 
 */
package com.walmart.ticketservice.util;

/**
 * @author Gopal 
 * Utility class for unique seat hold id generation
 */
public class SeatHoldIdGenerator {

	private static long counter = 0;

	/**
	 * Utility method to generate seat hold ID
	 * 
	 * @return SeatHoldID string
	 */
	public static String getNextUniqueSeatHoldId() {
		counter++;
		String seatHoldIdPrefix = Messages.SEAT_HOLD_ID_PREFIX;
		return seatHoldIdPrefix + counter;
	}
}
