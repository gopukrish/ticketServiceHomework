/**
 * 
 */
package com.walmart.ticketservice.util.factory;

import org.apache.log4j.Logger;

import com.walmart.ticketservice.domain.Seat;

/**
 * @author Gopal 
 * Factory class to create Seat object
 */
public class SeatFactory {
	static Logger log = Logger.getLogger(SeatFactory.class);

	public SeatFactory() {

	}

	/**
	 * Get Seat Created in Row
	 * 
	 * @param seatNo
	 * @return
	 */
	public Seat getSeatCreated(int seatNo) {
		Seat seat = new Seat(seatNo, false, null);
		log.debug("Seat Created : " + seat);
		return seat;
	}
}
