/**
 * 
 */
package com.walmart.ticketservice;

import com.walmart.ticketservice.execption.ServiceException;

/**
 * @author Gopal
 * Service interface for reserving seats
 */
public interface SeatReserveService {

	String reserveSeat(String seatHoldId, String custEmail)
			throws ServiceException;

}
