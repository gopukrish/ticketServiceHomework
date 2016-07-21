/**
 * 
 */
package com.walmart.ticketservice.util.factory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.walmart.ticketservice.domain.Level;
import com.walmart.ticketservice.domain.Venue;
import com.walmart.ticketservice.execption.FactoryUtilException;
import com.walmart.ticketservice.util.Messages;
import com.walmart.ticketservice.util.PropertiesCache;

/**
 * @author Gopal 
 * Factory class to create venue with all levels
 */
public class VenueFactory {

	private LevelFactory levelFactory;

	static Logger log = Logger.getLogger(VenueFactory.class);

	public VenueFactory() {
		levelFactory = new LevelFactory();
	}

	/**
	 * Get all level list
	 * 
	 * @param venueLevels
	 * @return
	 */
	private List<String> getLevelList(String venueLevels) {
		String[] levels = venueLevels.split("\\|");
		return Arrays.asList(levels);
	}

	/**
	 * Get venue created
	 * 
	 * @return
	 * @throws FactoryUtilException
	 */
	public Venue getVenueCreated() throws FactoryUtilException {
		String venueLevels = PropertiesCache.getValue(Messages.VENUE_LEVELS);
		List<String> levels = getLevelList(venueLevels);
		List<Level> levelsList = new ArrayList<Level>();
		Map<Integer, Level> venueMap = new HashMap<Integer, Level>();
		Venue venue = new Venue();

		for (String levelId : levels) {
			Level level = levelFactory.getLevelCreated(levelId);
			levelsList.add(level);
			venueMap.put(level.getLevelId(), level);
		}
		venue.setAvailableLevels(levelsList);
		venue.setLevelMap(venueMap);
		log.debug("Venue Created : " + venue);
		return venue;
	}
}
