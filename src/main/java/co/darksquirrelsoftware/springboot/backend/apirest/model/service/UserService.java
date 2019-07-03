package co.darksquirrelsoftware.springboot.backend.apirest.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.darksquirrelsoftware.springboot.backend.apirest.model.entity.User;
import co.darksquirrelsoftware.springboot.backend.apirest.model.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	private final static String USER_NOT_FOUND_EXCEPTION_MESSAGE = "Username not found!";
	
	private Logger logger = LoggerFactory.getLogger(UserService.class);

	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		
		if (user == null) {
			logger.error(USER_NOT_FOUND_EXCEPTION_MESSAGE);
			throw new UsernameNotFoundException(USER_NOT_FOUND_EXCEPTION_MESSAGE);
		}
		
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				user.getEnabled(), true, true, true, authorities);
	}

}
