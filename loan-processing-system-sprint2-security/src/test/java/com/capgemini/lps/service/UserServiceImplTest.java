package com.capgemini.lps.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.lps.entity.User;
import com.capgemini.lps.service.UserService;


@SpringBootTest
class UserServiceImplTest {


	@Autowired
	private UserService userService;

	User user;
	User addUser = null;

	@BeforeEach
	void addUser() {
		user = new User();
		user.setFullName("Karan Mehta");
		user.setAdharNo("692033697711");
		user.setEmail("karan@gmail.com");
		user.setDob("08/05/1990");
		user.setGender("Male");
		user.setMobileNo("9009765432");
		user.setRole("ROLE_ADMIN");
		user.setPassword("Qwerty@123");
		addUser = userService.save(user);
	}

	@Test
	void testAddUser() {
		assertNotNull(addUser);
	}

	@Test
	void testFindUser() {
		User user = userService.findById(this.addUser.getUserId());
		assertNotNull(user);
	}

	@Test
	void testFindAllUsers() {
		List<User> userList = userService.findAll();
		assertNotNull(userList);
	}

	@AfterEach
	void testDeleteUser() {
		user = userService.findById(this.addUser.getUserId());
		userService.deleteById(user.getUserId());
	}


}

