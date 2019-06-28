package co.darksquirrelsoftware.springboot.backend.apirest.exception;

public class ClientEmailDuplicatedException extends RuntimeException {

	private static final long serialVersionUID = 1302344239728488472L;

	public ClientEmailDuplicatedException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ClientEmailDuplicatedException(String arg0) {
		super(arg0);
	}

	public ClientEmailDuplicatedException(Throwable arg0) {
		super(arg0);
	}

}
