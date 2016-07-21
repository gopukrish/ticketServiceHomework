/**
 * 
 */
package com.walmart.ticketservice.util.factory.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.walmart.ticketservice.domain.Seat;
import com.walmart.ticketservice.util.factory.SeatFactory;

/**
 * @author Gopal
 * 
 */
public class SeatFactoryTest {
	private SeatFactory seatFactory;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		seatFactory = new SeatFactory();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.walmart.ticketservice.util.factory.SeatFactory#getSeatCreated(int)}
	 * .
	 */
	@Test
	public void testGetSeatCreated() {
		Seat seat = seatFactory.getSeatCreated(1);
		assertNotNull(seat);
		assertEquals(1, seat.getSeatNo());
		assertFalse(seat.isBooked());
		assertFalse(seat.isHolded());
	}

}
