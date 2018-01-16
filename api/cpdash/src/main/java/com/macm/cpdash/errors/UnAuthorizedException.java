package com.macm.cpdash.errors;

/**
 * 
 * @author Aeroui
 *
 */
public class UnAuthorizedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1996576599414175808L;

	public UnAuthorizedException(String msg) {
		super(msg);
	}

	public UnAuthorizedException() {
		this("You are not allowed for such operation!");
	}

}
