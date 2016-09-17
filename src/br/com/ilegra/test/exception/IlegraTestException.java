package br.com.ilegra.test.exception;

/**
 * Class to map application exceptions
 * @author Tales Viegas - tales@terra.com.br
 *
 */
public class IlegraTestException extends Exception {

	private static final long serialVersionUID = 4162239348926538675L;
	private boolean fatal = false;

	public IlegraTestException() {
		super();
	}

	public IlegraTestException(String message, boolean fatal) {
		super(message);
		this.fatal = fatal;
	}

	/**
	 * @return true if is a fatal error
	 */
	public boolean isFatal(){
		return this.fatal;
	}
}
