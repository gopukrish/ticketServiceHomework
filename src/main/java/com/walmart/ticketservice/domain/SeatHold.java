/**
 * 
 */
package com.walmart.ticketservice.domain;

import java.util.List;

/**
 * @author Gopal 
 * Domain object to store seat hold details
 */
public class SeatHold {
	private String seatHoldId;
	private int numSeats;
	private List<SeatDetails> seatDetails;
	private double totalPrice;
	private String customerEmail;

	public SeatHold() {

	}

	public String getSeatHoldId() {
		return seatHoldId;
	}

	public void setSeatHoldId(String seatHoldId) {
		this.seatHoldId = seatHoldId;
	}

	public int getNumSeats() {
		return numSeats;
	}

	public void setNumSeats(int numSeats) {
		this.numSeats = numSeats;
	}

	public List<SeatDetails> getSeatDetails() {
		return seatDetails;
	}

	public void setSeatDetails(List<SeatDetails> seatDetails) {
		this.seatDetails = seatDetails;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	@Override
	public String toString() {
		return "SeatHold [seatHoldId=" + seatHoldId + ", numSeats=" + numSeats
				+ ", seatDetails=" + seatDetails + ", totalPrice=" + totalPrice
				+ ", customerEmail=" + customerEmail + "]";
	}

	public static class SeatDetails {
		private int levelId;
		private int rowNum;
		private int seatNo;

		public SeatDetails() {

		}

		public SeatDetails(int levelId, int rowNum, int seatNo) {
			this.levelId = levelId;
			this.rowNum = rowNum;
			this.seatNo = seatNo;
		}

		public int getLevelId() {
			return levelId;
		}

		public void setLevelId(int levelId) {
			this.levelId = levelId;
		}

		public int getRowNum() {
			return rowNum;
		}

		public void setRowNum(int rowNum) {
			this.rowNum = rowNum;
		}

		public int getSeatNo() {
			return seatNo;
		}

		public void setSeatNo(int seatNo) {
			this.seatNo = seatNo;
		}

		@Override
		public String toString() {
			return "SeatDetails [levelId=" + levelId + ", rowNum=" + rowNum
					+ ", seatNo=" + seatNo + "]";
		}
	}
}
