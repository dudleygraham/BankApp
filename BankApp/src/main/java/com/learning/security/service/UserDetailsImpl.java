package com.learning.security.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learning.entity.Customer;

import lombok.Data;

@Data
public class UserDetailsImpl implements UserDetails {
	
	private Long id;
	private String username;
	private String email;
	
	@JsonIgnore
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	private UserDetailsImpl(Long id, String username,
			String password, 
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}
	
	public static UserDetailsImpl build(Customer customer) {
		List<GrantedAuthority> authorities = customer.getRoles()
				.stream()
				.map(role-> new SimpleGrantedAuthority(role.getRoleName().name()))
				.collect(Collectors.toList());
		return new UserDetailsImpl(customer.getCustomerId(), 
				customer.getUsername(), 
				customer.getPassword(), 
				authorities);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return authorities;
	}

	@Override
	public String getPassword() {

		return password;
	}

	@Override
	public String getUsername() {

		return username;
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
	
	public boolean equals(Object o)
	{
		if(this == o)
			return true;
		  if(o == null || getClass() != o.getClass())
			return false;
		  UserDetailsImpl user = (UserDetailsImpl) o;
		  return Objects.equals(id, user.id);
	}

}
