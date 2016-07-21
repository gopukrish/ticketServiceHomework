/**
 * 
 */
package com.walmart.ticketservice.execption;

/**
 * @author Gopal
 * Exception class for factory util classes
 */
public class FactoryUtilException extends UtilException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FactoryUtilException() {
		super();
	}

	public FactoryUtilException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FactoryUtilException(String message, Throwable cause) {
		super(message, cause);
	}

	public FactoryUtilException(String message) {
		super(message);
	}

	public FactoryUtilException(Throwable cause) {
		super(cause);
	}

}
