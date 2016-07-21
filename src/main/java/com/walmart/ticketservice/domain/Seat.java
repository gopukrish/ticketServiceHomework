/**
 * 
 */
package com.walmart.ticketservice.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Gopal 
 * Domain object to store each seat details
 */
@XmlRootElement(name = "Seat")
@XmlAccessorType(XmlAccessType.FIELD)
public class Seat implements Comparable<Seat> {
	private int seatNo;
	private boolean isBooked;
	private boolean isHolded;
	private String customerEmail;

	public Seat() {

	}

	public Seat(int seatNo, boolean isBooked, String customerEmail) {
		this.seatNo = seatNo;
		this.isBooked = isBooked;
		this.customerEmail = customerEmail;
	}

	public int getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}

	public boolean isBooked() {
		return isBooked;
	}

	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}

	public boolean isHolded() {
		return isHolded;
	}

	public void setHolded(boolean isHolded) {
		this.isHolded = isHolded;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	@Override
	public String toString() {
		return "Seat [ seatNo=" + seatNo + ", isBooked=" + isBooked
				+ ", isHolded=" + isHolded + ", customerEmail=" + customerEmail
				+ " ]";
	}

	public int compareTo(Seat seat2) {
		return this.getSeatNo() - seat2.getSeatNo();
	}

}
