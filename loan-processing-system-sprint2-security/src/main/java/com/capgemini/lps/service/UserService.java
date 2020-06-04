package com.capgemini.lps.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.capgemini.lps.entity.Loan;
import com.capgemini.lps.entity.User;

public interface UserService {

	public List<User> findAll();

	public User findById(int theId);

	User save(User theClient);

	public void deleteById(int theId);

	public User login(String email);
	
	public User findByEmail(String email);

	public User findByAadhar(String aadhar);

	public User findByPhone(String string);

	User getByEmail(String email);
	
//	public List<User> findAllCustomer();

	public Page<User> getClients(int pageNo, int itemsPerPage);

	public Page<User> getSortClients(int pageNo, int itemsPerPage, String fieldName);
	
	public Page<User> getAllRequested(int pageNo , int itemsPerPage);
	
	public Page<User> getAllSortedRequested(int pageNo , int itemsPerPage , String fieldName);
	
	public Page<User> findAllCustomer(int pageNo , int itemsPerPage); // new 
	
	public Page<User> findAllSortedCustomer(int pageNo , int itemsPerPage , String fieldName);// new



}
