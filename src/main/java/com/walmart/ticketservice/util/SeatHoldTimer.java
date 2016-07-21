/**
 * 
 */
package com.walmart.ticketservice.util;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.walmart.ticketservice.domain.SeatHold;
import com.walmart.ticketservice.execption.ServiceException;
import com.walmart.ticketservice.impl.VenueService;

/**
 * @author Gopal 
 * Utility class for expiry time of holded seats
 */
public class SeatHoldTimer implements Runnable {
	static Logger log = Logger.getLogger(SeatHoldTimer.class);

	private SeatHold seatHold;

	private int holdTime;

	public SeatHoldTimer() {

	}

	public SeatHold getSeatHold() {
		return seatHold;
	}

	public void setSeatHold(SeatHold seatHold) {
		this.seatHold = seatHold;
	}

	public int getHoldTime() {
		return holdTime;
	}

	public void setHoldTime(int holdTime) {
		this.holdTime = holdTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		try {
			log.debug("Count down started for seatHold : "
					+ seatHold.getSeatHoldId());
			TimeUnit.SECONDS.sleep(getHoldTime());
			boolean isSuccess = VenueService.unReserveHoldedSeats(seatHold
					.getSeatHoldId());
			if (isSuccess)
				log.debug("Successfully cleared holded seats for "
						+ seatHold.getSeatHoldId());
			else
				log.debug("Seat holded might have been booked for "
						+ seatHold.getSeatHoldId());
		} catch (InterruptedException e) {
			log.error("Error in run : " + e.getStackTrace());
		} catch (ServiceException e) {
			log.error("Error in service :" + e.getStackTrace());
		}
	}

	@Override
	public String toString() {
		return "SeatHoldTimer [seatHold=" + seatHold + ", holdTime=" + holdTime
				+ "]";
	}
}
