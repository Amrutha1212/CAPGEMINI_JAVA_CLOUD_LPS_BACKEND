package com.capgemini.lps.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.lps.entity.Loan;
import com.capgemini.lps.exception.LoanProgNotFoundException;
import com.capgemini.lps.response.Response;
import com.capgemini.lps.service.LoanService;


@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api1")
public class LoanRestController {

	@Autowired
	private LoanService loanService;
	
	
	public LoanRestController(LoanService theLoanService) {
		loanService = theLoanService;
	}
	
	
	@GetMapping("/getLoans")
	public List<Loan> findAll1() {
		return loanService.findAll();
	}

	
	@GetMapping("/getLoansByNo/{loanNo}")
	public Response<Loan> getLoans(@PathVariable int loanNo) { 
		
		Loan theLoan = loanService.findById(loanNo);
		
		if (theLoan!= null) {
			return new Response<>(false,"Loan found",theLoan);
			
		}else {
			throw new LoanProgNotFoundException("Loan not found ");
			
		}
		
	}
	
	
	@PostMapping("/addLoans")
	public Response<Loan> addLoan(@Valid @RequestBody Loan theLoan) {
		
		theLoan.setLoanNo(0);
		Loan loan1= loanService.save(theLoan);
		if(loan1!=null) {
			return new Response<>(false,"Loan Program  added successfully",loan1);
		}
		else {
			return new Response<>(true,"loan cannot be added",null);
		}
	
	}
	
	
	
	@PutMapping("/updateLoans")
	public Response<Loan> updateProduct(@Valid @RequestBody Loan theProduct) {
		
		Loan product1 = loanService.save(theProduct);
		if(product1!=null) {
			return new Response<>(false,"Loan updated successfully",product1);
		}
		else {
			return new Response<>(true,"updation failed",null);
		}
	}
	
	
	
	@DeleteMapping("/deleteLoans/{loanNo}")
	public Response<Loan> deleteProduct(@PathVariable int loanNo) {
		
		Loan tempLoan = loanService.findById(loanNo);
		if (tempLoan != null) {
			loanService.deleteById(loanNo);
			return new Response<>(false,"Loan deleted successfully",tempLoan);
		}	else {
			
			throw new LoanProgNotFoundException("loan not found");
		}
		

	}

	@GetMapping("/getLoans/{pageNo}/{itemsPerPage}")
	public Page<Loan> getLoans(@PathVariable int pageNo ,@PathVariable int itemsPerPage){ 
	return loanService.getLoans(pageNo, itemsPerPage);
	}
	
	
	@GetMapping("/getLoans/{pageNo}/{itemsPerPage}/{fieldName}")
	public Page<Loan> getSortLoans(@PathVariable int pageNo,@PathVariable int itemsPerPage,@PathVariable String fieldName) {
		return loanService.getSortLoans(pageNo, itemsPerPage, fieldName);
	}

}










