package com.capgemini.lps.rest;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.lps.entity.Applicant;
import com.capgemini.lps.entity.User;
import com.capgemini.lps.exception.ApplicantNotFoundException;
import com.capgemini.lps.response.Response;
import com.capgemini.lps.service.ApplicantService;
import com.capgemini.lps.service.UserService;

@RestController
@RequestMapping("/api2")
@CrossOrigin(origins = "*")
public class ApplicantRestController implements Serializable {

	@Autowired
	private ApplicantService applicantService;

	@Autowired
	private UserService userService;

//	@GetMapping("/loanapplication")
//	public List<Applicant> findAll() {
//		return applicantService.findAll();
//	}

	@GetMapping("/loanApplication")
	public List<Applicant> findAllRequested() {
		return applicantService.findAll();
	}

	@PutMapping("/loanApplicationApprove/{appId}")
	public Response<Applicant> findAllApproved(@PathVariable Integer appId) {
		Applicant result = applicantService.setApproved(appId);
		if (result == null) {
			return new Response<>(true, "Updation failed", null);
		} else {
			return new Response<>(false, "Appication successfully approved!!", result);
		}
//		return applicantService.setApproved(appId);
	}

	@PutMapping("/loanApplicationReject/{appId}")
	public Response<Applicant> findAllRejected(@PathVariable Integer appId) {
		Applicant result = applicantService.setRejected(appId);
		if (result == null) {
			return new Response<>(true, "Updation failed", null);
		} else {
			return new Response<>(false, "Appication successfully rejected!!", result);
		}
	}

	@GetMapping("/loanApplication/{loanapplication}")
	public Applicant getById(@PathVariable int loanapplication) {

		Applicant applicant = applicantService.getById(loanapplication);

		if (applicant == null) {
			throw new ApplicantNotFoundException("id not found " + loanapplication);
		}
		return applicant;
	}

	@PostMapping("/AddLaoanApplication")
	public Response<Applicant> saveApplicant(@Valid @RequestBody Applicant applicant) {
		applicant.setAppId(0);
		Applicant result = applicantService.saveApplicant(applicant);
		if (result == null) {
			return new Response<>(true, "save failed", null);
		} else {
			return new Response<>(false, "Successfully saved", result);
		}
	}

	@PostMapping("/makeloan/{email}")
	public Response<Applicant> makeLoan(@PathVariable String email, @Valid @RequestBody Applicant applicant) {
		User user = userService.getByEmail(email);
		applicant.setUser(user);
		if (applicantService.saveApplicant(applicant) == null) {

			return new Response<>(true, "application not saved", null);

		} else {
			return new Response<>(false, "Application saved successfull", null);
		}
	}

	@PutMapping("/updateLoanApplication")
	public Applicant updateApplicant(@Valid @RequestBody Applicant applicant) {
		applicantService.saveApplicant(applicant);
		return applicant;
	}

}
