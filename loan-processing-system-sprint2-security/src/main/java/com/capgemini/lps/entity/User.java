package com.capgemini.lps.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_info")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int userId;

	
	@NotNull(message = "Please enter password")
//	@NotEmpty(message = "Please enter password")
	@Pattern(regexp = "(?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,}", message = "password must contain at least one number, one uppercase, one lowercase, one special character and atleast 8 or more characters.")
	@Column
	private String password;

	@NotNull
	@NotEmpty(message = "Please enter full name")
	@Pattern(regexp = "([a-zA-Z]{3,20}) ([a-zA-Z ]{1,15})", message = "please enter proper full name (eg. John M)")
	@Size(min = 3, message = "full name should contain atleast 3 characters")
	@Column(name = "full_name")
	private String fullName;

	@NotNull
	@NotEmpty(message = "Please enter email")
	@Email(message = "Please enter proper email address")
	@Size(min = 8, message = "email should contain atleast 8 characters")
	@Column
	private String email;

	@NotNull
	@NotEmpty(message = "Please enter mobile number")
	@Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 digits.")
	@Column(name = "mobile_no")
	private String mobileNo;

	@NotNull
	@NotEmpty(message = "Please enter role")
	@Pattern(regexp = "(ROLE_)+([A-Z]{5,15})", message = "Please enter role in the format ROLE_CUSTOMER")
	@Column
	private String role;

	@NotNull
	@NotEmpty(message = "Please enter gender")
	@Pattern(regexp = "Male|Female", message = "please enter proper gender (Male or Female)")
	@Column
	private String gender;

	@NotNull
	@NotEmpty(message = "Please enter Date of Birth")
	@Column
	private String dob;

	@NotNull
	@NotEmpty(message = "Please enter adhar number")
	@Pattern(regexp = "^\\d{12}$", message = "Adhar number must be 12 digits.")
	@Column(name = "adhar_no")
	private String adharNo;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//	@JoinColumn(name = "user_fk", referencedColumnName = "userid")
	@JsonManagedReference
	private List<Applicant> applicant;

}
