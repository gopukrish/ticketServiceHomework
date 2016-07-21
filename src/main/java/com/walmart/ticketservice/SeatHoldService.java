/**
 * 
 */
package com.walmart.ticketservice;

import com.walmart.ticketservice.domain.SeatHold;
import com.walmart.ticketservice.execption.ServiceException;

/**
 * @author Gopal 
 * Service interface for holding seat for customer
 */
public interface SeatHoldService {

	SeatHold holdSeat(int numSeats, String custEmail) throws ServiceException;

	SeatHold holdSeat(int numSeats, int levelId, String custEmail)
			throws ServiceException;

	SeatHold holdSeat(int numSeats, int minLevelId, int maxLevelId,
			String custEmail) throws ServiceException;

}
