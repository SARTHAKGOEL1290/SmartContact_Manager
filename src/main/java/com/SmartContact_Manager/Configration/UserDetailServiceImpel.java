package com.SmartContact_Manager.Configration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.SmartContact_Manager.Dao.UserRepository;
import com.SmartContact_Manager.Entities.Users;

public class UserDetailServiceImpel implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Users users= userRepository.getUserByUserName(username);
		if(users == null)
		{
			throw new UsernameNotFoundException("User not Found !!!!");
		}
		
		CustomUserDetail customUserDetail = new CustomUserDetail(users);
		return customUserDetail;
	}

}
