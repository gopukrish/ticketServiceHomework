/**
 * 
 */
package com.walmart.ticketservice.util.factory.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.walmart.ticketservice.domain.Row;
import com.walmart.ticketservice.execption.FactoryUtilException;
import com.walmart.ticketservice.util.factory.RowFactory;

/**
 * @author Gopal
 * 
 */
public class TestRowFactory {
	private RowFactory rowFactory;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		rowFactory = new RowFactory();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.walmart.ticketservice.util.factory.RowFactory#getRowCreated(int, int)}
	 * .
	 */
	@Test
	public void testGetRowCreated() {
		try {
			Row row = rowFactory.getRowCreated(1, 10);
			assertNotNull(row);
			assertNotNull(row.getSeatsMap());
			assertEquals(10, row.getAvailableSeats());
		} catch (FactoryUtilException e) {
			e.printStackTrace();
		}
	}

}
