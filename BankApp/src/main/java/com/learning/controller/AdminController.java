package com.learning.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.entity.Customer;
import com.learning.entity.Role;
import com.learning.entity.Staff;
import com.learning.enums.EnableType;
import com.learning.enums.RoleType;
import com.learning.exception.IdNotFoundException;
import com.learning.exception.NoDataFoundException;
import com.learning.exception.UsernameExistsException;
import com.learning.payload.request.CreateStaffRequest;
import com.learning.payload.request.SignInRequest;
import com.learning.payload.request.SignupRequest;
import com.learning.payload.response.JwtResponse;
import com.learning.payload.response.StaffResponse;
import com.learning.security.jwt.JwtUtils;
import com.learning.security.service.UserDetailsImpl;
import com.learning.service.CustomerService;

import com.learning.service.StaffService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AdminController {
  @Autowired
  private StaffService staffService;
  @Autowired
  private CustomerService cs;
  @Autowired
  private AuthenticationManager am;
  @Autowired
  private JwtUtils jwt;
 
  @Autowired
  private PasswordEncoder passwordEncoder;
  
  StaffResponse staffResponse;
  @PostMapping("/staff")
	public ResponseEntity<?> register(@Valid @RequestBody SignupRequest sr) {	
	  	
		Staff s = new Staff();
		s.setStaffUserName(sr.getUsername());
		s.setStaffName(sr.getFullname());
		s.setStaffPassword(sr.getPassword());
		Set<Role> roles = new HashSet<>();

		Role staffRole = new Role();
		staffRole.setRoleName(RoleType.STAFF);
				
		roles.add(staffRole);
		s.setRoles(roles);
		
		Staff staff = staffService.addStaff(s);
		return ResponseEntity.status(201).body(staff);
  }
  @GetMapping("/staff")
	public ResponseEntity<?> getAllStaffs(){
		List<Staff> Staffs = staffService.getAllStaffs();
		List<StaffResponse> srs = new ArrayList<>();
		Staffs.forEach(e->{StaffResponse sr = new StaffResponse();
		sr.setId(e.getStaffId());
		sr.setPassword(e.getStaffPassword());
		sr.setName(e.getStaffName());
		sr.setUsername(e.getStaffUserName());
		srs.add(sr);
		});
		if(srs.size()>0) {
		return ResponseEntity.ok(srs);
		}
		else {
			throw new NoDataFoundException("no Staff data");
		}
	}
  	@GetMapping(value = "{staffId}")
  	public ResponseEntity<?> getStaffById(@PathVariable("staffId") long staffId)
  	{
  		Optional<Staff> staff = staffService.getStaffById(staffId);
  		return ResponseEntity.ok(staff);
  	}
  	@PutMapping(value="/{id}")
	public ResponseEntity<?> updateStaffById(@PathVariable("id")long id, EnableType status) {
		
	Staff staff = staffService.getStaffById(id).orElseThrow(()->new NoDataFoundException("data not available"));
	staff.setStatus(status);
	StaffResponse StaffResponse=  new StaffResponse();
	StaffResponse.setStatus(status);
	return ResponseEntity.status(200).body(StaffResponse);
	}
//  	@PostMapping(value = "/staff")
//	@PreAuthorize("hasRole('ADMIN')")
//	public ResponseEntity<?> createStaff(@Valid @RequestBody CreateStaffRequest request) {
//		// username already exists or any error
//		String userName = request.getUsername();
//		if (!cs.existsByUsername(userName)) {
//			Customer customer = new Customer();
//			customer.setFullname(request.getFullname());
//			customer.setUsername(userName);
//			String password = passwordEncoder.encode(request.getPassword());
//			customer.setPassword(password);
//
//			// set role to staff
//			Set<Role> roles = new HashSet<>();
//
//			Role staffRole = new Role();
//			staffRole.setRoleName(RoleType.STAFF);
//					
//			roles.add(staffRole);
//			customer.setRoles(roles);
//
//			Customer c = cs.addCustomer(customer);
//
//			StaffResponse staffRespose = new StaffResponse();
//			staffRespose.setId(c.getCustomerId());
//			staffRespose.setUsername(c.getUsername());
//			staffRespose.setName(c.getFullname());
//			staffRespose.setStatus(c.getEnabled());
//
//			return ResponseEntity.ok(staffRespose);
//		} else {
//			throw new UsernameExistsException("Username: " + request.getUsername() + " already exits");
//		}
//	}
  	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> login(@Valid @RequestBody SignInRequest request) {
		String username = request.getUsername();
		String password = request.getPassword();
		Authentication authentication = am
				.authenticate(new UsernamePasswordAuthenticationToken(username, password));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String j = jwt.generateToken(authentication);
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetailsImpl.getAuthorities().stream().map(e -> e.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponse(j, userDetailsImpl.getId(), userDetailsImpl.getUsername(), roles)); 
	}
}