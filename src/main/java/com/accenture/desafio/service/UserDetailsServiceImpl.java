package com.accenture.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.accenture.desafio.domain.User;
import com.accenture.desafio.repository.UserRepository;
import com.accenture.desafio.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if(user == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(user.getId(), user.getEmail(), user.getSenha(), user.getOrigins());
	}

}
