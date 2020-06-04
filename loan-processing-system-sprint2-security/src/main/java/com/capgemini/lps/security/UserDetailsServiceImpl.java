package com.capgemini.lps.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.capgemini.lps.dao.UserRepository;
import com.capgemini.lps.entity.User;
import com.capgemini.lps.service.UserService;

import lombok.Data;

@Data
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;
	public static User user;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetailsImpl udi = new UserDetailsImpl();
		udi.setUser(userService.login(username));
//		user=dao.getUser(username);
//		if(user!=null)
//		udi.setBean(user);
//		else {
//			throw new UsernameNotFoundException("User Not Found");
//		}
		return udi;
	}


}
