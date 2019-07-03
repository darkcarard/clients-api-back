package co.darksquirrelsoftware.springboot.backend.apirest.model.repository;

import org.springframework.data.repository.CrudRepository;

import co.darksquirrelsoftware.springboot.backend.apirest.model.entity.Zone;

public interface ZoneRepository extends CrudRepository<Zone, Long> {

}
