package co.darksquirrelsoftware.springboot.backend.apirest.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.darksquirrelsoftware.springboot.backend.apirest.model.entity.Client;
import co.darksquirrelsoftware.springboot.backend.apirest.model.repository.ClientRepository;

@Service
public class ClientServiceJPA implements ClientService {

	private ClientRepository clientRepository;
	
	@Autowired
	public ClientServiceJPA(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public List<Client> findAll() {
		return (List<Client>) clientRepository.findAll();
	}

	@Override
	public Client findById(Long id) {
		return clientRepository.findById(id).orElse(null);
	}

	@Override
	public Client save(Client client) {
		return clientRepository.save(client);
	}

	@Override
	public void delete(Long id) {
		clientRepository.deleteById(id);		
	}

	
	
}
