package co.darksquirrelsoftware.springboot.backend.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.darksquirrelsoftware.springboot.backend.apirest.model.entity.Client;
import co.darksquirrelsoftware.springboot.backend.apirest.model.service.ClientService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ClientRestController {

	private static final Integer NUMBER_OF_ROWS_PER_PAGE = 5;
	
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
	public Client saveClient(@RequestBody Client client) {
		return clientService.save(client);
	}

	@PutMapping("/clients/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Client updateClient(@RequestBody Client client, @PathVariable Long id) {
		Client clientTmp = clientService.findById(id);
		
		clientTmp.setFirstName(client.getFirstName());
		clientTmp.setLastName(client.getLastName());
		clientTmp.setEmail(client.getEmail());

		return clientService.save(clientTmp);
	}
	
	@DeleteMapping("/clients/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteClient(@PathVariable Long id) {
		clientService.delete(id);
	}
}
