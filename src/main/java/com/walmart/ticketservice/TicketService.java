/**
 * 
 */
package com.walmart.ticketservice;

import java.util.Map;

import com.walmart.ticketservice.domain.SeatHold;
import com.walmart.ticketservice.execption.ServiceException;

/**
 * @author Gopal 
 * Main Service interface for tickeing service
 */
public interface TicketService {
	int numSeatsAvailable() throws ServiceException;

	int numSeatsAvailable(int venueLevelId) throws ServiceException;

	SeatHold findAndHoldSeats(int numSeats, String custEmail)
			throws ServiceException;

	SeatHold findAndHoldSeats(int numSeats, int levelId, String custEmail)
			throws ServiceException;

	SeatHold findAndHoldSeats(int numSeats, int minLevelId, int maxLevelId,
			String customerEmail) throws ServiceException;

	String reserveSeats(String seatHoldId, String customerEmail)
			throws ServiceException;

	Map<Integer, String> getVenueLevels() throws ServiceException;

}
