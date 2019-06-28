package co.darksquirrelsoftware.springboot.backend.apirest.model.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import co.darksquirrelsoftware.springboot.backend.apirest.model.entity.Client;

public interface ClientService {

	public List<Client> findAll();

	public Page<Client> findAll(Pageable pageable);

	public Client findById(Long id);

	public Client save(Client client);

	public void delete(Long id);

}
