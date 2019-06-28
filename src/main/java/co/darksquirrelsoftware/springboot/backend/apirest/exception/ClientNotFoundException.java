package co.darksquirrelsoftware.springboot.backend.apirest.exception;

public class ClientNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1302344239728488472L;

	public ClientNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ClientNotFoundException(String arg0) {
		super(arg0);
	}

	public ClientNotFoundException(Throwable arg0) {
		super(arg0);
	}

}
