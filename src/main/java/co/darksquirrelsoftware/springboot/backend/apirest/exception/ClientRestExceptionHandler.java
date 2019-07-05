package co.darksquirrelsoftware.springboot.backend.apirest.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ClientRestExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ClientErrorResponse> handleException(ClientNotFoundException exception) {

		ClientErrorResponse clientErrorResponse = new ClientErrorResponse(HttpStatus.NOT_FOUND.value(),
				exception.getLocalizedMessage().toString(), System.currentTimeMillis());

		return new ResponseEntity<ClientErrorResponse>(clientErrorResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ClientErrorResponse> handleException(ClientEmailDuplicatedException exception) {

		ClientErrorResponse clientErrorResponse = new ClientErrorResponse(HttpStatus.BAD_REQUEST.value(),
				exception.getLocalizedMessage().toString(), System.currentTimeMillis());

		return new ResponseEntity<ClientErrorResponse>(clientErrorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<ClientErrorResponse> handleException(Exception exception) {
		ClientErrorResponse clientErrorResponse = new ClientErrorResponse(HttpStatus.BAD_REQUEST.value(),
				exception.getLocalizedMessage().toString(), System.currentTimeMillis());

		return new ResponseEntity<ClientErrorResponse>(clientErrorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<ClientErrorResponse> handleException(AccessDeniedException exception) {
		ClientErrorResponse clientErrorResponse = new ClientErrorResponse(HttpStatus.FORBIDDEN.value(),
				exception.getLocalizedMessage().toString(), System.currentTimeMillis());

		return new ResponseEntity<ClientErrorResponse>(clientErrorResponse, HttpStatus.FORBIDDEN);
	}

}
