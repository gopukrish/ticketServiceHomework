/**
 * 
 */
package com.walmart.ticketservice.impl;

import java.util.ArrayList;
import java.util.List;

import com.walmart.ticketservice.SeatHoldService;
import com.walmart.ticketservice.domain.Level;
import com.walmart.ticketservice.domain.SeatHold;
import com.walmart.ticketservice.execption.ServiceException;

/**
 * @author Gopal
 * 
 */
public class SeatHoldServiceImpl implements SeatHoldService {

	/**
	 * Seat hold service method
	 */
	public SeatHold holdSeat(int numSeats, String custEmail)
			throws ServiceException {
		if (VenueService.getVenue().getAvailableSeats() >= numSeats)
			return VenueService.holdSeats(VenueService.getVenue()
					.getAvailableLevels(), numSeats, custEmail);
		else
			return null;
	}

	/**
	 * Seat hold service method
	 */
	public SeatHold holdSeat(int numSeats, int levelId, String custEmail)
			throws ServiceException {
		if (VenueService.getVenue().getAvailableSeats(levelId) >= numSeats) {
			List<Level> levels = new ArrayList<Level>();
			levels.add(VenueService.getVenue().getLevelMap().get(levelId));
			return VenueService.holdSeats(levels, numSeats, custEmail);
		} else
			return null;
	}

	/**
	 * Seat hold service method
	 */
	public SeatHold holdSeat(int numSeats, int minLevelId, int maxLevelId,
			String custEmail) throws ServiceException {
		int totalSeats = 0;
		List<Level> levels = new ArrayList<Level>();
		for (int i = minLevelId; i <= maxLevelId; i++) {
			totalSeats = totalSeats
					+ VenueService.getVenue().getAvailableSeats(i);
			levels.add(VenueService.getVenue().getLevelMap().get(i));
		}
		if (totalSeats >= numSeats) {
			return VenueService.holdSeats(levels, numSeats, custEmail);
		} else
			return null;
	}
}
