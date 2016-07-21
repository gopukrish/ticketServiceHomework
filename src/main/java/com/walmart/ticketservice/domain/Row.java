/**
 * 
 */
package com.walmart.ticketservice.domain;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.walmart.ticketservice.util.CommonUtils;
import com.walmart.ticketservice.util.Predicate;

/**
 * @author Gopal 
 * Domain object class to map seat objects in each row
 */
@XmlRootElement(name = "Row")
@XmlAccessorType(XmlAccessType.FIELD)
public class Row implements Comparable<Row> {
	private int rowId;
	private Map<Integer, Seat> seatsMap;
	private int availableSeats;

	public Row() {

	}

	public Row(int rowId, Map<Integer, Seat> seatsMap, int availableSeats) {
		this.rowId = rowId;
		this.seatsMap = seatsMap;
		this.availableSeats = availableSeats;
	}

	public int getRowId() {
		return rowId;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
	}

	public Map<Integer, Seat> getSeatsMap() {
		return seatsMap;
	}

	public void setSeatsMap(Map<Integer, Seat> seatsMap) {
		this.seatsMap = seatsMap;
	}

	public int getAvailableSeats() {
		List<Seat> availSeats = getAvailSeats();
		availableSeats = availSeats.size();
		return availableSeats;
	}

	public List<Seat> getAvailSeats() {
		Predicate<Seat> availableSeatPredicate = new Predicate<Seat>() {
			public boolean apply(Seat seat) {
				return (!seat.isBooked() && !seat.isHolded());
			}
		};
		Collection<Seat> availSeats = CommonUtils.filter(
				getSeatsMap().values(), availableSeatPredicate);
		Collections.sort((List<Seat>) availSeats);
		return (List<Seat>) availSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	@Override
	public String toString() {
		return "Row [ rowId=" + rowId + ", seatsMap = " + seatsMap
				+ ", availableSeats=" + availableSeats + " ]";
	}

	public int compareTo(Row row2) {
		return this.rowId - row2.rowId;
	}

}
