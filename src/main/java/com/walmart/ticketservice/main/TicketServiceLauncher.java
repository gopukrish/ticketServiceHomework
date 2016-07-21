/**
 * 
 */
package com.walmart.ticketservice.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.walmart.ticketservice.TicketService;
import com.walmart.ticketservice.domain.SeatHold;
import com.walmart.ticketservice.execption.ServiceException;
import com.walmart.ticketservice.execption.UtilException;
import com.walmart.ticketservice.impl.TicketServiceImpl;
import com.walmart.ticketservice.impl.VenueService;
import com.walmart.ticketservice.util.TicketPrinter;

/**
 * @author Gopal 
 * Main Launcher class for Ticket Service
 */
public class TicketServiceLauncher {
	private TicketService ticketService;
	private TicketPrinter printer;
	private InputStreamReader inputStreamReader;
	private BufferedReader br;
	static Logger log = Logger.getLogger(TicketServiceLauncher.class);

	public TicketServiceLauncher() {
		ticketService = new TicketServiceImpl();
		printer = new TicketPrinter();
		inputStreamReader = new InputStreamReader(System.in);
		br = new BufferedReader(inputStreamReader);
	}

	/**
	 * Print available tickets in venue
	 * 
	 * @throws ServiceException
	 */
	private void printAvailableTickets() throws ServiceException {
		int venueAvailSeats = ticketService.numSeatsAvailable();
		System.out.println("\nTotal available seats in venue are :\t"
				+ venueAvailSeats);
	}

	/**
	 * Print available levels in Venue
	 * 
	 * @throws ServiceException
	 */
	private void printVenueLevels() throws ServiceException {
		List<Integer> levels = new ArrayList<Integer>();
		levels.addAll(ticketService.getVenueLevels().keySet());
		Collections.sort(levels);
		System.out.println("\nAvailable Levels in Venue\n");
		for (int level : levels) {
			System.out.println(level + "\t"
					+ ticketService.getVenueLevels().get(level));
		}
		System.out.println("\nPlease choose Level Ids above");
	}

	/**
	 * Print available seats in level id
	 * 
	 * @param levelId
	 * @throws ServiceException
	 */
	private void printAvailableTickets(int levelId) throws ServiceException {
		int venueLvlAvailSeats = ticketService.numSeatsAvailable(levelId);
		System.out.println("\nTotal available seats in Level " + levelId
				+ " are :\t" + venueLvlAvailSeats);
	}

	/* Hold Seat related */
	/**
	 * Validate Level input
	 * 
	 * @param input
	 * @return
	 * @throws ServiceException
	 */
	private boolean isValidLevelInput(String input) throws ServiceException {
		if (input == null || input.equals("") || !input.matches("\\d"))
			return false;
		if (ticketService.getVenueLevels().keySet()
				.contains(Integer.parseInt(input)))
			return true;
		return false;
	}

