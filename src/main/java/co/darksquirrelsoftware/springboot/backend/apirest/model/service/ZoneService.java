package co.darksquirrelsoftware.springboot.backend.apirest.model.service;

import java.util.List;

import co.darksquirrelsoftware.springboot.backend.apirest.model.entity.Zone;

public interface ZoneService {

	public List<Zone> findAll();

}
