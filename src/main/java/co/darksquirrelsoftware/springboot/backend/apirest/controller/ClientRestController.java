package co.darksquirrelsoftware.springboot.backend.apirest.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ClientRestController {

	private static final Integer NUMBER_OF_ROWS_PER_PAGE = 5;
	private static final String PATH_UPLOADED_FILES = "uploads";
	private static final String IO_ERROR = "Error with file: ";
	private static final String RESOURCE_ERROR = "Can´t load the file!";

	private ClientService clientService;

	@Autowired
	public ClientRestController(ClientService clientService) {
		this.clientService = clientService;
	}

	@GetMapping("/clients")
	public List<Client> findAllClients() {
		return clientService.findAll();
	}

	@GetMapping("/clients/page/{page}")
	public Page<Client> findAllClients(@PathVariable Integer page) {
		return clientService.findAll(PageRequest.of(page, NUMBER_OF_ROWS_PER_PAGE));
	}

	@GetMapping("/clients/{id}")
	public Client findClientById(@PathVariable Long id) {
		return clientService.findById(id);
	}

	@PostMapping("/clients")
	@ResponseStatus(HttpStatus.CREATED)
	public Client saveClient(@Valid @RequestBody Client client, BindingResult bindingResult) {

		validateFieldErrors(bindingResult);

		return clientService.save(client);
	}

	@PutMapping("/clients/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Client updateClient(@Valid @RequestBody Client client, @PathVariable Long id, BindingResult bindingResult) {

		validateFieldErrors(bindingResult);

		Client clientTmp = clientService.findById(id);

		clientTmp.setFirstName(client.getFirstName());
		clientTmp.setLastName(client.getLastName());
		clientTmp.setEmail(client.getEmail());

		return clientService.save(clientTmp);
	}

	@DeleteMapping("/clients/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteClient(@PathVariable Long id) {
		Client client = clientService.findById(id);
		deleteFile(client);
		clientService.delete(id);
	}

	@PostMapping("clients/upload")
	public Client uploadImagefile(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id) {

		Client clientTmp = clientService.findById(id);

		deleteFile(clientTmp);
		clientTmp.setPicture(uploadFile(file));

		return clientService.save(clientTmp);
	}

	@GetMapping("clients/upload/img/{fileName:.+}")
	public ResponseEntity<Resource> viewImage(@PathVariable String fileName) {
		Resource resource = null;

		try {
			resource = new UrlResource(Paths.get(PATH_UPLOADED_FILES).resolve(fileName).toAbsolutePath().toUri());
		} catch (MalformedURLException e) {
			throw new RuntimeException(IO_ERROR + e.getCause().getMessage());
		}

		if (!resource.exists() || !resource.isReadable()) {
			throw new RuntimeException(RESOURCE_ERROR);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}

	private void validateFieldErrors(BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<String> errors = bindingResult.getFieldErrors().stream()
					.map(error -> error.getField() + ": " + error.getDefaultMessage()).collect(Collectors.toList());
			throw new RuntimeException(errors.toString());
		}
	}

	private String uploadFile(MultipartFile file) {
		String fileName = "";

		if (!file.isEmpty()) {
			fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename().replace(" ", "");
			Path filePath = Paths.get(PATH_UPLOADED_FILES).resolve(fileName).toAbsolutePath();
			try {
				Files.copy(file.getInputStream(), filePath);
			} catch (IOException e) {
				throw new RuntimeException(IO_ERROR + e.getCause().getMessage());
			}
		}

		return fileName;
	}

	private void deleteFile(Client client) {
		if (client.getPicture() != null && !client.getPicture().isEmpty()) {
			Path oldFilePath = Paths.get(PATH_UPLOADED_FILES, client.getPicture()).toAbsolutePath();
			try {
				Files.deleteIfExists(oldFilePath);
			} catch (IOException e) {
				throw new RuntimeException(IO_ERROR + e.getCause().getMessage());
			}
		}
	}

}
