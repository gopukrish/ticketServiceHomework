/**
 * 
 */
package com.walmart.ticketservice.util.factory;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.walmart.ticketservice.domain.Row;
import com.walmart.ticketservice.domain.Seat;
import com.walmart.ticketservice.execption.FactoryUtilException;

/**
 * @author Gopal 
 * Factory class to create Row objects
 */
public class RowFactory {
	private SeatFactory seatFactory;

	static Logger log = Logger.getLogger(RowFactory.class);

	/**
	 * 
	 */
	public RowFactory() {
		seatFactory = new SeatFactory();
	}

	/**
	 * Get Row created for level
	 * 
	 * @param rowNum
	 * @param numSeats
	 * @return
	 * @throws FactoryUtilException
	 */
	public Row getRowCreated(int rowNum, int numSeats)
			throws FactoryUtilException {
		try {
			Map<Integer, Seat> seatsMap = new HashMap<Integer, Seat>();
			for (int seatNo = 1; seatNo <= numSeats; seatNo++) {
				Seat seat = seatFactory.getSeatCreated(seatNo);
				seatsMap.put(seatNo, seat);
			}
			Row row = new Row(rowNum, seatsMap, numSeats);
			log.debug("Created row : " + row);
			return row;
		} catch (Exception e) {
			log.error("Error in creating Row : " + e.getMessage());
			throw new FactoryUtilException(e.getMessage());
		}
	}
}
