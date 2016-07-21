/**
 * 
 */
package com.walmart.ticketservice.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.walmart.ticketservice.domain.Level;
import com.walmart.ticketservice.domain.Row;
import com.walmart.ticketservice.domain.Seat;
import com.walmart.ticketservice.domain.SeatHold;
import com.walmart.ticketservice.domain.Venue;
import com.walmart.ticketservice.execption.ServiceException;
import com.walmart.ticketservice.execption.UtilException;
import com.walmart.ticketservice.util.CommonUtils;
import com.walmart.ticketservice.util.Messages;
import com.walmart.ticketservice.util.PropertiesCache;
import com.walmart.ticketservice.util.SeatHoldIdGenerator;
import com.walmart.ticketservice.util.SeatHoldTimer;
import com.walmart.ticketservice.util.TicketServiceThreadPoolExecutor;
import com.walmart.ticketservice.util.factory.VenueFactory;

/**
 * @author Gopal 
 * Venue service class to get venue object centrally through out
 * the application
 */
public class VenueService {
	private static Venue venue;

	private static VenueFactory venueFactory = new VenueFactory();

	private static Map<String, SeatHold> seatHoldMap = new ConcurrentHashMap<String, SeatHold>();

	private static TicketServiceThreadPoolExecutor tcktSrvcThrdPoolExec;

	/**
	 * Get Ticket service thread pool instantiated
	 * 
	 * @return
	 */
	private static TicketServiceThreadPoolExecutor getThreadPoolExecutor() {
		int minThreadCnt = Integer.parseInt(PropertiesCache.getValue(
				Messages.MIN_THREAD_CNT, "20"));
		int maxThreadCnt = Integer.parseInt(PropertiesCache.getValue(
				Messages.MAX_THREAD_CNT, "30"));
		int maxTimeOut = Integer.parseInt(PropertiesCache.getValue(
				Messages.MAX_TIME_OUT, "120"));
		if (tcktSrvcThrdPoolExec == null) {
			tcktSrvcThrdPoolExec = new TicketServiceThreadPoolExecutor(
					minThreadCnt, maxThreadCnt, maxTimeOut);
		}
		return tcktSrvcThrdPoolExec;
	}

	/**
	 * @return
	 * @throws ServiceException
	 */
	public synchronized static Venue getVenue() throws ServiceException {
		try {
			if (venue == null) {
				venue = venueFactory.getVenueCreated();
				return venue;
			} else {
				return venue;
			}
		} catch (Exception e) {
			throw new ServiceException("Error in getVenue " + e.getStackTrace());
		}
	}

	/**
	 * Hold seats for customer
	 * 
	 * @param levels
	 * @param numSeats
	 * @param custEmail
	 * @return
	 * @throws ServiceException
	 */
	public synchronized static SeatHold holdSeats(List<Level> levels,
			int numSeats, String custEmail) throws ServiceException {
		try {
			SeatHold seatHold = new SeatHold();
			List<SeatHold.SeatDetails> seatDetailsList = new ArrayList<SeatHold.SeatDetails>();
			SeatHold.SeatDetails seatDtls = null;
			int seatsFound = 0;
			double totalPrice = 0, levelPrice = 0;
			for (Level level : levels) {
				List<Row> rows = level.getAvailableRowList();
				levelPrice = CommonUtils.getPrice(level.getPrice());
				for (Row row : rows) {
					List<Seat> seats = row.getAvailSeats();
					for (Seat seat : seats) {
						seatDtls = new SeatHold.SeatDetails(level.getLevelId(),
								row.getRowId(), seat.getSeatNo());
						seatDetailsList.add(seatDtls);
						seat.setHolded(true);
						seat.setCustomerEmail(custEmail);
						totalPrice = totalPrice + levelPrice;
						seatsFound++;
						if (seatsFound == numSeats)
							break;
					}
					if (seatsFound == numSeats)
						break;
				}
				if (seatsFound == numSeats)
					break;
			}
			seatHold.setNumSeats(numSeats);
			seatHold.setSeatDetails(seatDetailsList);
			seatHold.setSeatHoldId(SeatHoldIdGenerator
					.getNextUniqueSeatHoldId());
			seatHold.setCustomerEmail(custEmail);
			seatHold.setTotalPrice(totalPrice);
			seatHoldMap.put(seatHold.getSeatHoldId(), seatHold);
			createSeatHoldTimerAndSubmitThread(seatHold);
			return seatHold;
		} catch (Exception e) {
			throw new ServiceException();
		}
	}

	/**
	 * Reserve seats for customer
	 * 
	 * @param seatHoldId
	 * @param custEmail
	 * @param reserve
	 * @return
	 * @throws ServiceException
	 */
	public synchronized static String reserveSeats(String seatHoldId,
			String custEmail, boolean reserve) throws ServiceException {
		if (seatHoldMap.containsKey(seatHoldId)) {
			SeatHold seatHolded = seatHoldMap.get(seatHoldId);
			for (SeatHold.SeatDetails stDtls : seatHolded.getSeatDetails()) {
				Level holdedLevel = venue.getLevelMap()
						.get(stDtls.getLevelId());
				Row holdedRow = holdedLevel.getRowMap().get(stDtls.getRowNum());
				Seat holdedSeat = holdedRow.getSeatsMap().get(
						stDtls.getSeatNo());
				if (reserve) {
					if (holdedSeat.isHolded()) {
						holdedSeat.setBooked(true);
						holdedSeat.setHolded(false);
						holdedSeat.setCustomerEmail(custEmail);
					}
				} else {
					holdedSeat.setBooked(false);
					holdedSeat.setHolded(false);
					holdedSeat.setCustomerEmail(null);
				}
			}
			seatHoldMap.remove(seatHoldId);
			return Messages.SUCCESS_MESSAGE_BOOK;
		} else {
			return Messages.SEAT_HOLD_ID_NOT_FOUND_MSG;
		}
	}

	/**
	 * Clear holded seats using hold Id
	 * 
	 * @param seatHoldId
	 * @return
	 * @throws ServiceException
	 */
	public synchronized static boolean unReserveHoldedSeats(String seatHoldId)
			throws ServiceException {
		if (seatHoldMap.containsKey(seatHoldId)) {
			reserveSeats(seatHoldId, null, false);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Submit timer task to pool executor
	 * 
	 * @param seatHold
	 * @throws UtilException
	 */
	private static void createSeatHoldTimerAndSubmitThread(SeatHold seatHold)
			throws UtilException {
		SeatHoldTimer holdTimer = new SeatHoldTimer();
		holdTimer.setSeatHold(seatHold);
		holdTimer.setHoldTime(Integer.parseInt(PropertiesCache
				.getValue(Messages.SEAT_HOLD_TIME)));
		getThreadPoolExecutor().submitTimerThread(holdTimer);
	}

	/**
	 * Clear holded seats at shutdown
	 * 
	 * @throws ServiceException
	 */
	private static void cleanUpHoldedSeats() throws ServiceException {
		for (String seatHoldId : seatHoldMap.keySet()) {
			unReserveHoldedSeats(seatHoldId);
		}
	}

	/**
	 * Shutdown the executor started
	 * 
	 * @throws ServiceException
	 * @throws UtilException
	 */
	public static void shutDownExecutor() throws ServiceException,
			UtilException {
		cleanUpHoldedSeats();
		getThreadPoolExecutor().shutDownExecutor();
	}
}
