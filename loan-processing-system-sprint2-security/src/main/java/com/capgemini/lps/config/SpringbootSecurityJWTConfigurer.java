package com.capgemini.lps.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.capgemini.lps.filter.JwtRequestFilter;
import com.capgemini.lps.security.BootAuthenticationEntryPoint;




@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringbootSecurityJWTConfigurer extends WebSecurityConfigurerAdapter {

	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder(12);
	}
	
	@Autowired
	private BootAuthenticationEntryPoint  bootAuthenticationEntryPoint;
	
	@Autowired 
	private UserDetailsService userDetailsService;
	
	@Autowired 
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService);
		
	} // End of configureGlobal()
	
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.cors().and().csrf().disable()
		    .authorizeRequests().antMatchers("/api/login").permitAll()
		    .and()
		    .authorizeRequests().antMatchers("/api/getUser").permitAll()
		    .and()
		    .authorizeRequests().antMatchers("/api/getUserById").hasRole("CUSTOMER")
		    .and()
		    .authorizeRequests().antMatchers("/api/deleteUser").hasRole("ADMIN")
		    .and()
		    .authorizeRequests().antMatchers("/api/getUserByPage/{pageNo}/{itemsPerPage}").hasRole("ADMIN")
		    .and()
		    .authorizeRequests().antMatchers("/api/sortUser/{pageNo}/{itemsPerPage}/{fieldName}").hasRole("ADMIN")
		    .and()
		    .authorizeRequests().antMatchers("/api/addUser").permitAll()
		    .and()
		    .authorizeRequests().antMatchers("/api2/loanApplication").permitAll()
		    .and()
		    .authorizeRequests().antMatchers("/api2/loanApplicationApprove").hasRole("APPROVAL")
		    .and()
		    .authorizeRequests().antMatchers("/api2/loanApplicationReject").hasRole("APPROVAL")
		    .and()
		    .authorizeRequests().antMatchers("/api2/updateLoanApplication").permitAll()
		    .and()
		    .authorizeRequests().antMatchers("/api2/AddLaoanApplication").permitAll()
		    .and()
		    .authorizeRequests().antMatchers("/api2/makeloan/{email}").hasRole("CUSTOMER")
		    .and()
		    .authorizeRequests().antMatchers("/api1/getLoans/{pageNo}/{itemsPerPage}").permitAll()
		    .and()
		    .authorizeRequests().antMatchers("/api/getRequested/{pageNo}/{itemsPerPage}").hasRole("APPROVAL")
			.and()
			.authorizeRequests().antMatchers("/api/getSortRequested/{pageNo}/{itemsPerPage}/{fieldName}").hasRole("APPROVAL")
			.and()
			.authorizeRequests().antMatchers("/api/getCustomer/{pageNo}/{itemsPerPage}").hasRole("ADMIN")
			.and()
			.authorizeRequests().antMatchers("/api/getSortCustomer/{pageNo}/{itemsPerPage}/{fieldName}").hasRole("ADMIN")
			.and()
		    .authorizeRequests().antMatchers("/api1/getLoansByNo").permitAll()
		    .and()
		    .authorizeRequests().antMatchers("/api1/addLoans").hasRole("ADMIN")
		    .and()
		    .authorizeRequests().antMatchers("/api1/deleteLoans").hasRole("ADMIN")
		    .and()
		    .authorizeRequests().antMatchers("/api1/updateLoans").hasRole("ADMIN")
		    .anyRequest().authenticated()
		    .and()
		    .exceptionHandling().authenticationEntryPoint(bootAuthenticationEntryPoint)
		    .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	
		    http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		
		
	}// End of configure()
	
	
	
}// end of class
