/**
 * 
 */
package com.walmart.ticketservice.util;

import com.walmart.ticketservice.domain.SeatHold;

/**
 * @author Gopal
 * Print ticket to console with formatted
 */
public class TicketPrinter {
	private SeatHold seatHold;
	
	public TicketPrinter(){
	}

	public SeatHold getSeatHold() {
		return seatHold;
	}

	public void setSeatHold(SeatHold seatHold) {
		this.seatHold = seatHold;
	}
	
	/**
	 * Get the Level, Row and Seat details formatted
	 * @return - Formatted level, row and seat details
	 */
	private String getSeatDetails(){
		StringBuffer sb = new StringBuffer();
		int i=0;
		for(SeatHold.SeatDetails stDtls : seatHold.getSeatDetails() ){
			if(i%2 == 0)
				sb.append("\n");
			sb.append("\tLevel : " + stDtls.getLevelId() + " Row No : " + stDtls.getRowNum() + " Seat No : " + stDtls.getSeatNo() + "\t");
			i++;
		}
		return sb.toString();
	}
	
	/**
	 * Print the ticket in formatted manner
	 */
	public void printTicket(){
		System.out.println("\nYour confirmation details");
		System.out.println("_________________________________________________TICKET DETAILS_________________________________________________");
		System.out.println("\tConfirmation\\Seat Hold ID  \t"+ seatHold.getSeatHoldId());
		System.out.println("\tEmail  \t\t\t\t" + seatHold.getCustomerEmail());
		System.out.println("\tNo. of seats  \t\t\t" + seatHold.getNumSeats());
		System.out.println("\tTotal Price  \t\t\t$" + seatHold.getTotalPrice());
		System.out.print("\n\tSeat Details");
		System.out.println(getSeatDetails());
		System.out.println("________________________________________________________________________________________________________________");
		System.out.println("*** Note : Your seats  will be holded for " + PropertiesCache.getValue(Messages.SEAT_HOLD_TIME) + " secs");
	}
}
