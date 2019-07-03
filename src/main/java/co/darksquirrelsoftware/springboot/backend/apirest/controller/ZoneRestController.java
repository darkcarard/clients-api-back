package co.darksquirrelsoftware.springboot.backend.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.darksquirrelsoftware.springboot.backend.apirest.model.entity.Zone;
import co.darksquirrelsoftware.springboot.backend.apirest.model.service.ZoneService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ZoneRestController {

	private ZoneService zoneService;

	@Autowired
	public ZoneRestController(ZoneService zoneService) {
		this.zoneService = zoneService;
	}
	
	@GetMapping("/zones")
	public List<Zone> findAllZones() {
		return zoneService.findAll();
	}
	
}
