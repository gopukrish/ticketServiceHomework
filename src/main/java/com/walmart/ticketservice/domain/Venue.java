/**
 * 
 */
package com.walmart.ticketservice.domain;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.walmart.ticketservice.util.CommonUtils;
import com.walmart.ticketservice.util.Predicate;

/**
 * @author Gopal 
 * Domain object to store venue details
 */
public class Venue {
	private List<Level> availableLevels;
	private Map<Integer, Level> levelMap;
	private int availableSeats;

	public Venue() {

	}

	public List<Level> getAvailableLevels() {
		Predicate<Level> availableLevelPredicate = new Predicate<Level>() {
			public boolean apply(Level level) {
				if (level.getAvailableSeats() > 0)
					return true;
				else
					return false;
			}
		};
		Collection<Level> availLevels = CommonUtils.filter(getLevelMap()
				.values(), availableLevelPredicate);
		Collections.sort(this.availableLevels);
		setAvailableLevels((List<Level>) availLevels);
		return this.availableLevels;
	}

	public void setAvailableLevels(List<Level> availablelevels) {
		this.availableLevels = availablelevels;
	}

	public Map<Integer, Level> getLevelMap() {
		return levelMap;
	}

	public void setLevelMap(Map<Integer, Level> levelMap) {
		this.levelMap = levelMap;
	}

	public int getAvailableSeats() {
		availableSeats = 0;
		for (Level level : getAvailableLevels()) {
			availableSeats = availableSeats + level.getAvailableSeats();
		}
		return availableSeats;
	}

	public int getAvailableSeats(int levelId) {
		int numberSeats = levelMap.get(levelId).getAvailableSeats();
		return numberSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	@Override
	public String toString() {
		return "Venue [ availableLevels =" + availableLevels + ", levelMap="
				+ levelMap + " ]";
	}
}
