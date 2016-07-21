/**
 * 
 */
package com.walmart.ticketservice.util;

/**
 * @author Gopal 
 * Constant properties accessed in application
 */
public interface Messages {
	String PROPS_FILE = "ticketservice";
	String PROPS_READ_ERROR = "Unable to read/process properties file";

	/* Level related properties suffix */
	String LEVEL_ID_SUFFIX = ".id";
	String LEVEL_NAME_SUFFIX = ".name";
	String LEVEL_PRICE_SUFFIX = ".price";
	String LEVEL_ROWS_SUFFIX = ".rows";
	String LEVEL_ROWS_SEATS_SUFFIX = ".row.seats";
	String LEVEL_ROW_NUMBERING_SUFFIX = ".row.numbering";

	String VENUE_LEVELS = "venue.levels";

	String SEAT_HOLD_TIME = "seat.hold.time";
	String SEAT_HOLD_ID_PREFIX = "TS-SH-";

	String SUCCESS_MESSAGE_BOOK = "\nSuccessfully booked your seats... Enjoy...";
	String SEAT_HOLD_ID_NOT_FOUND_MSG = "\nSeat Hold\\Confirmation Id not found, Please check again";

	String MIN_THREAD_CNT = "min.thread.count";
	String MAX_THREAD_CNT = "max.thread.count";
	String MAX_TIME_OUT = "max.time.out";
}
