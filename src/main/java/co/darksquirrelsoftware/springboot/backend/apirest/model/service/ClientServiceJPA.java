package co.darksquirrelsoftware.springboot.backend.apirest.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.darksquirrelsoftware.springboot.backend.apirest.exception.ClientEmailDuplicatedException;
import co.darksquirrelsoftware.springboot.backend.apirest.exception.ClientNotFoundException;
import co.darksquirrelsoftware.springboot.backend.apirest.model.entity.Client;
import co.darksquirrelsoftware.springboot.backend.apirest.model.repository.ClientRepository;

@Service
public class ClientServiceJPA implements ClientService {

	private final static String CLIENT_NOT_FOUND_MESSAGE = "Client not found!";
	private final static String CLIENT_DUPLICATED_EMAIL_MESSAGE = "The email is already registered!";

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
	public Page<Client> findAll(Pageable pageable) {
		return clientRepository.findAll(pageable);
	}
	
	@Override
	public Client findById(Long id) {
		return clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(CLIENT_NOT_FOUND_MESSAGE));
	}

	@Override
	public Client save(Client client) {
		Client clientTmp;
		try {
			clientTmp = clientRepository.save(client);
		} catch (DataIntegrityViolationException e) {		
			throw new ClientEmailDuplicatedException(CLIENT_DUPLICATED_EMAIL_MESSAGE);
		} catch (Exception e) {
			throw e;
		}
		return clientTmp;
	}

	@Override
	public void delete(Long id) {
		clientRepository.deleteById(id);
	}

}
