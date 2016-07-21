/**
 * 
 */
package com.walmart.ticketservice.util.factory.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.walmart.ticketservice.domain.Venue;
import com.walmart.ticketservice.execption.FactoryUtilException;
import com.walmart.ticketservice.util.factory.VenueFactory;

/**
 * @author Gopal
 * 
 */
public class TestVenueFactory {
	private VenueFactory venueFactory;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		venueFactory = new VenueFactory();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.walmart.ticketservice.util.factory.VenueFactory#getVenueCreated()}
	 * .
	 */
	@Test
	public void testGetVenueCreated() {
		try {
			Venue venue = venueFactory.getVenueCreated();
			assertNotNull(venue);
			assertNotNull(venue.getLevelMap());
			assertNotNull(venue.getAvailableLevels());
		} catch (FactoryUtilException e) {
			e.printStackTrace();
		}
	}

}
