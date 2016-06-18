/*
 * Created on 03/11/2005
 * */
package br.usjt.bd2.sgbd.xml;

/**
 * @author ricardocury
 */
public class EstruturaException extends Exception {
	private static final long serialVersionUID = 2641140603134462420L;
	private long lineError;
	
	/**
	 * @param lineError
	 */
	public EstruturaException(long lineError) {
		super();
		setLineError( lineError );
	}
	/**
	 * @param message
	 */
	public EstruturaException(String message, long lineError) {
		super(message);
		setLineError( lineError );
	}
	/**
	 * @param message
	 * @param cause
	 */
	public EstruturaException(String message, Throwable cause, long lineError) {
		super(message);
		setLineError( lineError );
	}
	/**
	 * @param cause
	 */
	public EstruturaException(Throwable cause, long lineError) {
		//super(cause);
		setLineError( lineError );
	}
	
	/**
	 * @return
	 */
	public long getLineError() {
		return lineError;
	}
	/**
	 * @param lineError
	 */
	private void setLineError(long lineError) {
		this.lineError = lineError;
	}
}