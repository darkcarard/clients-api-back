package co.darksquirrelsoftware.springboot.backend.apirest.model.repository;

import org.springframework.data.repository.CrudRepository;

import co.darksquirrelsoftware.springboot.backend.apirest.model.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	public User findByUsername(String username);
}
