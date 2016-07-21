/**
 * 
 */
package com.walmart.ticketservice.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.walmart.ticketservice.execption.UtilException;

/**
 * @author Gopal 
 * Utility class which will submit seatholdtimer threads
 */
public class TicketServiceThreadPoolExecutor {
	private ThreadPoolExecutor tcktSrvThrdPoolExec;

	static Logger log = Logger.getLogger(TicketServiceThreadPoolExecutor.class);

	public TicketServiceThreadPoolExecutor(int minThreadCnt, int maxThreadCnt,
			int maxTimeOut) {
		tcktSrvThrdPoolExec = new ThreadPoolExecutor(minThreadCnt,
				maxThreadCnt, maxTimeOut, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(minThreadCnt));
		log.debug("Started TicketServiceThreadPoolExecutor");
	}

	/**
	 * Submits the timer thread to thread pool
	 * 
	 * @param seatHoldTimer
	 * @throws UtilException
	 */
	public void submitTimerThread(SeatHoldTimer seatHoldTimer)
			throws UtilException {
		log.debug("Submitting runnable task to executor : " + seatHoldTimer);
		try {
			tcktSrvThrdPoolExec.submit(seatHoldTimer);
		} catch (Exception e) {
			log.error("Error in submitTimerThread " + e.getStackTrace());
			throw new UtilException("Error in submitting Task "
					+ e.getStackTrace());
		}
	}

	/**
	 * Shutdowns the thread pool executor
	 * 
	 * @throws UtilException
	 */
	public void shutDownExecutor() throws UtilException {
		log.debug("Shutting down TicketServiceThreadPoolExecutor");
		try {
			tcktSrvThrdPoolExec.shutdownNow();
		} catch (Exception e) {
			log.error("Error in shutDownExecutor " + e.getStackTrace());
			throw new UtilException("Error in shutting down executor "
					+ e.getStackTrace());
		}
	}
}
