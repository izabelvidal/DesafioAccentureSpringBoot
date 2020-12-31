package com.accenture.desafio.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.accenture.desafio.domain.enums.Origin;

public class UserSS implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String email;
	private String password;
	private Collection<? extends GrantedAuthority> authorites;

	public UserSS() {
	}

	public UserSS(Long id, String email, String password, Set<Origin> origin) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.authorites = origin.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao()))
				.collect(Collectors.toList());
	}

	
	public Long getId() {
		return id;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorites;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public boolean hasOrigin(Origin origin) {
		return getAuthorities().contains(new SimpleGrantedAuthority(origin.getDescricao()));
	}
}
