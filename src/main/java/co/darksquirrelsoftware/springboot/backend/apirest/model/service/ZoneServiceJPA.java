package co.darksquirrelsoftware.springboot.backend.apirest.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.darksquirrelsoftware.springboot.backend.apirest.model.entity.Zone;
import co.darksquirrelsoftware.springboot.backend.apirest.model.repository.ZoneRepository;

@Service
public class ZoneServiceJPA implements ZoneService {
	
	private ZoneRepository zoneRepository;
	
	
	@Autowired
	public ZoneServiceJPA(ZoneRepository zoneRepository) {
		this.zoneRepository = zoneRepository;
	}

	@Override
	public List<Zone> findAll() {
		return (List<Zone>) zoneRepository.findAll();
	}

}
