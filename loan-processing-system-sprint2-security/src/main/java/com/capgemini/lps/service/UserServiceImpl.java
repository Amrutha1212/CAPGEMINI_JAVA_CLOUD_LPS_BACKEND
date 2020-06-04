package com.capgemini.lps.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.capgemini.lps.dao.UserRepository;
import com.capgemini.lps.entity.User;
import com.capgemini.lps.exception.EmailNotFoundException;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository theUserRepository) {
		userRepository = theUserRepository;
	}

//	@Override
//	public List<User> findAll() {
//		return userRepository.findAll();
//	}

	@Override
	public List<User> findAll() {
		return userRepository.findAllRequested();
	}

	@Override
	public User findById(int theId) {
		Optional<User> result = userRepository.findById(theId);

		User theClient = null;

		if (result.isPresent()) {
			theClient = result.get();
		}
		else {
			// we didn't find the employee
			throw new RuntimeException("Did not find product id - " + theId);
		}
		
		return theClient;
	}

	@Override
	public User save(User theClient) {
		theClient.setPassword(passwordEncoder.encode(theClient.getPassword()));
		return userRepository.save(theClient);
	}

	@Override
	public void deleteById(int theId) {
		userRepository.deleteById(theId);
	}

	@Override
	public Page<User> getClients(int pageNo, int itemsPerPage) {
		Pageable pageable = PageRequest.of(pageNo, itemsPerPage);
		return userRepository.findAll(pageable);

	}

	@Override
	public Page<User> getSortClients(int pageNo, int itemsPerPage, String fieldName) {
		Pageable pageable = PageRequest.of(pageNo, itemsPerPage, Sort.by(fieldName));
		return userRepository.findAll(pageable);
	}

	@Override
	public User login(String email) {

		Optional<User> result = userRepository.login(email);

		User theClient = null;

		if (result.isPresent()) {
			theClient = result.get();
		} else {
			// we didn't find the employee
			throw new EmailNotFoundException("Email-id does not exist !!! ");

		}
//		
		return theClient;
	}

	@Override
	public User getByEmail(String email) {
		return userRepository.getByEmail(email);
	}
	
	@Override
	public User findByEmail(String email) {

		return userRepository.findbyEmail(email);
	}

	@Override
	public User  findByAadhar(String aadhar) {

		return userRepository.findbyAadhar(aadhar);
	}

	@Override
	public User  findByPhone(String mobNo) {

		return userRepository.findbyMobile(mobNo);
	}
	

	@Override
	public Page<User> getAllRequested(int pageNo, int itemsPerPage) {
		Pageable pageable = PageRequest.of(pageNo , itemsPerPage);
		return userRepository.getAllRequested(pageable);
		
	}
	
	@Override
	public Page<User> getAllSortedRequested(int pageNo, int itemsPerPage, String fieldName) {
		Pageable pageable = PageRequest.of(pageNo, itemsPerPage, Sort.by(fieldName));
		return userRepository.getAllRequested(pageable);
	}
	
	@Override
	public Page<User> findAllCustomer(int pageNo, int itemsPerPage) {
		Pageable pageable = PageRequest.of(pageNo , itemsPerPage);
		return userRepository.findAllCustomer(pageable);
	}

	@Override
	public Page<User> findAllSortedCustomer(int pageNo, int itemsPerPage, String fieldName) {
		Pageable pageable = PageRequest.of(pageNo, itemsPerPage, Sort.by(fieldName));
		return userRepository.findAllCustomer(pageable);
	}


}
