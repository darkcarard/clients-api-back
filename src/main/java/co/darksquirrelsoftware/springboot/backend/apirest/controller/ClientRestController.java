package co.darksquirrelsoftware.springboot.backend.apirest.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.darksquirrelsoftware.springboot.backend.apirest.model.entity.Client;
import co.darksquirrelsoftware.springboot.backend.apirest.model.service.ClientService;
import co.darksquirrelsoftware.springboot.backend.apirest.model.service.FileUploadService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ClientRestController {

	private static final Integer NUMBER_OF_ROWS_PER_PAGE = 5;
	private static final String IO_ERROR = "Error with file: ";
	private static final String RESOURCE_ERROR = "Can´t load the file!";
	private static final String ROLE_ADMIN = "ROLE_ADMIN";
	private static final String ROLE_USER = "ROLE_USER";

	private ClientService clientService;
	private FileUploadService fileUploadService;

	@Autowired
	public ClientRestController(ClientService clientService, FileUploadService uploadFileService) {
		this.clientService = clientService;
		this.fileUploadService = uploadFileService;
	}

	@GetMapping("/clients")
	public List<Client> findAllClients() {
		return clientService.findAll();
	}

	@GetMapping("/clients/page/{page}")
	public Page<Client> findAllClients(@PathVariable Integer page) {
		return clientService.findAll(PageRequest.of(page, NUMBER_OF_ROWS_PER_PAGE));
	}

	@Secured({ROLE_ADMIN, ROLE_USER})
	@GetMapping("/clients/{id}")
	public Client findClientById(@PathVariable Long id) {
		return clientService.findById(id);
	}

	@Secured({ROLE_ADMIN})
	@PostMapping("/clients")
	@ResponseStatus(HttpStatus.CREATED)
	public Client saveClient(@Valid @RequestBody Client client, BindingResult bindingResult) {

		validateFieldErrors(bindingResult);

		return clientService.save(client);
	}
	
	@Secured({ROLE_ADMIN})
	@PutMapping("/clients/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Client updateClient(@Valid @RequestBody Client client, @PathVariable Long id, BindingResult bindingResult) {

		validateFieldErrors(bindingResult);

		Client clientTmp = clientService.findById(id);

		clientTmp.setFirstName(client.getFirstName());
		clientTmp.setLastName(client.getLastName());
		clientTmp.setEmail(client.getEmail());
		clientTmp.setCreatedAt(client.getCreatedAt());
		clientTmp.setZone(client.getZone());

		return clientService.save(clientTmp);
	}

	@Secured({ROLE_ADMIN})
	@DeleteMapping("/clients/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteClient(@PathVariable Long id) {
		Client client = clientService.findById(id);
		
		try {
			fileUploadService.deleteFile(client.getPicture());
		} catch (IOException e) {
			throw new RuntimeException(IO_ERROR + e.getCause().getMessage());
		}

		clientService.delete(id);
	}

	@Secured({ROLE_ADMIN, ROLE_USER})
	@PostMapping("clients/upload")
	public Client uploadImagefile(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id) {

		Client clientTmp = clientService.findById(id);

		try {
			fileUploadService.deleteFile(clientTmp.getPicture());
			
			clientTmp.setPicture(fileUploadService.copyFile(file));
			
			return clientService.save(clientTmp);
			
		} catch (IOException e) {
			throw new RuntimeException(IO_ERROR + e.getCause().getMessage());
		}
	}

	@GetMapping("clients/upload/img/{fileName:.+}")
	public ResponseEntity<Resource> viewImage(@PathVariable String fileName) {

		Resource resource;
		HttpHeaders headers;
		try {
			resource = fileUploadService.uploadFile(fileName);
			headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");
		} catch (MalformedURLException e) {
			throw new RuntimeException(RESOURCE_ERROR + e.getCause().getMessage());
		}

		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}

	private void validateFieldErrors(BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<String> errors = bindingResult.getFieldErrors().stream()
					.map(error -> error.getField() + ": " + error.getDefaultMessage()).collect(Collectors.toList());
			throw new RuntimeException(errors.toString());
		}
	}
}
