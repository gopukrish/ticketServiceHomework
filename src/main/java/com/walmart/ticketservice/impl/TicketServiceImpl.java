/**
 * 
 */
package com.walmart.ticketservice.impl;

import java.util.HashMap;
import java.util.Map;

import com.walmart.ticketservice.SeatHoldService;
import com.walmart.ticketservice.SeatReserveService;
import com.walmart.ticketservice.TicketService;
import com.walmart.ticketservice.domain.Level;
import com.walmart.ticketservice.domain.SeatHold;
import com.walmart.ticketservice.execption.ServiceException;

/**
 * @author Gopal 
 * Main interface class to ticket service which serves all
 * requests
 */
public class TicketServiceImpl implements TicketService {

	private SeatHoldService seatHoldService;

	private SeatReserveService seatReserveService;

	public TicketServiceImpl() {
		seatHoldService = new SeatHoldServiceImpl();
		seatReserveService = new SeatReserveServiceImpl();
	}

	/**
	 * Get Avalilable seats
	 * 
	 * @see com.walmart.ticketservice.TicketService#numSeatsAvailable()
	 */
	public int numSeatsAvailable() throws ServiceException {
		return VenueService.getVenue().getAvailableSeats();
	}

	/**
	 * Get available seats by level id
	 */
	public int numSeatsAvailable(int venueLevelId) throws ServiceException {
		return VenueService.getVenue().getAvailableSeats(venueLevelId);
	}

	/**
	 * Find and hold seats using number of seats
	 */
	public SeatHold findAndHoldSeats(int numSeats, String custEmail)
			throws ServiceException {
		return seatHoldService.holdSeat(numSeats, custEmail);
	}

	/**
	 * Find and hold seats using number of seats , level id
	 */
	public SeatHold findAndHoldSeats(int numSeats, int levelId, String custEmail)
			throws ServiceException {
		return seatHoldService.holdSeat(numSeats, levelId, custEmail);
	}

	/**
	 * Find and hold seats using number of seats , level id range
	 */
	public SeatHold findAndHoldSeats(int numSeats, int minLevelId,
			int maxLevelId, String customerEmail) throws ServiceException {
		return seatHoldService.holdSeat(numSeats, minLevelId, maxLevelId,
				customerEmail);
	}

	/**
	 * Reserve seat for customer using seathold id
	 */
	public String reserveSeats(String seatHoldId, String customerEmail)
			throws ServiceException {
		return seatReserveService.reserveSeat(seatHoldId, customerEmail);
	}

	/**
	 * Get all venue levels map
	 */
	public Map<Integer, String> getVenueLevels() throws ServiceException {
		Map<Integer, String> levels = new HashMap<Integer, String>();
		for (Level level : VenueService.getVenue().getLevelMap().values()) {
			levels.put(level.getLevelId(), level.getLevelName());
		}
		return levels;
	}

}
