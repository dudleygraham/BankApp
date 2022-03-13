package com.learning.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.entity.Customer;
import com.learning.entity.Role;
import com.learning.enums.RoleType;
import com.learning.exception.IdNotFoundException;
import com.learning.payload.request.SignInRequest;
import com.learning.payload.request.SignupRequest;
import com.learning.payload.response.JwtResponse;
import com.learning.repository.RoleRepository;
import com.learning.security.jwt.JwtUtils;
import com.learning.security.service.UserDetailsImpl;
import com.learning.service.CustomerService;



@RestController
// /api
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private CustomerService userService;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@PostMapping("/signIn")
	public ResponseEntity<?> signIn(@Valid @RequestBody SignInRequest signInRequest)
	{
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken
					(signInRequest.getUsername(), signInRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateToken(authentication);
			
			UserDetailsImpl userDetailsImpl = (UserDetailsImpl)authentication.getPrincipal();
			
			List<String> roles = userDetailsImpl.getAuthorities()
					.stream()
					.map(e->e.getAuthority())
					.collect(Collectors.toList());
			
			return ResponseEntity.ok(new JwtResponse(jwt, userDetailsImpl.getId(), 
					userDetailsImpl.getUsername(), 
					roles));
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return ResponseEntity.badRequest().body("Problem in sign in");
	}

	@PostMapping("/register")
	public ResponseEntity<?> createUser(@Valid @RequestBody  SignupRequest signupRequest){
		// can u create user object?
		// can u initialize the values based on the signuprequest object?
		Set<Role> roles = new HashSet<>();
		if(signupRequest.getRoles()==null) {
			Role userRole = roleRepository.findByRoleName(RoleType.CUSTOMER)
					.orElseThrow(()-> new IdNotFoundException("RoleId not found exception"));
				roles.add(userRole);
		}
		else {
			signupRequest.getRoles().forEach(e->{
				
				switch (e) {
				case "customer":					
						Role userRole = roleRepository.findByRoleName(RoleType.CUSTOMER)
						.orElseThrow(()->  new IdNotFoundException("RoleId not found exception"));
					roles.add(userRole);
					break;
				default:
					break;
				}
			});
		}
		
		Customer customer = new Customer();
 		System.out.println("Sign up request" + signupRequest);
		
		customer.setUsername(signupRequest.getUsername());
		customer.setFullname(signupRequest.getFullname());
		customer.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
		customer.setRoles(roles);

		Customer customer2 = userService.addCustomer(customer);
		
		return ResponseEntity.status(201).body(customer2);	
		// 400 401 500 501 201 200 301 302 404 503
	}
	
	

}