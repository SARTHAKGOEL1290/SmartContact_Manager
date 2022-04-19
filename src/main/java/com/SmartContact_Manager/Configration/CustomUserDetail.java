package com.SmartContact_Manager.Configration;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.SmartContact_Manager.Entities.Users;

public class CustomUserDetail implements UserDetails {

	
      private Users users;
	
	public CustomUserDetail(Users users) {
		super();
		this.users = users;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		
		SimpleGrantedAuthority simplegranted = new SimpleGrantedAuthority(users.getRole());
		return List.of(simplegranted);
	}

	@Override
	public String getPassword() {
		
		return users.getPassword();
	}

	@Override
	public String getUsername() {
		
		return users.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
