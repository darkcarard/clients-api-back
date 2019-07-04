package co.darksquirrelsoftware.springboot.backend.apirest.model.service;

import co.darksquirrelsoftware.springboot.backend.apirest.model.entity.User;

public interface UserService {

	public User findByUsername(String username);
}
