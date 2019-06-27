package co.darksquirrelsoftware.springboot.backend.apirest.model.repository;

import org.springframework.data.repository.CrudRepository;

import co.darksquirrelsoftware.springboot.backend.apirest.model.entity.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {

}
