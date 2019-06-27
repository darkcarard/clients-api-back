package co.darksquirrelsoftware.springboot.backend.apirest.model.service;

import java.util.List;

import co.darksquirrelsoftware.springboot.backend.apirest.model.entity.Client;

public interface ClientService {

	public List<Client> findAll();
	
	public Client findById(Long id);
	
	public Client save(Client client);
	
	public void delete(Long id);
	
	
}
