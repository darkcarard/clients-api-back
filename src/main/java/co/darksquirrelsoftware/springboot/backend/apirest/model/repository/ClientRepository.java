package co.darksquirrelsoftware.springboot.backend.apirest.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.darksquirrelsoftware.springboot.backend.apirest.model.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
