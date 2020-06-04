package com.capgemini.lps.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.lps.entity.Applicant;
import com.capgemini.lps.response.Response;

public interface ApplicationRepository extends JpaRepository<Applicant, Integer>{
//@Query("delete from Applicant where user_id=?0")
//public Response<Applicant> deleteClient(int userId);
	@Query(value = "select a from Applicant a where applicationStatus = 'Requested'")
	List<Applicant> findAllRequested();
	
	@Query(value = "select a from Applicant a where applicationStatus = 'Approved'")
	List<Applicant> findAllApproved();
	
	@Query(value = "select a from Applicant a where applicationStatus = 'Rejected'")
	List<Applicant> findAllRejected();
}
