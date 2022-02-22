package com.learning.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.learning.IdNotFoundException;
import com.learning.dto.Address;
import com.learning.dto.Role;
import com.learning.dto.User;
import com.learning.enums.RoleName;
import com.learning.exception.NoDataFoundException;
import com.learning.payload.request.SignupRequest;
import com.learning.payload.response.UserResponse;
import com.learning.repository.RoleRepo;
import com.learning.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService us;
	@Autowired
	private RoleRepo rr;
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteByUserId(@PathVariable("id") long id){
		if (us.existsbyId(id)) {
			us.deleteUserById(id);
			return ResponseEntity.noContent().build();
		}
		else {
			throw new NoDataFoundException("no user data");
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody SignupRequest sr) {
		Set<Role> roles = new HashSet<>();
		if(sr.getRoles()==null) {
			Role ru = rr.findByRoleName(RoleName.USER).orElseThrow(()-> new IdNotFoundException("Role not found"));
			roles.add(ru);
		}else {
		sr.getRoles().forEach(e->{
		switch(e) {
		case"user":
			Role ru = rr.findByRoleName(RoleName.USER).orElseThrow(()-> new IdNotFoundException("Role not found"));
			roles.add(ru);
			break;
		case "admin":
			Role ra = rr.findByRoleName(RoleName.ADMIN).orElseThrow(()->new IdNotFoundException("Role not found"));
			roles.add(ra);
		default: break;}
	
		});
		}
		User u = new User();
		Set<Address> addresses = new HashSet<>();
		sr.getAddress().forEach(e->{
			Address a = new Address();
			a.setCity(e.getCity());
			a.setCountry(e.getCountry());
			a.setHno(e.getHno());
			a.setState(e.getState());
			a.setStreet(e.getStreet());
			a.setUser(u);
			a.setZip(e.getZip());
			addresses.add(a);
		});
		u.setAddresses(addresses);
		u.setDoj(sr.getDateOfJoining());
		u.setEmail(sr.getEmail());
		u.setName(sr.getName());
		u.setPassword(sr.getPassword());
		u.setRoles(roles);
		User user = us.addUser(u);
		return ResponseEntity.status(201).body(user);
	}
	@GetMapping("/")
	public ResponseEntity<?> getAllUsers(){
		List<User> users = us.getAllUsers();
		List<UserResponse> urs = new ArrayList<>();
		users.forEach(e->{UserResponse ur = new UserResponse();
		ur.setEmail(e.getEmail());
		ur.setName(e.getName());
		Set<String> roles = new HashSet<>();
		ur.setDateOfJoining(e.getDoj());
		e.getRoles().forEach(e2->{roles.add(e2.getRoleName().name());});
		Set<com.learning.payload.request.Address> addresses = new HashSet<>();
		e.getAddresses().forEach(e3->{
			com.learning.payload.request.Address address2 = new com.learning.payload.request.Address();
			address2.setCity(e3.getCity());
			address2.setHno(e3.getHno());
			address2.setCountry(e3.getCountry());
			address2.setState(e3.getState());
			address2.setStreet(e3.getStreet());
			address2.setZip(e3.getZip());
			addresses.add(address2);
		});
		ur.setAddress(addresses);
		ur.setRoles(roles);
		urs.add(ur);
		});
		if(urs.size()>0) {
		return ResponseEntity.ok(urs);
		}
		else {
			throw new NoDataFoundException("no user data");
		}
	}
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id")long id) {
		
	User user =	us.getUserById(id).orElseThrow(()->new NoDataFoundException("data not available"));
		// DTO ===> UserResponse()
	UserResponse userResponse=  new UserResponse();
	userResponse.setEmail(user.getEmail());
	userResponse.setName(user.getName());
	Set<String> roles= new HashSet<>();
	userResponse.setDateOfJoining(user.getDoj());
	user.getRoles().forEach(e2->{
		roles.add(e2.getRoleName().name());
	});
	Set<com.learning.payload.request.Address> addresses = new HashSet<>();
	user.getAddresses().forEach(e3->{
		com.learning.payload.request.Address address2 = new com.learning.payload.request.Address();
		address2.setHno(e3.getHno());
		address2.setCity(e3.getCity());
		address2.setCountry(e3.getCountry());
		address2.setState(e3.getState());
		address2.setStreet(e3.getStreet());
		address2.setZip(e3.getZip());
		addresses.add(address2);
	});
	userResponse.setAddress(addresses);
	userResponse.setRoles(roles);
	return ResponseEntity.status(200).body(userResponse);
	}
	@PutMapping(value="/{id}")
	public ResponseEntity<?> updateUserById(@PathVariable("id")long id, String email) {
		
	User user =	us.getUserById(id).orElseThrow(()->new NoDataFoundException("data not available"));
		// first get user
	UserResponse userResponse=  new UserResponse();
	userResponse.setEmail(email);//is this it?
	userResponse.setName(user.getName());
	Set<String> roles= new HashSet<>();
	userResponse.setDateOfJoining(user.getDoj());
	user.getRoles().forEach(e2->{
		roles.add(e2.getRoleName().name());
	});
	Set<com.learning.payload.request.Address> addresses = new HashSet<>();
	user.getAddresses().forEach(e3->{
		com.learning.payload.request.Address address2 = new com.learning.payload.request.Address();
		address2.setHno(e3.getHno());
		address2.setCity(e3.getCity());
		address2.setCountry(e3.getCountry());
		address2.setState(e3.getState());
		address2.setStreet(e3.getStreet());
		address2.setZip(e3.getZip());
		addresses.add(address2);
	});
	userResponse.setAddress(addresses);
	userResponse.setRoles(roles);//there is no way it's this simple
	return ResponseEntity.status(200).body(userResponse);
	}
}
