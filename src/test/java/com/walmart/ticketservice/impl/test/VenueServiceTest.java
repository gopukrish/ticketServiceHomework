/**
 * 
 */
package com.walmart.ticketservice.impl.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.walmart.ticketservice.domain.Level;
import com.walmart.ticketservice.domain.SeatHold;
import com.walmart.ticketservice.domain.Venue;
import com.walmart.ticketservice.execption.ServiceException;
import com.walmart.ticketservice.impl.VenueService;

/**
 * @author Gopal
 * 
 */
public class VenueServiceTest {
	private Venue venue;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.walmart.ticketservice.impl.VenueService#getVenue()}.
	 */
	@Test
	public void testGetVenue() {
		try {
			venue = VenueService.getVenue();
			assertNotNull(venue);
			assertNotNull(venue.getAvailableLevels());
			assertNotNull(venue.getLevelMap());
			int totalSeats = 0;
			for (Level level : venue.getAvailableLevels()) {
				totalSeats = totalSeats + level.getAvailableSeats();
			}
			assertEquals(venue.getAvailableSeats(), totalSeats);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for
	 * {@link com.walmart.ticketservice.impl.VenueService#holdSeats(java.util.List, int, java.lang.String)}
	 * .
	 */
	@Test
	public void testHoldSeats() {
		try {
			venue = VenueService.getVenue();
			assertNotNull(venue);
			int availableSeatsBeforeHold = venue.getAvailableSeats();
			SeatHold seatHold = VenueService.holdSeats(
					venue.getAvailableLevels(), 50, "abc@gmail.com");
			assertNotNull(seatHold);
			int availSeatsAfterHold = venue.getAvailableSeats();
			assertEquals(availSeatsAfterHold, (availableSeatsBeforeHold - 50));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for
	 * {@link com.walmart.ticketservice.impl.VenueService#reserveSeats(java.lang.String, java.lang.String, boolean)}
	 * .
	 */
	@Test
	public void testReserveSeats() {
		try {
			venue = VenueService.getVenue();
			assertNotNull(venue);
			int availableSeatsBeforeReserve = venue.getAvailableSeats();
			SeatHold seatHold = VenueService.holdSeats(
					venue.getAvailableLevels(), 100, "xyz@gmail.com");
			assertNotNull(seatHold);
			VenueService.reserveSeats(seatHold.getSeatHoldId(),
					"xyz@gmail.com", true);
			int availSeatsAfterReserve = venue.getAvailableSeats();
			assertEquals(availSeatsAfterReserve,
					(availableSeatsBeforeReserve - 100));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for
	 * {@link com.walmart.ticketservice.impl.VenueService#unReserveHoldedSeats(java.lang.String)}
	 * .
	 */
	@Test
	public void testUnReserveHoldedSeats() {
		try {
			venue = VenueService.getVenue();
			assertNotNull(venue);
			int availableSeatsBeforeHold = venue.getAvailableSeats();
			SeatHold seatHold = VenueService.holdSeats(
					venue.getAvailableLevels(), 200, "test@gmail.com");
			assertNotNull(seatHold);
			int availSeatsAfterHold = venue.getAvailableSeats();
			assertEquals(availSeatsAfterHold, (availableSeatsBeforeHold - 200));
			VenueService.unReserveHoldedSeats(seatHold.getSeatHoldId());
			assertEquals(availableSeatsBeforeHold, venue.getAvailableSeats());
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

}
