/**
 * 
 */
package com.walmart.ticketservice.util.factory.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.walmart.ticketservice.domain.Level;
import com.walmart.ticketservice.execption.FactoryUtilException;
import com.walmart.ticketservice.util.factory.LevelFactory;

/**
 * @author Gopal
 * 
 */
public class TestLevelFactory {
	private LevelFactory levelFactory;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		levelFactory = new LevelFactory();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.walmart.ticketservice.util.factory.LevelFactory#getLevelCreated(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetLevelCreated() {
		try {
			Level level = levelFactory.getLevelCreated("main");
			assertNotNull(level);
			Level level1 = levelFactory.getLevelCreated("main1");
			assertNull(level1);
		} catch (FactoryUtilException e) {
			
		}

	}

}