	/**
	 * Validate number of seats input
	 * 
	 * @param input
	 * @return
	 */
	private boolean isValidNumSeats(String input) {
		try {
			if (input == null || input.equals("") || !input.matches("\\d+")
					|| Integer.parseInt(input) == 0)
				return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * Validate Email
	 * 
	 * @param email
	 * @return
	 */
	private boolean isValidEmail(String email) {
		String emailRegex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+\\.com";
		try {
			if (email == null || email.equals("") || !email.matches(emailRegex))
				return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * Get Number of Seats Input
	 * 
	 * @return
	 */
	private int getNumSeatsInput() {
		boolean isValidSeatsInput = false;
		String seatInput;
		do {
			System.out.println("\nPlease enter number of seats to hold : \t");
			seatInput = readInput();
			isValidSeatsInput = isValidNumSeats(seatInput);
			if (!isValidSeatsInput)
				System.out
						.println("\nInvalid number of seats, please enter valid number : ");
		} while (!isValidSeatsInput);
		return Integer.parseInt(seatInput);
	}

	/**
	 * Get customer Email input
	 * 
	 * @return
	 */
	private String getCustomerEmailInput() {
		boolean isValidEmail = false;
		String emailInput;
		do {
			System.out.println("\nPlease enter your Email Id : \t");
			emailInput = readInput();
			isValidEmail = isValidEmail(emailInput);
			if (!isValidEmail)
				System.out
						.println("\nInvalid Email, please enter valid Email : ");
		} while (!isValidEmail);
		return emailInput;
	}

	/**
	 * Get Level Id Input
	 * 
	 * @return
	 * @throws ServiceException
	 */
	private int getLevelIdInput() throws ServiceException {
		boolean isValid = false;
		String lvlInput;
		do {
			printVenueLevels();
			lvlInput = readInput();
			isValid = isValidLevelInput(lvlInput);
			if (!isValid) {
				System.out
						.println("\nInvalid Level input, please enter only below values");
			}
		} while (!isValid);
		return Integer.parseInt(lvlInput);
	}

	/**
	 * check Seat Availability
	 * 
	 * @param numSeats
	 * @return
	 * @throws ServiceException
	 */
	private boolean checkSeatAvailability(int numSeats) throws ServiceException {
		if (ticketService.numSeatsAvailable() >= numSeats)
			return true;
		return false;
	}

	/**
	 * check Seat Availability
	 * 
	 * @param numSeats
	 * @param levelId
	 * @return
	 * @throws ServiceException
	 */
	private boolean checkSeatAvailability(int numSeats, int levelId)
			throws ServiceException {
		if (ticketService.numSeatsAvailable(levelId) >= numSeats)
			return true;
		return false;
	}

	/**
	 * check Seat Availability
	 * 
	 * @param numSeats
	 * @param minLevelId
	 * @param maxLevelId
	 * @return
	 * @throws ServiceException
	 */
	private boolean checkSeatAvailability(int numSeats, int minLevelId,
			int maxLevelId) throws ServiceException {
		int totalSeats = 0;
		for (int i = minLevelId; i <= maxLevelId; i++)
			totalSeats = totalSeats + ticketService.numSeatsAvailable(i);
		if (totalSeats >= numSeats)
			return true;
		return false;
	}

	/**
	 * Get seat hold id input
	 * 
	 * @return
	 */
	private String getSeatHoldIdInput() {
		boolean isValidSeatHldId = false;
		String hldId;
		do {
			System.out
					.println("\nPlease enter Seat Hold ID/Confirmation number :\t");
			hldId = readInput();
			if (hldId == null || hldId.equals("")) {
				System.out
						.println("\nInvalid input, please enter valid confirmation number :\t");
			} else
				isValidSeatHldId = true;
		} while (!isValidSeatHldId);
		return hldId;
	}

	/* Hold Seat related */

	/**
	 * Print Header
	 */
	private void printHeader() {
		System.out
				.println("***************** Welcome To Venue Ticket Booking Service *****************");
	}

	/**
	 * Print Menu
	 */
	private void printMenu() {
		System.out.println("\n--------------------------------------");
		System.out.println("Please enter below options : ");
		System.out.println("1:\tGet available seats in venue");
		System.out.println("2:\tGet available seats by Level");
		System.out.println("3:\tHold seats in any level");
		System.out.println("4:\tHold seats in specific level");
		System.out.println("5:\tHold seats by level range");
		System.out.println("6:\tReserve seats");
		System.out.println("7:\tQuit the application");
		System.out.println("--------------------------------------\n");
	}

	/**
	 * Process user input and provide options for operation
	 * 
	 * @param input
	 * @throws ServiceException
	 * @throws UtilException
	 * @throws DaoException
	 */
	private void processInput(String input) throws ServiceException,
			UtilException {
		if (!input.matches("[1-7]")) {
			System.out
					.println("\nInvalid input , Please enter options between 1-7 only\n");
			return;
		}
		char chInput = input.charAt(0);
		switch (chInput) {
		case '1':
			printAvailableTickets();
			break;

		case '2':
			int lvlId = getLevelIdInput();
			printAvailableTickets(lvlId);
			break;
		case '3':
			int numSeats = getNumSeatsInput();
			String custEmail = getCustomerEmailInput();
			if (checkSeatAvailability(numSeats)) {
				SeatHold seatHold = ticketService.findAndHoldSeats(numSeats,
						custEmail);
				printer.setSeatHold(seatHold);
				printer.printTicket();
			} else {
				System.out
						.println("\nSorry ... Your request can't be processed due to seats unavailability in whole venue");
			}
			break;
		case '4':
			int noSeats = getNumSeatsInput();
			String customerEmail = getCustomerEmailInput();
			System.out.println("\nPlease enter level Id to hold seats in\t");
			int levelId = getLevelIdInput();
			if (checkSeatAvailability(noSeats, levelId)) {
				SeatHold seatLevelHold = ticketService.findAndHoldSeats(
						noSeats, levelId, customerEmail);
				printer.setSeatHold(seatLevelHold);
				printer.printTicket();
			} else {
				System.out
						.println("\nSorry ... Your request can't be processed due to seats unavailability in Level "
								+ levelId);
			}
			break;
		case '5':
			int seats = getNumSeatsInput();
			String custmEmail = getCustomerEmailInput();
			System.out.println("\nPlease enter level range Id to hold seats");
			System.out.println("\nPlease enter minimum level Id : \t");
			int minLevelId = getLevelIdInput();
			int maxLevelId = minLevelId;
			do {
				System.out.println("\nPlease enter maximum level Id : \t");
				maxLevelId = getLevelIdInput();
				if (maxLevelId <= minLevelId)
					System.out
							.println("\nMaxium Level id should be greater than min level Id");
			} while (maxLevelId <= minLevelId);
			if (checkSeatAvailability(seats, minLevelId, maxLevelId)) {
				SeatHold seatLevelHold = ticketService.findAndHoldSeats(seats,
						minLevelId, maxLevelId, custmEmail);
				printer.setSeatHold(seatLevelHold);
				printer.printTicket();
			} else {
				System.out
						.println("\nSorry ... Your request can't be processed due to seats unavailability in provided Level Range");
			}
			break;
		case '6':
			String seatHldId = getSeatHoldIdInput();
			String custMail = getCustomerEmailInput();
			String message = ticketService.reserveSeats(seatHldId, custMail);
			System.out.println(message);
			break;
		case '7':
			System.out.println("\nThanks for using Venue Ticketing Service");
			System.out.println("clearing holded seats, resources");
			System.out.println("Shutting down...");
			VenueService.shutDownExecutor();
			break;
		}
	}

	/**
	 * Read input from keyboard
	 * 
	 * @return
	 */
	private String readInput() {
		String input = null;
		try {
			if (br != null) {
				System.out.println("\nYour input :\t");
				input = br.readLine();
			}
		} catch (IOException e) {
			log.error("Error in read input : " + e.getStackTrace());
		}
		return input;
	}

	public static void main(String[] args) {
		log.debug("Starting application");
		TicketServiceLauncher launcher = new TicketServiceLauncher();
		launcher.printHeader();
		String input;
		do {
			launcher.printMenu();
			input = launcher.readInput();
			try {
				launcher.processInput(input);
			} catch (ServiceException e) {
				log.error("Error in service : " + e.getStackTrace());
			} catch (UtilException e) {
				log.error("Error in Util : " + e.getStackTrace());
			}
		} while (!input.equalsIgnoreCase("7"));
		log.debug("Quiting application");
	}
}
