package com.walmart.ticketservice.impl;

import com.walmart.ticketservice.SeatReserveService;
import com.walmart.ticketservice.execption.ServiceException;

/**
 * @author Gopal
 * Seat Reserve implementation class
 */
public class SeatReserveServiceImpl implements SeatReserveService {

	/**
	 * Reserve seats service method
	 */
	public String reserveSeat(String seatHoldId, String custEmail)
			throws ServiceException {
		return VenueService.reserveSeats(seatHoldId, custEmail, true);
	}
}
