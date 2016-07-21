/**
 * 
 */
package com.walmart.ticketservice.util.factory;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.walmart.ticketservice.domain.Level;
import com.walmart.ticketservice.domain.Row;
import com.walmart.ticketservice.execption.FactoryUtilException;
import com.walmart.ticketservice.util.Messages;
import com.walmart.ticketservice.util.PropertiesCache;

/**
 * @author Gopal 
 * Factory class to create level objects which inturn create row
 *         and seats objects using their factories
 */
public class LevelFactory {
	private RowFactory rowFactory;

	static Logger log = Logger.getLogger(LevelFactory.class);

	public LevelFactory() {
		rowFactory = new RowFactory();
	}

	/**
	 * Get Level object created and return it
	 * 
	 * @param levelIdentifier
	 * @return
	 * @throws FactoryUtilException
	 */
	public Level getLevelCreated(String levelIdentifier)
			throws FactoryUtilException {
		Level level = null;
		log.debug("Creating Level from properties");
		level = createLevel(levelIdentifier);
		return level;
	}

	/**
	 * Create Level object from scratch using properties
	 * 
	 * @param levelIdentifier
	 * @return
	 * @throws FactoryUtilException
	 */
	private Level createLevel(String levelIdentifier)
			throws FactoryUtilException {
		try {
			int levelId = Integer.parseInt(PropertiesCache
					.getValue(levelIdentifier + Messages.LEVEL_ID_SUFFIX));
			String levelName = PropertiesCache.getValue(levelIdentifier
					+ Messages.LEVEL_NAME_SUFFIX);
			String levelPrice = PropertiesCache.getValue(levelIdentifier
					+ Messages.LEVEL_PRICE_SUFFIX);
			int numRows = Integer.parseInt(PropertiesCache
					.getValue(levelIdentifier + Messages.LEVEL_ROWS_SUFFIX));
			int numRowSeats = Integer.parseInt(PropertiesCache
					.getValue(levelIdentifier
							+ Messages.LEVEL_ROWS_SEATS_SUFFIX));
			int totalSeats = numRows * numRowSeats;

			Map<Integer, Row> rowsMap = new HashMap<Integer, Row>();

			for (int rowNum = 1; rowNum <= numRows; rowNum++) {
				Row row = rowFactory.getRowCreated(rowNum, numRowSeats);
				rowsMap.put(rowNum, row);
			}
			Level level = new Level(levelId, levelIdentifier, levelName,
					levelPrice, numRows, totalSeats, totalSeats, rowsMap);
			log.debug("Creating Level : " + level);
			return level;
		} catch (Exception e) {
			log.error("Error creating level : " + e.getMessage());
			throw new FactoryUtilException(e.getMessage());
		}
	}

}
